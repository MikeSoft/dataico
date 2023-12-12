(ns dataicogit.main
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:require [dataicogit.task_one :refer [task-one]])
  (:require [dataicogit.task_two :refer [task-two]])
  (:require [dataicogit.task_three :refer [task-three]])
  )


(def cli-options
  [["-h" "--help" "Show help"]
   ["-t" "--task number" "The task to run"]
   ["-f" "--file path" "The file to use as input"]
   ])


(defn helpText [options]
  (println "This is a simple Clojure script.")
  (println "Options:")
  (doseq [[short long desc] options]
    (println (str " " short ", " long " " desc))))

(defn task3 []
  (println "Task 3"))



(defn task [task-number & task-args]
  (cond
    (= task-number "1") (apply task-one task-args)
    (= task-number "2") (apply task-two task-args)
    (= task-number "3") (apply task-three task-args)
    :else (println "Invalid task number")))

(defn -main [& args]
  (let [{:keys [options errors summary]} (parse-opts args cli-options)]
    (println "Options:" options)
    (cond
      (:help options) (helpText cli-options)
      (:task options) (task (:task options) (:file options))
      errors (do (println "Error parsing options:" errors)
                 (println summary))
      :else (helpText cli-options))))


