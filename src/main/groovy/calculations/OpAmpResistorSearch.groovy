package calculations

import components.OpAmp
import components.Resistor
import components.Value

class OpAmpResistorSearch {

	List<OpAmp> findOpAmps(Value vIn, Value vOut, Set<Resistor> resistors) {
		List<OpAmp> opAmps
		
		if ((vOut.value / vIn.value).abs() < 1 
			|| (vIn.value > 0 && vOut.value < 0) 
			|| (vIn.value < 0 && vOut.value > 0)) {
			opAmps = findInvertingOpAmps(vIn, vOut, resistors)
		} else {
			opAmps = findNonInvertingOpAmps(vIn, vOut, resistors)
		}
		
		return opAmps
	}
	
	private List<OpAmp> findInvertingOpAmps(Value vIn, Value vOut, Set<Resistor> resistors) {
		List<OpAmp> opAmps = []
		
		resistors.each { Resistor rIn ->
			resistors.each { Resistor rG ->
				OpAmp opAmp = new OpAmp.Inverting(vIn, rIn, rG)
				
				if (opAmp.vOut.value == vOut.value) {
					opAmps << opAmp
				}
			}
		}
		
		return opAmps
	}
	
	private List<OpAmp> findNonInvertingOpAmps(Value vIn, Value vOut, Set<Resistor> resistors) {
		List<OpAmp> opAmps = []
		
		resistors.each { Resistor rIn ->
			resistors.each { Resistor rG ->
				OpAmp opAmp = new OpAmp.NonInverting(vIn, rIn, rG)
				
				if (opAmp.vOut.value == vOut.value) {
					opAmps << opAmp
				}
			}
		}
		
		return opAmps
	}
}
