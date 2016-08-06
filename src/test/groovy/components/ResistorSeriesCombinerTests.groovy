package components

import org.junit.Test

class ResistorSeriesCombinerTests {

	@Test
	void testCombineDepth1() {
		Set<SeriesResistor> resistorSeries = new ResistorSeriesCombiner().createSeriesResistorSeries(Resistors.E6, 1)

		resistorSeries.each { it -> println it }
	}

	@Test
	void testCombineDepth2() {
		Set<SeriesResistor> resistorSeries = new ResistorSeriesCombiner().createSeriesResistorSeries(Resistors.E24, 2)

		resistorSeries.each { it -> println it }
	}
	
	@Test
	void testCombineDepth2Min() {
		Set<SeriesResistor> resistorSeries = new ResistorSeriesCombiner().createSeriesResistorSeries(Resistors.E24, 2, 10000)

		resistorSeries.each { it -> println it }
	}

}
