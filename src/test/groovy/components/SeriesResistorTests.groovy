package components

import groovy.transform.CompileStatic

import org.junit.Test


@CompileStatic
class SeriesResistorTests {

	@Test
	void testToString() {
		List<SingleResistor> resistors = [new SingleResistor(new Value(100)), new SingleResistor(new Value(120))] as List<SingleResistor>
		Resistor resistor = new SeriesResistor(resistors)
	}
	
	@Test
	void testOrderSet() {
		Set<Resistor> resistors = new TreeSet<Resistor>(new ComponentComparator<Resistor>())
		
		List<SingleResistor> resistorValues = [new SingleResistor(new Value(240)), new SingleResistor(new Value(120))] as List<SingleResistor>
		resistors << new SeriesResistor(resistorValues)
		
		resistorValues = [new SingleResistor(new Value(100)), new SingleResistor(new Value(120))] as List<SingleResistor>
		resistors << new SeriesResistor(resistorValues)
		
		resistors << new SingleResistor(new Value(110))

		resistorValues = [new SingleResistor(new Value(100))] as List<SingleResistor>
		resistors << new SeriesResistor(resistorValues)

		resistors.each { it ->
			println it
		}
	}

}
