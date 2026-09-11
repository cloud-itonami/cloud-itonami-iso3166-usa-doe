# cloud-itonami-iso3166-usa-doe

Open ISO 3166 **agency-level** Blueprint for **USA-DOE**: Department of Energy
(parent country: **USA**).

This leaf designs a forkable OSS business for an independent operator
navigating **Department of Energy**-specific public-procurement / regulatory compliance
(DOE contractor registration and energy-sector security clauses), composing with the country coordinator
`cloud-itonami-iso3166-usa`.

## What this is NOT

- **Not Department of Energy.** Commercial compliance navigation only.
- **Not legal advice.** Cite official sources; route licensed work to counsel.

## Official surface

- https://www.energy.gov/

## Spec-basis (what the compliance claim rests on)

`src/statute/facts.cljk` — 11 DOE agency-level regulations, each cited to the
official eCFR and confirmed against the eCFR versioner API on 2026-08-19:

- **DEAR** (48 CFR ch. 9): parts 904, 909, 923, 952, 970
- **10 CFR ch. III** (DOE): parts 707, 824, 851
- **FAR** (48 CFR ch. 1) incl. subpart 4.11 (SAM registration)

Government-wide U.S. statutes are **not** duplicated here — they belong to the
country coordinator `cloud-itonami-iso3166-usa`. The two catalogs compose.

    kbb -M:test                  # offline: shape + provenance rules
    kbb --backend sci tools/verify_citations.cljk  # live: re-checks every label against eCFR

The live gate exits `0` verified / `1` drifted / `2` could-not-answer. Note it
deliberately does **not** `curl` the citation URLs: www.ecfr.gov answers
automated clients with HTTP 200 and a "Request Access" interstitial, so a
status-code check there would report success while proving nothing.

## Capability layer

Resolves via `kotoba-lang/iso3166` (`USA-DOE`, parent `USA`).

## License

AGPL-3.0-or-later.
