package components

import groovy.transform.CompileStatic

@CompileStatic
class SeriesResistor extends Resistor {

	List<SingleResistor> resistors
	
	SeriesResistor(List<SingleResistor> resistors) {
		this.resistors = resistors
		this.value = resistors.first().value
		resistors.tail().each { Resistor resistor ->
			this.value = this.value.plus(resistor.value)
		}
	}

	@Override
	public String toString() {
		return "${super.toString()} : [${resistors.collect { it.toString() }.join(', ')}]"
	}
	
}
