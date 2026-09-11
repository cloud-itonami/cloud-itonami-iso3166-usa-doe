(ns statute.facts
  "Agency-level compliance catalog for **USA-DOE** (United States Department of
  Energy) -- the spec-basis behind this leaf's blueprint claim that an
  independent operator can navigate `DOE contractor registration and
  energy-sector security clauses`.

  Scope. This is the DOE-specific layer only. Government-wide U.S. federal
  statutes (Sarbanes-Oxley, FTC Act s5, FLSA, ...) live in the country
  coordinator `cloud-itonami-iso3166-usa`'s `statute.facts` and are NOT
  duplicated here; the two catalogs compose, keyed `USA-DOE` -> `USA`.

  Provenance. Every entry cites the official eCFR (Electronic Code of Federal
  Regulations, GPO/Office of the Federal Register) address for the smallest
  stable unit that was independently confirmed. Nothing here is fabricated:
  each `:statute/verified-label` below is the byte-exact `label_description`
  returned by the eCFR versioner API on `:statute/verified-at`, and
  `statute.facts-test` re-fetches that API and fails if any label drifts.

  Why the citation and the verification URL differ. `:statute/url` is the
  canonical human address a person should open. It is deliberately NOT the
  URL that was machine-verified: fetching www.ecfr.gov from an automated
  client returns HTTP 200 with a `Federal Register :: Request Access`
  interstitial rather than the regulation, so a status-code check against it
  would report success while proving nothing. We therefore verify through the
  documented machine API (`:statute/verified-via`) and record both. Do not
  `curl` the `:statute/url` and treat a 200 as confirmation -- it is not.

  Extending. A regulation not in this table has NO spec-basis, full stop.
  Extend `catalog` with a real, API-confirmed citation; never invent an id,
  a URL, or a label."
  (:require [kotoba.lang.text :as str]))

(def ecfr-structure-api
  "eCFR versioner structure endpoints these entries were verified against.
  Keyed by CFR title. The date is the title's `up_to_date_as_of` at
  verification time, so the call is reproducible rather than `current`."
  {10 "https://www.ecfr.gov/api/versioner/v1/structure/2026-08-17/title-10.json"
   48 "https://www.ecfr.gov/api/versioner/v1/structure/2026-08-07/title-48.json"})

(def catalog
  "iso3166 code -> vector of regulation entries.

  `USA-DOE` is an agency-level key (parent `USA`), matching
  `blueprint.edn`'s `:itonami.blueprint/iso3166`."
  {"USA-DOE"
   [;; ── DEAR: 48 CFR chapter 9 — DOE's own acquisition regulation ─────────
    {:statute/id "usa-doe.dear"
     :statute/title "Department of Energy Acquisition Regulation (DEAR)"
     :statute/jurisdiction "USA-DOE"
     :statute/kind :regulation
     :statute/law-number "48 CFR Chapter 9"
     :statute/url "https://www.ecfr.gov/current/title-48/chapter-9"
     :statute/url-provenance :official-ecfr
     :statute/cfr-title 48
     :statute/cfr-node [:chapter "9"]
     :statute/verified-via "https://www.ecfr.gov/api/versioner/v1/structure/2026-08-07/title-48.json"
     :statute/verified-label "Department of Energy"
     :statute/verified-at "2026-08-19"
     :statute/topic #{:public-procurement :agency-supplement}}
    {:statute/id "usa-doe.dear-904-administrative-matters"
     :statute/title "DEAR Part 904 — Administrative Matters (includes DOE classified-information and security subparts)"
     :statute/jurisdiction "USA-DOE"
     :statute/kind :regulation
     :statute/law-number "48 CFR Part 904"
     :statute/url "https://www.ecfr.gov/current/title-48/chapter-9/subchapter-A/part-904"
     :statute/url-provenance :official-ecfr
     :statute/cfr-title 48
     :statute/cfr-node [:part "904"]
     :statute/verified-via "https://www.ecfr.gov/api/versioner/v1/structure/2026-08-07/title-48.json"
     :statute/verified-label "Administrative Matters"
     :statute/verified-at "2026-08-19"
     :statute/topic #{:public-procurement :security-clearance}}
    {:statute/id "usa-doe.dear-909-contractor-qualifications"
     :statute/title "DEAR Part 909 — Contractor Qualifications"
     :statute/jurisdiction "USA-DOE"
     :statute/kind :regulation
     :statute/law-number "48 CFR Part 909"
     :statute/url "https://www.ecfr.gov/current/title-48/chapter-9/subchapter-B/part-909"
     :statute/url-provenance :official-ecfr
     :statute/cfr-title 48
     :statute/cfr-node [:part "909"]
     :statute/verified-via "https://www.ecfr.gov/api/versioner/v1/structure/2026-08-07/title-48.json"
     :statute/verified-label "Contractor Qualifications"
     :statute/verified-at "2026-08-19"
     :statute/topic #{:public-procurement :contractor-registration}}
    {:statute/id "usa-doe.dear-923-environment"
     :statute/title "DEAR Part 923 — Environment, Sustainable Acquisition, and Material Safety"
     :statute/jurisdiction "USA-DOE"
     :statute/kind :regulation
     :statute/law-number "48 CFR Part 923"
     :statute/url "https://www.ecfr.gov/current/title-48/chapter-9/subchapter-D/part-923"
     :statute/url-provenance :official-ecfr
     :statute/cfr-title 48
     :statute/cfr-node [:part "923"]
     :statute/verified-via "https://www.ecfr.gov/api/versioner/v1/structure/2026-08-07/title-48.json"
     :statute/verified-label "Environment, Sustainable Acquisition, and Material Safety"
     :statute/verified-at "2026-08-19"
     :statute/topic #{:public-procurement :environment}}
    {:statute/id "usa-doe.dear-952-clauses"
     :statute/title "DEAR Part 952 — Solicitation Provisions and Contract Clauses"
     :statute/jurisdiction "USA-DOE"
     :statute/kind :regulation
     :statute/law-number "48 CFR Part 952"
     :statute/url "https://www.ecfr.gov/current/title-48/chapter-9/subchapter-H/part-952"
     :statute/url-provenance :official-ecfr
     :statute/cfr-title 48
     :statute/cfr-node [:part "952"]
     :statute/verified-via "https://www.ecfr.gov/api/versioner/v1/structure/2026-08-07/title-48.json"
     :statute/verified-label "Solicitation Provisions and Contract Clauses"
     :statute/verified-at "2026-08-19"
     :statute/topic #{:public-procurement :contract-clauses :security-clearance}}
    {:statute/id "usa-doe.dear-970-mo-contracts"
     :statute/title "DEAR Part 970 — DOE Management and Operating Contracts"
     :statute/jurisdiction "USA-DOE"
     :statute/kind :regulation
     :statute/law-number "48 CFR Part 970"
     :statute/url "https://www.ecfr.gov/current/title-48/chapter-9/subchapter-I/part-970"
     :statute/url-provenance :official-ecfr
     :statute/cfr-title 48
     :statute/cfr-node [:part "970"]
     :statute/verified-via "https://www.ecfr.gov/api/versioner/v1/structure/2026-08-07/title-48.json"
     :statute/verified-label "DOE Management and Operating Contracts"
     :statute/verified-at "2026-08-19"
     :statute/topic #{:public-procurement :facility-management}}

    ;; ── 10 CFR chapter III — DOE's substantive site/worker rules ──────────
    {:statute/id "usa-doe.10cfr707-substance-abuse"
     :statute/title "10 CFR Part 707 — Workplace Substance Abuse Programs at DOE Sites"
     :statute/jurisdiction "USA-DOE"
     :statute/kind :regulation
     :statute/law-number "10 CFR Part 707"
     :statute/url "https://www.ecfr.gov/current/title-10/chapter-III/part-707"
     :statute/url-provenance :official-ecfr
     :statute/cfr-title 10
     :statute/cfr-node [:part "707"]
     :statute/verified-via "https://www.ecfr.gov/api/versioner/v1/structure/2026-08-17/title-10.json"
     :statute/verified-label "Workplace Substance Abuse Programs at DOE Sites"
     :statute/verified-at "2026-08-19"
     :statute/topic #{:worksite-compliance :labor}}
    {:statute/id "usa-doe.10cfr824-classified-info-penalties"
     :statute/title "10 CFR Part 824 — Procedural Rules for the Assessment of Civil Penalties for Classified Information Security Violations"
     :statute/jurisdiction "USA-DOE"
     :statute/kind :regulation
     :statute/law-number "10 CFR Part 824"
     :statute/url "https://www.ecfr.gov/current/title-10/chapter-III/part-824"
     :statute/url-provenance :official-ecfr
     :statute/cfr-title 10
     :statute/cfr-node [:part "824"]
     :statute/verified-via "https://www.ecfr.gov/api/versioner/v1/structure/2026-08-17/title-10.json"
     :statute/verified-label "Procedural Rules for the Assessment of Civil Penalties for Classified Information Security Violations"
     :statute/verified-at "2026-08-19"
     :statute/topic #{:security-clearance :enforcement}}
    {:statute/id "usa-doe.10cfr851-worker-safety"
     :statute/title "10 CFR Part 851 — Worker Safety and Health Program"
     :statute/jurisdiction "USA-DOE"
     :statute/kind :regulation
     :statute/law-number "10 CFR Part 851"
     :statute/url "https://www.ecfr.gov/current/title-10/chapter-III/part-851"
     :statute/url-provenance :official-ecfr
     :statute/cfr-title 10
     :statute/cfr-node [:part "851"]
     :statute/verified-via "https://www.ecfr.gov/api/versioner/v1/structure/2026-08-17/title-10.json"
     :statute/verified-label "Worker Safety and Health Program"
     :statute/verified-at "2026-08-19"
     :statute/topic #{:worksite-compliance :labor}}

    ;; ── FAR: government-wide prerequisite a DOE bidder must satisfy ───────
    {:statute/id "usa-doe.far"
     :statute/title "Federal Acquisition Regulation (FAR)"
     :statute/jurisdiction "USA-DOE"
     :statute/kind :regulation
     :statute/law-number "48 CFR Chapter 1"
     :statute/url "https://www.ecfr.gov/current/title-48/chapter-1"
     :statute/url-provenance :official-ecfr
     :statute/cfr-title 48
     :statute/cfr-node [:chapter "1"]
     :statute/verified-via "https://www.ecfr.gov/api/versioner/v1/structure/2026-08-07/title-48.json"
     :statute/verified-label "Federal Acquisition Regulation"
     :statute/verified-at "2026-08-19"
     :statute/topic #{:public-procurement}}
    {:statute/id "usa-doe.far-4.11-sam-registration"
     :statute/title "FAR Subpart 4.11 — System for Award Management (contractor registration prerequisite)"
     :statute/jurisdiction "USA-DOE"
     :statute/kind :regulation
     :statute/law-number "48 CFR Subpart 4.11"
     :statute/url "https://www.ecfr.gov/current/title-48/chapter-1/subchapter-A/part-4/subpart-4.11"
     :statute/url-provenance :official-ecfr
     :statute/cfr-title 48
     :statute/cfr-node [:subpart "4.11"]
     :statute/verified-via "https://www.ecfr.gov/api/versioner/v1/structure/2026-08-07/title-48.json"
     :statute/verified-label "System for Award Management"
     :statute/verified-at "2026-08-19"
     :statute/topic #{:public-procurement :contractor-registration}}]})

(defn spec-basis
  "Entries for `iso3166`, or nil when this catalog claims no basis for it."
  [iso3166]
  (get catalog iso3166))

(defn by-topic
  "Entries for `iso3166` carrying `topic`. Empty for unknown jurisdictions."
  [iso3166 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3166)))

(defn citations
  "Every official URL in the catalog, deduplicated and sorted. This is the
  set `statute.facts-test` and the ingest gate check."
  []
  (->> (vals catalog)
       (mapcat identity)
       (map :statute/url)
       distinct
       sort
       vec))

(defn coverage
  "Honest coverage report: what was asked for vs what has a spec-basis.
  Never reports a jurisdiction as covered because it looks plausible."
  ([] (coverage (keys catalog)))
  ([iso3166s]
   (let [have (filter catalog iso3166s)
         missing (remove catalog iso3166s)]
     {:requested (count iso3166s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :citation-count (count (citations))
      :note (str "cloud-itonami-iso3166-usa-doe statute.facts: "
                 (count (get catalog "USA-DOE"))
                 " DOE agency-level regulations, each confirmed against the "
                 "official eCFR versioner API on 2026-08-19. Government-wide "
                 "U.S. statutes are NOT here -- see cloud-itonami-iso3166-usa. "
                 "Extend `statute.facts/catalog`; never invent an id or URL.")})))

(defn ecfr-api-for
  "The eCFR structure endpoint an entry was verified against."
  [entry]
  (get ecfr-structure-api (:statute/cfr-title entry)))

(defn official-url?
  "True when `u` is an eCFR address. The only provenance this catalog accepts."
  [u]
  (and (string? u) (str/starts-with? u "https://www.ecfr.gov/current/")))
