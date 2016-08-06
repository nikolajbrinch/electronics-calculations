package calculations

import groovy.transform.CompileStatic;

import java.util.Set

import components.Capacitor;
import components.Capacitors;
import components.ESeries;
import components.Inductors;
import components.Resistors;

@CompileStatic
abstract class Mc34063Support {

	Set<Integer> resistorSeries = ESeries.E12
	double vsat = 1.0
	double vin
	double vout
	double iout
	int fmin
	double vripple
	double vf
	double realRsc
	double r1
	double r2
	double realVout
	double realCt
	double realCo
	double realLmin

	public Mc34063Support(double vin, double vout, double iout, int fmin, double vripple, double vf) {
		this.vin = vin
		this.vout = vout
		this.iout = iout
		this.fmin = fmin
		this.vripple = vripple
		this.vf = vf
		calculateRsc()
		calculateResistors()
		calculateCt()
		calculateCo()
		calculateLmin()
	}

	public abstract double getTonDividedByToff()

	public abstract double getIPkSwitch()

	public abstract double getCo()

	public abstract double getLMin()

	public double getTonAddToff() {
		return  1 / fmin
	}

	public double getToff() {
		return getTonAddToff() / (getTonDividedByToff() + 1)
	}

	public double getTon() {
		return getTonAddToff() - getToff()
	}

	public double getCt() {
		return 4 * Math.pow(10, -5) * getTon()
	}

	public double getRsc() {
		return 0.3 / getIPkSwitch()
	}

	public double getAbsVout() {
		return Math.abs(vout)
	}

	public double getVinMin() {
		return vin * 0.9
	}

	public void calculateRsc() {
		int rsc = (int) (getRsc() * 1000)

		this.realRsc = rsc * 1000

		resistorSeries.each { resistor ->
			if (Math.abs(rsc - resistor) < Math.abs(rsc - realRsc)) {
				realRsc = resistor
			}
		}

		realRsc = realRsc / 1000
	}

	public void calculateCt() {
		double ct = getCt()

		this.realCt = ct * 1000

		Capacitors.VALUES.each { Capacitor capacitor ->
			if (Math.abs(ct - capacitor.value.value) < Math.abs(ct - realCt)) {
				realCt = capacitor.value.value
			}
		}
	}

	public void calculateCo() {
		double co = getCo()

		this.realCo = co * 1000

		Capacitors.VALUES.each { Capacitor capacitor ->
			if (capacitor.value.value >= co) {
				if (Math.abs(co - capacitor.value.value) < Math.abs(co - realCo)) {
					realCo = capacitor.value.value
				}
			}
		}
	}

	public void calculateLmin() {
		double lmin = getLMin()

		this.realLmin = lmin * 1000

		getInductorValues().each { double inductor ->
			if (inductor >= lmin) {
				if (Math.abs(lmin - inductor) < Math.abs(lmin - realLmin)) {
					realLmin = inductor
				}
			}
		}
	}

	public Set<Integer> getResistorValues() {
		Set<Integer> resistorValues = []

		Resistors.MULTIPLIERS.each { int multiplier ->
			resistorSeries.each { int resistor ->
				resistorValues << resistor * multiplier
			}
		}

		return resistorValues
	}

	private Set<Double> getInductorValues() {
		Set<Double> inductorValues = []

		Inductors.MULTIPLIERS.eachWithIndex { double multiplier, int index ->
			Inductors.SERIES[index].each { inductor ->
				long inductorValue = (long) (inductor *  multiplier * 10)
				inductorValues <<  (double) (inductorValue / 10 * Math.pow(10, -6))
			}
		}

		return inductorValues
	}

	public void calculateResistors() {
		this.realVout = vout * 1000

		getResistorValues().each { int r1Value ->
			getResistorValues().each { int r2Value ->
				double v = 1.25 * (1 + (r2Value / r1Value))

				if (Math.abs(getAbsVout() - v) < Math.abs(getAbsVout() - realVout)) {
					this.r1 = r1Value
					this.r2 = r2Value
					realVout = v
				}
			}
		}

		realVout = realVout * (vout < 0 ? -1 : 1)
	}

	public double getRealVout() {
		return realVout
	}
}
