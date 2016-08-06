package components

import spock.lang.Specification

class OpAmpSpec extends Specification {

	void "test inverting 1"() {
		when:
			OpAmp opAmp = new OpAmp.Inverting(new Value(30), new SingleResistor(new Value(108200)), new SingleResistor(new Value(18018)) )

		then:
			opAmp.vOut.value == -5.0
			opAmp.gain.value == 0.167
	}
	
	void "test non-inverting"() {
		when:
			OpAmp opAmp = new OpAmp.NonInverting(new Value(-250, 1000), new SingleResistor(new Value(10000)), new SingleResistor(new Value(190000)))

		then:
			opAmp.vOut.value == -5.0
			opAmp.gain.value == 20.0
	}

}
