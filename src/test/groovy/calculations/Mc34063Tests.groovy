package calculations;

import static org.junit.Assert.*;

import org.junit.Test;

class Mc34063Tests {

	@Test
	public void test2() {
		Mc34063.Inverter inverter = new Mc34063.Inverter(20.0, -29.0, 0.200, 75000, 0.005, 0.4)
		outputInverter(inverter)
	}

	@Test
	public void test4() {
		Mc34063 inverter = new Mc34063.StepUp(20.0, 29.0, 0.200, 75000, 0.005, 0.4)
		outputInverter(inverter)
	}

	public void outputInverter(Mc34063 inverter) {
		println "----------------------------------------"
		println "Vin/Vout/Iout (V/V/A): ${inverter.vin}/${inverter.vout}/${inverter.iout}"
		println "Ton/Toff: ${Math.round((inverter.getTonDividedByToff()) * 100)/100}"
		println "Ton + Toff (uS): ${Math.round((inverter.getTonAddToff() * Math.pow(10, 6)) * 10)/10}"
		println "Ton (uS): ${Math.round((inverter.getTon() * Math.pow(10, 6)) * 10)/10}"
		println "Toff (uS): ${Math.round((inverter.getToff() * Math.pow(10, 6)) * 10)/10}"
		println "CT (pF): ${Math.ceil(inverter.getCt() * Math.pow(10, 12))}"
		println "Ipk(switch) (A): ${Math.round((inverter.getIPkSwitch()) * 1000)/1000}"
		println "Rsc (R): ${Math.round((inverter.getRsc()) * 100)/100}"
		println "L(min) (uH): ${Math.ceil(inverter.getLMin() * Math.pow(10, 6))}"
		println "CO (uF): ${Math.ceil(inverter.getCo() * Math.pow(10, 6))}"
		println "R1 (Kohm): ${(inverter.r1) / 1000}"
		println "R2 (Kohm): ${inverter.r2 / 1000}"
		println "Real Ct (pF): ${Math.ceil(inverter.realCt * Math.pow(10, 12))}"
		println "Real Rsc (ohm): ${inverter.realRsc}"
		println "Real L(min) (uH): ${Math.ceil(inverter.realLmin * Math.pow(10, 6))}"
		println "Real Co (uF): ${Math.ceil(inverter.realCo * Math.pow(10, 6))}"
		println "Real Vout (V): ${Math.round((inverter.getRealVout()) * 1000)/1000}"
	}
}
