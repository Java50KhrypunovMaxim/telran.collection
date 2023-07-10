package telran.util;

import java.util.function.Predicate;

public interface List<T> extends Collection<T> 
{
 void add(int index, T obj);
 T get (int index);
 T set (int index, T obj);
 T remove (int index);
 int indexOf (T pattern);
 int lastIndexOf(T pattern);
 int indexOf (Predicate<T> predicate);
 int lastIndexOf (Predicate<T> predicate);
 
 @Override
 default public boolean contains (Object pattern)
 {
	 return indexOf((T)pattern) >= 0;
 }
 
 @Override
	default public boolean remove(Object pattern) {
		int index = indexOf((T)pattern);
		boolean res = false;
		if (index >= 0) {
			res = true;
			remove(index);
		}
		return res;
	}
 
 default public void indexValidation(int index, boolean sizeInclusive) {
		int size = size();
	 	int bounder = sizeInclusive ? size : size - 1;
		if (index < 0 || index > bounder ) {
			throw new IndexOutOfBoundsException(index);
		}
}
}

