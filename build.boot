(def +version+ "0.1.0")

(set-env!
 :source-paths    #{"src"}
 :resource-paths  #{"resources"}
 :target-path     "www"
 :dependencies
 '[[org.omcljs/om "1.0.0-alpha22"]
   [devcards "0.2.1"]

   [org.clojure/clojurescript "1.7.170" :scope "test"]
   [org.clojure/test.check "0.8.2" :scope "test"]

   [adzerk/boot-cljs          "1.7.48-6"   :scope "test"]
   [adzerk/boot-cljs-repl     "0.2.0"      :scope "test"]
   [adzerk/boot-reload        "0.4.1"      :scope "test"]
   [pandeiro/boot-http        "0.7.0"      :scope "test"]
   [crisptrutski/boot-cljs-test "0.2.0-SNAPSHOT" :scope "test"]])

(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
 '[adzerk.boot-reload    :refer [reload]]
 '[pandeiro.boot-http    :refer [serve]]
 '[crisptrutski.boot-cljs-test :refer [test-cljs]])


(task-options!
 test-cljs {:js-env :phantom})

(task-options!
 reload {:ws-host (.getHostAddress (java.net.Inet4Address/getLocalHost))})

(deftask build []
  (comp (cljs)))

(deftask run []
  (comp (watch)
        (cljs-repl)
        (reload)
        (speak)
        (build)))

(deftask production []
  (task-options! cljs   {:optimizations :advanced})
  identity)

(deftask development []
  #_(set-env! :source-paths #(into % #{"test"})
              :resource-paths #(into % #{"devcards"}))
  (task-options! cljs   {:optimizations :none :source-map true}
                 reload {:on-jsload 'cadabra.core/reload})
  identity)

(deftask dev
  "Simple alias to run application in development mode"
  []
  (comp (development)
        (run)
        #_(test-cljs)))


(deftask testing []
  (set-env! :source-paths #(into % #{"test"}))
  identity)


(ns-unmap 'boot.user 'test)

(deftask test []
  (comp (testing)
        (test-cljs :exit?  true)))

(deftask autotest []
  (comp (testing)
        (watch)
        (test-cljs)))

(deftask deploy []
  (println "I'm not implemented yet")
  identity)
