(ns cadabra.core)

(enable-console-print!)

(println 'CORE)

(defn main []
  (let [c (.. js/document (createElement "DIV"))]
    (aset c "innerHTML" "<p>i'm dynamically created</p>")
    (.. js/document (getElementById "app") (appendChild c))))

(defn init []
  (println 'INIT))

(defn reload []
  (main))
