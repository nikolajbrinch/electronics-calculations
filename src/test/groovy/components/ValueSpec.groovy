package components

import spock.lang.Specification

class ValueSpec extends Specification {

	void "test plus"() {
		when:
		double value = new Value(1, 2).plus(new Value(2, 4)).value

		then:
		value == 1
		
		when:
		value = new Value(1, 2).plus(4).value

		then:
		value == 4.5
	}

	void "test minus"() {
		when:
		double value = new Value(1, 2).minus(new Value(2, 4)).value

		then:
		value == 0
		
		when:
		value = new Value(2, 1).minus(1).value

		then:
		value == 1
	}

	void "test multiply"() {
		when:
			double value = new Value(1, 2).multiply(new Value(2, 4)).value

		then:
			value == 0.25
			
		when:
			 value = new Value(1, 2).multiply(2).value

		then:
			value == 1
	}
	
	void "test divide"() {
		when:
			double value = new Value(1, 2).divide(new Value(2, 4)).value

		then:
			value == 1
			
		when:
			 value = new Value(1, 2).divide(2).value

		then:
			value == 0.25
	}

}
