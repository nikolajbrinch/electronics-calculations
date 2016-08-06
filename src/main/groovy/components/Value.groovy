package components

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Value {

	long counter

	long divider

	Value(long counter) {
		this(counter, 1)
	}
	
	Value(long counter, long divider) {
		this.counter = counter
		this.divider = divider
	}

	Value(Value value) {
		this(value.counter, value.divider)
	}

	Value plus(Value value) {
		return new Value(this.counter * value.divider + value.counter * this.divider, this.divider * value.divider)
	}

	Value plus(int value) {
		return this.plus(new Value(value, 1))
	}

	Value minus(Value value) {
		return new Value(this.counter * value.divider - value.counter * this.divider, this.divider * value.divider)
	}

	Value minus(int value) {
		return this.minus(new Value(value, 1))
	}

	Value multiply(Value value) {
		return new Value(this.counter * value.counter, value.divider * this.divider)
	}

	Value multiply(int value) {
		return new Value(this.counter * value, this.divider)
	}

	Value divide(Value value) {
		return new Value(this.counter * value.divider, value.counter * this.divider)
	}
	
	Value divide(int value) {
		return new Value(this.counter, this.divider * value)
	}
	
	double getValue() {
		return (double) counter / (double) divider
	}

	@Override
	public String toString() {
		return "${getValue()}"
	}

}
