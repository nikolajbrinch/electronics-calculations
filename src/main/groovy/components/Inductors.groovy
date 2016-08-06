package components

import groovy.transform.CompileStatic;

import java.util.List;
import java.util.Set;

@CompileStatic
class Inductors {

	public static Set<Double> MULTIPLIERS = [
		0.01,
		0.1,
		1,
		10,
		100] as Set<Double>

	public static List<Set<Integer>> SERIES = [
		ESeries.E24,
		ESeries.E24,
		ESeries.E24,
		ESeries.E24,
		ESeries.E6] as List<Set<Integer>>
}
