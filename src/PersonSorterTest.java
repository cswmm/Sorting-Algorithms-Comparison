

import static org.junit.Assert.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;


public class PersonSorterTest {

	Person JohnDoe = new Person("Doe", "John", Date.valueOf("1990-06-22"));
	Person HarryPotter = new Person("Potter", "Harry", Date.valueOf("1989-08-11"));
	Person JamesPotter = new Person("Potter", "James", Date.valueOf("1969-06-11"));
	Person JamesBond = new Person("Bond", "James", Date.valueOf("1990-06-23"));

	// TODO: you can add more persons for your tests

	Person[] P = { JohnDoe, HarryPotter, JamesBond, JamesPotter};

	/**
	 * Test sorting BY_LAST_NAME
	 */
	@Test
	public void testPersonSorter_BY_LAST_NAME() {
		// The list sorted using your method
		Person[] sortedP1 = PersonSorter.sortBy(P, PersonSorter.BY_LAST_NAME);
		List<Person> list1 = Arrays.asList(sortedP1);

		// The list sorted using Collections.sort (native Java sorting method)
		List<Person> list2 = Arrays.asList(P);
		Collections.sort(list2, new PersonComparator(PersonSorter.BY_LAST_NAME));

		// check whether your sorting method is correct
		assertEquals(list1, list2);
	}
	
	/**
	 * Test sorting BY_DATE_OF_BIRTH
	 */
	@Test
	public void testPersonSorter_BY_DATE_OF_BIRTH() {
		
		Person[] sortedP1 = PersonSorter.sortBy(P, PersonSorter.BY_DATE_OF_BIRTH);
		List<Person> list1 = Arrays.asList(sortedP1);

		List<Person> list2 = Arrays.asList(P);
		Collections.sort(list2, new PersonComparator(PersonSorter.BY_DATE_OF_BIRTH));

		assertEquals(list1, list2);
	}

	/**
	 * Test sorting BY_LAST_NAME then BY_DATE_OF_BIRTH
	 */
	@Test
	public void testPersonSorter_BY_LAST_NAME_THEN_DATE_OF_BIRTH() {

		Person[] sortedP1 = PersonSorter.sortByLastNameAndDateOfBirth(P);
		List<Person> list1 = Arrays.asList(sortedP1);

		List<Person> list2 = Arrays.asList(P);
		Collections.sort(list2, new PersonComparator(PersonSorter.BY_LAST_NAME, PersonSorter.BY_DATE_OF_BIRTH));

		assertEquals(list1, list2);
	}
	
	
	

	/**
	 * Do not modify this class. This is for testing the correctness of your
	 * sorting algorithm
	 * 
	 * @author rprasojo
	 *
	 */
	private class PersonComparator implements Comparator<Person> {

		private List<Integer> byValues = new ArrayList<Integer>();

		PersonComparator(int... bvs) {
			for (Integer val : bvs) {
				byValues.add(val);
			}
		}

		@Override
		public int compare(Person p1, Person p2) {
			int ret = 0;
			for (Integer byValue : byValues) {
				switch (byValue) {
				case PersonSorter.BY_LAST_NAME:
					ret = p1.getLastName().compareTo(p2.getLastName());
					break;
				case PersonSorter.BY_DATE_OF_BIRTH:
					ret = p1.getDateOfBirth().compareTo(p2.getDateOfBirth());
					break;
				// TODO: add more cases here for every
				default:
					ret = 0;
				}
				if (ret != 0)
					break;
			}

			return ret;
		}

	}

}
