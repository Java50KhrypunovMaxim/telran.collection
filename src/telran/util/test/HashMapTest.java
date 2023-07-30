package telran.util.test;


import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import telran.collection.Collection;
import telran.collection.HashMap;
import telran.collection.Map.Entry;
import telran.collection.Set;

public class HashMapTest extends MapTest {
@BeforeEach
@Override
void setUp() {
	map = new HashMap<>();
	super.setUp();
}



@Override
protected String[] getKeysActual(String[] keys) {
	Arrays.sort(keys);
	return keys;
}

@Override
protected Entry<String, Integer>[] getEntriesActual(Entry<String, Integer>[] entries) {
	Arrays.sort(entries);
	return entries;
}
}