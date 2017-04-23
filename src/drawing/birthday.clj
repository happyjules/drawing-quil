(ns drawing.birthday
  (:require [quil.core :as q]
            [quil.middleware :as m]))

;;need setup, draw, and sketch functions
(defn setup []
  (q/text-font (q/create-font "DejaVu Sans" 36 false))
  {:balloon (q/load-image "images/balloon.png")
   :params [{:x 10 :y 500 :speed 2 :swing 2} 
            {:x 200 :y 450 :speed 4 :swing 0.5} 
            {:x 500 :y 480 :speed 3 :swing 1} 
            {:x 700 :y 490 :speed 2 :swing 1.5}]})

(defn update-y
  [m]
  (let [y (:y m)
        speed (:speed m)]
    (if (< y -300)           ;; y is greater than or equal to image height?
      (assoc m :y 500)                ;; true - get it back to the 0 (top)
      (update-in m [:y] - speed))))

(defn update-x
  [m]
  (let [x (:x m)
        swing (:swing m)
        y (:y m)]
    (cond
     (< x 0) (assoc m :x (q/width))                                  ;; too left
     (< x (q/width)) (update-in m [:x] + (* swing (q/sin (/ y 50)))) ;; within frame
     :else (assoc m :x 0))))   

(defn update [state]
  (let [params (:params state)
        params (map update-y params)
        params (map update-x params)]
    (assoc state :params params)))

(defn draw [state]
  (q/background (q/color 150 200 50))
  (q/text "Happy Birthday Andrew!" 250 250)
  (let [param (:params state)]
    (dotimes [n (count param)]
      (q/image (:balloon state) (:x (nth param n)) (:y (nth param n))))))


(q/defsketch birthday
  :title "You can see balloons"
  :size [1000 500]
  :setup setup
  :update update
  :draw draw
  :features [:keep-on-top]
  :middleware [m/fun-mode])


