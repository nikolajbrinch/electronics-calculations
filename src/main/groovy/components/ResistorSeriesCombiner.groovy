package components

class ResistorSeriesCombiner {

	Set<SeriesResistor> createSeriesResistorSeries(Set<SingleResistor> resistorSeries, int depth, long minimumValue = 1, long maximumValue = -1) {
		Set<SeriesResistor> resistors = new TreeSet<SeriesResistor>(new ComponentComparator())
		 
		for (int i = 1; i <= depth; i++) {
			resistors = combine(resistorSeries, i, resistors, minimumValue, maximumValue)
		}
		
		return resistors
	}
	
	private Set<SeriesResistor> combine(Set<SingleResistor> resistorSeries, int depth, Set<SeriesResistor> resistors, long minimumValue = 1, long maximumValue = -1) {
		Set<SeriesResistor> result = new TreeSet<SeriesResistor>(new ComponentComparator())

		resistorSeries.each { SingleResistor resistor ->
			if (depth == 1) {
				SeriesResistor newResistor = new SeriesResistor([createResistor(resistor)])
				
				addResistor(result, newResistor, minimumValue, maximumValue)
			} else {		
				resistors.each { SeriesResistor seriesResistor ->
					SeriesResistor newResistor = new SeriesResistor(seriesResistor.resistors + createResistor(resistor))
					
					addResistor(result, newResistor, minimumValue, maximumValue)
				}
			}
		}
		
		return resistors + result
	}
	
	private SingleResistor createResistor(SingleResistor resistor) {
		return new SingleResistor(resistor)
	}
	
	private void addResistor(Set<SeriesResistor> result, SeriesResistor newResistor, long minimumValue = 1, long maximumValue = -1) {
		if (newResistor.value.value >= (double) minimumValue) {
			if (maximumValue == -1 || newResistor.value.value <= (double) maximumValue) {
				result << newResistor
			}
		}
	}
	
}
