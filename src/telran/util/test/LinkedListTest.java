package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.ArrayList;
import telran.util.Collection;
import telran.util.LinkedList;

public class LinkedListTest extends ListTest {
	@Override
	@BeforeEach
	
	void setUp() throws Exception {
		collection = new LinkedList<Integer>();
		super.setUp();
	}
	@Override
	protected Collection<Integer> getCollection(Integer[] ar1) {
		LinkedList<Integer>  res  = new  LinkedList<>();
		for (Integer num: ar1)
		{
			res.add(num);
		}
		return res;

	}

}
