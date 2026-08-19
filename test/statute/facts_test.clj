(ns statute.facts-test
  "Offline conformance gate for the DOE citation catalog. Runs with no
  network: it pins the shape and provenance rules that make the catalog
  trustworthy. The complementary *live* check -- re-fetching the eCFR API
  and diffing every `:statute/verified-label` -- is `tools/verify_citations.cljs`,
  which is what actually proves the citations are not fabricated."
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is testing]]
            [statute.facts :as facts]))

(def ^:private entries (facts/spec-basis "USA-DOE"))

(deftest doe-has-spec-basis
  (is (seq entries) "USA-DOE must have a spec-basis; an empty catalog is not a pass")
  (is (= 11 (count entries))))

(deftest every-citation-is-official-ecfr
  (testing "no entry may cite anything but the official eCFR"
    (doseq [e entries]
      (is (facts/official-url? (:statute/url e))
          (str (:statute/id e) " cites a non-eCFR URL: " (:statute/url e)))
      (is (= :official-ecfr (:statute/url-provenance e))
          (str (:statute/id e) " has provenance " (:statute/url-provenance e))))))

(deftest every-entry-carries-its-verification-evidence
  (testing "a citation without recorded evidence is indistinguishable from a fabricated one"
    (doseq [e entries]
      (is (contains? #{10 48} (:statute/cfr-title e))
          (str (:statute/id e) " has no CFR title"))
      (is (= (facts/ecfr-api-for e) (:statute/verified-via e))
          (str (:statute/id e) " verified-via does not match its CFR title's API endpoint"))
      (is (and (string? (:statute/verified-label e))
               (seq (:statute/verified-label e)))
          (str (:statute/id e) " has no verified-label"))
      (is (= "2026-08-19" (:statute/verified-at e))
          (str (:statute/id e) " has no verification date")))))

(deftest required-keys-present
  (doseq [e entries]
    (doseq [k [:statute/id :statute/title :statute/jurisdiction :statute/kind
               :statute/law-number :statute/url :statute/topic]]
      (is (contains? e k) (str (:statute/id e) " is missing " k)))
    (is (= "USA-DOE" (:statute/jurisdiction e)))
    (is (seq (:statute/topic e)) (str (:statute/id e) " has no topic"))))

(deftest ids-and-urls-are-unique
  (is (= (count entries) (count (distinct (map :statute/id entries))))
      "duplicate :statute/id")
  (is (= (count entries) (count (distinct (map :statute/url entries))))
      "duplicate :statute/url — two entries pointing at one regulation"))

(deftest law-number-agrees-with-cfr-title
  (testing "a 10 CFR entry may not be filed under title 48, or vice versa"
    (doseq [e entries]
      (is (str/starts-with? (:statute/law-number e)
                            (str (:statute/cfr-title e) " CFR"))
          (str (:statute/id e) ": law-number " (pr-str (:statute/law-number e))
               " contradicts :statute/cfr-title " (:statute/cfr-title e))))))

(deftest url-path-agrees-with-cfr-title
  (doseq [e entries]
    (is (str/includes? (:statute/url e) (str "/title-" (:statute/cfr-title e) "/"))
        (str (:statute/id e) ": URL is not under its declared CFR title"))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (testing "the catalog must not answer for jurisdictions it does not cover"
    (is (nil? (facts/spec-basis "USA"))
        "government-wide USA law belongs to cloud-itonami-iso3166-usa, not here")
    (is (nil? (facts/spec-basis "USA-DOD")))
    (is (nil? (facts/spec-basis "ZZZ")))
    (is (empty? (facts/by-topic "ZZZ" :public-procurement)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["USA-DOE" "USA" "ZZZ"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["USA" "ZZZ"] (:missing-jurisdictions c)))
    (is (= 11 (:citation-count c)))))

(deftest by-topic-filters
  (is (= ["usa-doe.10cfr707-substance-abuse" "usa-doe.10cfr851-worker-safety"]
         (mapv :statute/id (facts/by-topic "USA-DOE" :worksite-compliance)))
      "both 10 CFR site rules carry :worksite-compliance")
  (is (= ["usa-doe.dear-909-contractor-qualifications"
          "usa-doe.far-4.11-sam-registration"]
         (mapv :statute/id (facts/by-topic "USA-DOE" :contractor-registration)))
      "registration path = DEAR 909 qualifications + FAR 4.11 SAM")
  (is (empty? (facts/by-topic "USA-DOE" :maritime))
      "a topic the catalog does not cover must return empty, not a guess"))

(deftest citations-are-the-ingest-surface
  (let [cs (facts/citations)]
    (is (= 11 (count cs)))
    (is (every? facts/official-url? cs))
    (is (= cs (sort cs)) "citations must be sorted for stable diffing")))
