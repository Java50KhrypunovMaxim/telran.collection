package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.List;

abstract class ListTest extends CollectionTest {

	List<Integer> list;
	@BeforeEach
	@Override
	void setUp() throws Exception {
		super.setUp();
		list = (List<Integer>)collection;
	}

	@Override
	void addTest() {
		Integer [] expected = {10,-20, 8, 14, 30, 12, 100, 10};
		assertTrue(list.add(10));
		assertArrayEquals(expected, list.toArray(new Integer[0]));
	}

	@Test
	void addIndexTest()
	{
		Integer [] expected = {10, 10,-20, 8, 14, 30, 12, 100};
		list.add(0, 10);
		assertArrayEquals(expected, list.toArray(new Integer[0]));
	}
	@Test
	void indexOfTest()
	{
		assertEquals(3, list.indexOf(14));
		assertEquals(5, list.indexOf(12));
		assertEquals(-1, list.indexOf(122));
	}
	
	@Test
	void lastIndexOfTest() // дает последний индекс обьекта который встречается в коллекции
	{
		assertTrue(list.add(8));
		assertEquals(7, list.lastIndexOf(8));
		assertTrue(list.add(8));
		assertEquals(8, list.lastIndexOf(8));
	}
	
	@Test
	void indexOfPredicateTest ()
	{
		assertEquals(1, list.indexOf(n->n<=0));
		assertEquals(-1, list.indexOf(n->n>101));
		assertEquals(4, list.indexOf(n->n>25));// вернет первое число которое подходит под предикат!!
		assertEquals(0, list.indexOf(n->n>0));
		assertEquals(-1, list.indexOf(n->n%2!=0));
	}
	
	@Test
	void lastIndexOfPredicateTest() 
	{
		assertEquals(6, list.lastIndexOf(n->n%2==0));
		assertEquals(-1, list.lastIndexOf(n->n%2!=0));
		assertEquals(4, list.lastIndexOf(n-> n<40 && n>20 ));
	}
	
	@Test
	void getTest() 
	{
		assertEquals(100, list.get(6));
		assertEquals(-20, list.get(1));
		assertThrows(IndexOutOfBoundsException.class,  () -> { list.get(33); });
	}
	
	@Test
	void setTest() 
	{
		Integer [] expected = {1,-20, 8, 14, 30, 12, 100};
		Integer [] expected2 = {1,-20, 8, 17, 30, 12, 100};// меняет обьет по индексу в массиве
		list.set(0, 1);
		assertArrayEquals(expected, list.toArray(new Integer[0]));
		list.set(3, 17);
		assertArrayEquals(expected2, list.toArray(new Integer[0]));
		assertThrows(IndexOutOfBoundsException.class,  () -> { list.set(-3, 17); });
	}
	
	@Test
	void removeByIndexTest() 
	{
		Integer [] expected = {10, -20, 30, 12, 100};
		list.remove(2);
		list.remove(2);
		assertArrayEquals(expected, list.toArray(new Integer[0]));
		assertThrows(IndexOutOfBoundsException.class,  () -> { list.remove(-1); });
	}
	
	@Test
	void removeByPaternTest() 
	{
		Integer [] expected = {10,-20, 8, 14, 12, 100};
		list.remove(4);
		assertArrayEquals(expected, list.toArray(new Integer[0]));
	}
	
	@Test
	void containtListTest() 
	{
		assertTrue(list.contains(100));
		assertFalse(list.contains(110));
	}
	

}
