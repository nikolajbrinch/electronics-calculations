package components

import groovy.transform.CompileStatic

@CompileStatic
class SingleResistor extends Resistor {

	SingleResistor(Value value) {
		super(value)
	}

	SingleResistor(SingleResistor singleResistor) {
		this(new Value(singleResistor.value))
	}

}
