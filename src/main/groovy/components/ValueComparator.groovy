package components

class ValueComparator implements Comparator<Value> {

	@Override
	public int compare(Value o1, Value o2) {
		return o1?.value <=> o2?.value
	}

}
