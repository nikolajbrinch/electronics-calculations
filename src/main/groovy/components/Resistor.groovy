package components

import groovy.transform.CompileStatic

@CompileStatic
abstract class Resistor extends Component {

	Resistor() {}
	
	Resistor(Value value) {
		super(value)
	}

	@Override
	public String toString() {
		String letter = 'R'
		
		double outValue = value.value
		
		String result = "${outValue}"
		int index = 0
		
		String[] parts = result.split('\\.')
		String intPart = parts[0]
		String decimalPart = parts[1]
		
		if (outValue >= 1000000) {
			letter = 'M'
			index = 6
		} else if (outValue >= 1000) {
			letter = 'K'
			index = 3
		}
		
		decimalPart = intPart.substring(intPart.length() - index) + decimalPart
		decimalPart = "${Long.parseLong(decimalPart.reverse())}".reverse()
		intPart = intPart.substring(0, intPart.length() - index)
		
		result = "${intPart}${letter}"
		
		if (Long.parseLong(decimalPart) > 0) {
			result = "${result}${decimalPart}"
		}
		
		return result
	}
	
}
