package calculations;

import static org.junit.Assert.*

import org.junit.Test

import components.OpAmp
import components.ResistorSeriesCombiner
import components.Resistors
import components.SeriesResistor
import components.Value

class OpAmpResistorSearchTests {
	
	Set<SeriesResistor> resistorSeries = new ResistorSeriesCombiner().createSeriesResistorSeries(Resistors.E24, 2, 1000, 1000000)
	
	@Test
	void testInverting() {
		List<OpAmp> opAmps = new OpAmpResistorSearch().findOpAmps(new Value(30), new Value(-5), resistorSeries)
//		List<OpAmp> opAmps = new OpAmpResistorSearch().findOpAmps(new Value(30), new Value(-4096, 1000), resistorSeries)
		
		opAmps.each {
			println it
		}
		
		opAmps = new OpAmpResistorSearch().findOpAmps(new Value(250, 1000), new Value(-5), resistorSeries)
//		opAmps = new OpAmpResistorSearch().findOpAmps(new Value(300, 1000), new Value(-4096, 1000), resistorSeries)
		
		opAmps.each {
			println it
		}
	}
	
	@Test
	void testNonInverting() {
		List<OpAmp> opAmps = new OpAmpResistorSearch().findOpAmps(new Value(250, 1000), new Value(5), resistorSeries)
//		List<OpAmp> opAmps = new OpAmpResistorSearch().findOpAmps(new Value(-300, 1000), new Value(-4096, 1000), resistorSeries)
		
		opAmps.each {
			println it
		}
	}

}
