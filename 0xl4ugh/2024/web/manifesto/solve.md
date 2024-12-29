1. Add ?username=admin query param.
2. Use `read-string` parsing to execute Clojure code.
3. Spend half an hour trying to figure out how Clojure works and then
get the flag with `#=(environ.core/env :flag)`