package telran.strings.test;

import static org.junit.jupiter.api.Assertions.*;
import static telran.strings.Strings.*;

import org.junit.jupiter.api.Test;

class StringsTest {

	@SuppressWarnings("unused")
	@Test
	void javaVariableTrueTest() {
		String regex = javaVariable();
		assertTrue("a".matches(regex));
		assertTrue("$".matches(regex)); int $;
		assertTrue("a123".matches(regex));
		assertTrue("__".matches(regex)); int __;
		assertTrue("_$".matches(regex)); int _$;
		assertTrue("$_".matches(regex)); int $_;
		assertTrue("aA".matches(regex));
		assertTrue("Aa".matches(regex));
		
	}
	@Test
	void javaVariableFalseTest() {
		String regex = javaVariable();
		assertFalse("1a".matches(regex));
		assertFalse("123".matches(regex));
		assertFalse("a&c".matches(regex));
		assertFalse("a C".matches(regex));
		
	}
	@Test
	void zero_300_true()
	{
		String regex = zero_300();
		assertTrue("0".matches(regex));
		assertTrue("10".matches(regex));
		assertTrue("9".matches(regex));
		assertTrue("100".matches(regex));
		assertTrue("199".matches(regex));
		assertTrue("299".matches(regex));
		assertTrue("300".matches(regex));
	}
	@Test
	void zero_300_false()
	{
		String regex = zero_300();
		assertFalse("01".matches(regex));
		assertFalse("301".matches(regex));
		assertFalse("1 0".matches(regex));
		assertFalse("1_0)".matches(regex));
		assertFalse("2.5".matches(regex));
		assertFalse("-1".matches(regex));
		assertFalse("a".matches(regex));
	}
	@Test
	void ipV4OctetTrueTest()
	{
		String regex = ipV4Octet();
		assertTrue("0".matches(regex));
		assertTrue("00".matches(regex));
		assertTrue("000".matches(regex));
		assertTrue("01".matches(regex));
		assertTrue("1".matches(regex));
		assertTrue("199".matches(regex));
		assertTrue("223".matches(regex));
		assertTrue("250".matches(regex));
		assertTrue("255".matches(regex));
	
	}
	@Test
	void ipV4OctetFalseTest()
	{
		String regex = ipV4Octet();
		assertFalse("a".matches(regex));
		assertFalse("-21".matches(regex));
		assertFalse("256".matches(regex));
		assertFalse("1_0".matches(regex));
		assertFalse("1000".matches(regex));
		assertFalse("300".matches(regex));
	}
	
	@Test
	void ipV4TrueTest()
	{
		String regex = ipV4Address();
		assertTrue("1.2.3.4".matches(regex));
		assertTrue("000.0.0.0".matches(regex));
		assertTrue("000.110.104.245".matches(regex));
	}
	@Test
	void ipV4TrueFalse()
	{
		String regex = ipV4Address();
		assertFalse("1.2.3.".matches(regex));
		assertFalse("000.0.0.a".matches(regex));
		assertFalse("100".matches(regex));
		assertFalse("1 2 3 4".matches(regex));
	}
	

}