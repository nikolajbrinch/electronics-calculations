(ns calculations)

(defn vinMin
  [vin]
  (/ (* vin 90) 100))
  
(defn abs
  [n]
  (max n (* -1 n)))

(defn tonDivOff 
  [vinmin absvout f vsat] 
  (/ (+ absvout f) (- vinmin vsat)))
  
(defn mc34063
  [vin vout iout vripple f vsat]
  (tonDivOff (vinMin vin) (abs vout) f vsat ))
