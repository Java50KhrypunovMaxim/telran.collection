package telran.util.test;
import telran.collection.Map.Entry;
import telran.collection.TreeMap;
import org.junit.jupiter.api.BeforeEach;

public class TreeMapTest extends MapTest {
	@BeforeEach
	@Override
	void setUp() {
		map = new TreeMap<>();
		super.setUp();
	}

	

	@Override
	protected String[] getKeysActual(String[] keys) {
		
		return keys;
	}

	@Override
	protected Entry<String, Integer>[] getEntriesActual(Entry<String, Integer>[] entries) {
		
		return entries;
	}
}