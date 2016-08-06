package components

import groovy.transform.CompileStatic

@CompileStatic
class ComponentComparator<T extends Component> implements Comparator<T> {

	ValueComparator valueComparator = new ValueComparator()
	
	@Override
	public int compare(T o1, T o2) {
		return valueComparator.compare(o1?.value, o2?.value)
	}

}
