package components

import groovy.transform.CompileStatic

@CompileStatic
abstract class Component {

	Value value
	
	Component() {}
	
	Component(Value value) {
		this.value = value
	}
	
}
