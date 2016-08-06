package components

import groovy.transform.CompileStatic


@CompileStatic
class Resistors {

	static Set<Integer> MULTIPLIERS = [-100, -10, 1, 10, 100, 1000, 10000] as Set<Integer>
	
	public static final Set<SingleResistor> E6
	 
	public static Set<SingleResistor> E12 
	
	public static Set<SingleResistor> E24
	
	public static Set<SingleResistor> E48
	
	public static Set<SingleResistor> E96
	
	public static Set<SingleResistor> EBAY
	
	static {
		E6 = createResistorSeries(ESeries.E6)
		E12 = createResistorSeries(ESeries.E12)
		E24 = createResistorSeries(ESeries.E24)
		E48 = createResistorSeries(ESeries.E48)
		E96 = createResistorSeries(ESeries.E96)
	}
	
	static Set<SingleResistor> createResistorSeries(Set<Integer> values) {
		Set<SingleResistor> resistors = new TreeSet<Component>(new ComponentComparator()) as Set<SingleResistor>
		
		MULTIPLIERS.each { int multiplier ->
			values.each { int value ->
				Value componentValue
				if (multiplier > 0) {
					componentValue = new Value(value, 1).multiply(multiplier)
				} else {
					componentValue = new Value(value, multiplier.abs())
				}
				resistors << new SingleResistor(componentValue)
			}
		}
		
		return resistors
	}
	
}
