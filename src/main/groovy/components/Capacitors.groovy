package components

import groovy.transform.CompileStatic;

import java.util.List;
import java.util.Set;

@CompileStatic
class Capacitors {

	public static Set<Long> DIVISORS = [
		100000000000000,
		10000000000000,
		1000000000000,
		100000000000,
		10000000000,
		1000000000,
		100000000,
		10000000,
		1000000,
		100000,
		10000] as Set<Long>

	public static List<Set<Integer>> SERIES = [
		ESeries.E24,
		ESeries.E24,
		ESeries.E24,
		ESeries.E24,
		ESeries.E6,
		ESeries.E6,
		ESeries.E6,
		ESeries.E6,
		ESeries.E6,
		ESeries.E6,
		ESeries.E6]

	public static final Set<Capacitor> VALUES
	
	static {
		Set<Capacitor> capacitorValues = []
		
		Capacitors.DIVISORS.eachWithIndex { long divisor, int index ->
			Capacitors.SERIES[index].each { int capacitor ->
				capacitorValues << new Capacitor(new Value(capacitor, divisor))
//				long capacitorValue = (long) (capacitor *  multiplier * 10)
//				capacitorValues <<  (double) (capacitorValue / 10 * Math.pow(10, -12))
			}
		}

		VALUES = capacitorValues
	}

}
