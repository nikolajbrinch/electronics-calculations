package components

import groovy.transform.CompileStatic

@CompileStatic
abstract class OpAmp {
	
	enum Type {
		INVERTING('Inverting'), NON_INVERTING('NonInverting')
		
		String name
		
		private Type(String name) {
			this.name = name
		}
	}

	Value vIn
	
	Value vOut
			
	Resistor rIn
	
	Resistor rG
	
	Value gain
	
	Type type
	
	static class Inverting extends OpAmp {
				
		Inverting(Value vIn, Resistor rIn, Resistor rG) {
			this.vIn = vIn
			this.rIn = rIn
			this.rG = rG
			this.gain = calcGain()
			this.vOut = calcVOut()
			this.type = Type.INVERTING
		}

		private Value calcGain() {
			return rG.value.divide(rIn.value)
		}
		
		private Value calcVOut() {
			return gain.multiply(vIn).multiply(-1)
		}
	
	}
	
	static class NonInverting extends OpAmp {
		
		NonInverting(Value vIn, Resistor rIn, Resistor rG) {
			this.vIn = vIn
			this.rIn = rIn
			this.rG = rG
			this.gain = calcGain()
			this.vOut = calcVOut()
			this.type = Type.NON_INVERTING
		}

		private Value calcGain() {
			return rG.value.divide(rIn.value).plus(1)
		}
		
		private Value calcVOut() {
			return gain.multiply(vIn) 
		}
	}
	
	@Override
	public String toString() {
		return "OpAmp[${type}] Vin: ${vIn}, Vout: ${vOut}, Rin: ${rIn}, Rgain: ${rG}, Gain: ${gain}"
	}
	
	
}
