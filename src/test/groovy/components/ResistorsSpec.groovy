package components

import spock.lang.Specification

class ResistorsSpec extends Specification {

	void "test E6"() {
		when:
			Set<SingleResistor> resistors = Resistors.E6

		then:
			resistors.each { SingleResistor resistor ->
				println resistor
			}
		
	}

}
