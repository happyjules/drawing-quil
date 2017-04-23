(ns drawing.lines
  (:require [quil.core :as q]))

;;need setup, draw, and sketch functions
(defn setup []
  (q/frame-rate 50)
  (q/color-mode :rgb))


(defn draw [] 
  (q/stroke (q/mouse-y) (q/mouse-y) (q/mouse-x))
  (q/line 0 0 (q/mouse-x) (q/mouse-y))
  (q/line 800 0 (q/mouse-x) (q/mouse-y))
  (q/line 0 800 (q/mouse-x) (q/mouse-y))
  (q/line 800 800 (q/mouse-x) (q/mouse-y))
  (q/text "Happy Birthday Andrew" 300 400))


(q/defsketch hello-lines
  :title "You can see lines"
  :size [800 800]
  :setup setup
  :draw draw
  :features [:keep-on-top])

