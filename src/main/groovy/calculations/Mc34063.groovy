package calculations

import groovy.transform.CompileStatic;


@CompileStatic
interface Mc34063 {

	static class StepUp extends Mc34063Support implements Mc34063 {

		public StepUp(double vin, double vout, double iout, int fmin, double vripple) {
			super(vin, vout, iout, fmin, vripple, 0.4)
		}

		public StepUp(double vin, double vout, double iout, int fmin, double vripple, double vf) {
			super(vin, vout, iout, fmin, vripple, vf)
		}

		public double getTonDividedByToff() {
			return (getAbsVout() + vf - getVinMin()) / (getVinMin() - vsat)
		}

		public double getIPkSwitch() {
			return 2 * iout * (getTonDividedByToff() + 1)
		}

		public double getLMin() {
			return ((getVinMin() - vsat) / getIPkSwitch()) * getTon()
		}

		public double getCo() {
			return 9 * ((iout * getTon()) / vripple)
		}
	}

	static class StepDown extends Mc34063Support implements Mc34063 {

		public StepDown(double vin, double vout, double iout, int fmin, double vripple) {
			super(vin, vout, iout, fmin, vripple, 0.4)
		}

		public StepDown(double vin, double vout, double iout, int fmin, double vripple, double vf) {
			super(vin, vout, iout, fmin, vripple, vf)
		}

		public double getTonDividedByToff() {
			return (getAbsVout() + vf) / (getVinMin() - vsat - getAbsVout())
		}

		public double getIPkSwitch() {
			return 2 * iout
		}

		public double getLMin() {
			return ((getVinMin() - vsat - getAbsVout()) / getIPkSwitch()) * getTon()
		}

		public double getCo() {
			return (getIPkSwitch() * getTonAddToff()) / (8 * vripple)
		}
	}

	static class Inverter extends Mc34063Support implements Mc34063 {

		public Inverter(double vin, double vout, double iout, int fmin, double vripple) {
			super(vin, vout, iout, fmin, vripple, 0.4)
		}

		public Inverter(double vin, double vout, double iout, int fmin, double vripple, double vf) {
			super(vin, vout, iout, fmin, vripple, vf)
		}

		public double getTonDividedByToff() {
			return (getAbsVout() + vf) / (getVinMin() - vsat)
		}

		public double getIPkSwitch() {
			return 2 * iout * (getTonDividedByToff() + 1)
		}

		public double getLMin() {
			return ((getVinMin() - vsat) / getIPkSwitch()) * getTon()
		}

		public double getCo() {
			return 9 * ((iout * getTon()) / vripple)
		}
	}
}
