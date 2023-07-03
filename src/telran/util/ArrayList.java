package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size = 0;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T obj) {
		if (size == array.length) {
			reallocate();
		}
		array[size++] = obj;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);

	}

	@Override
	public boolean remove(Object pattern) {
		boolean flag = false;
		int index = indexOf((T) pattern);
		if (index > -1) {
			flag = true;
			remove(index);
		}
		return flag;
	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		System.arraycopy(array, 0, ar, 0, size);
		if (ar.length > size) {
			ar[size] = null;
		}

		return ar;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {

		int newSize = size;
		int i = 0;
		while(i < size) {
			if(predicate.test(array[i])) {
				remove(i);
			} else {
				i++;
			}
		}
//		for (int i = size - 1; i > 0; i--) {
//			if (predicate.test(array[i])) {
//				remove(i);
//			}
//		}
		return newSize > size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean addAll(Collection<T> collection) {
		while ((collection.size() + size) > array.length) {
			reallocate();
		}
		int oldSize = size;
		T[] collectionArray = (T[]) new Object[collection.size()];
		collectionArray = collection.toArray(collectionArray);
		int index = 0;
		while (index < collectionArray.length) {
			array[size++] = collectionArray[index++];
		}
		return oldSize < size;
	}

	@Override
	public boolean removeAll(Collection<T> collection) {
		int oldsize = size;
		T[] collectionOfNewArray = (T[]) new Object[collection.size()];
		collectionOfNewArray = collection.toArray(collectionOfNewArray);
		for (T object : collectionOfNewArray) {
			int index = indexOf(object);
			if (index != -1) {
				remove(index);
				size--;
			}
		}
		return size < oldsize;
	}

	@Override
	public Iterator<T> iterator() {

		return new ArListIterator();
	}

	private class ArListIterator implements Iterator<T> {
		int current = 0;
		boolean flagForNext = false;

		@Override
		public boolean hasNext() {

			return current < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			flagForNext = true;
			return array[current++];
		}

		@Override
		public void remove() {
			if (!flagForNext) {
				throw new IllegalStateException();
			}
			flagForNext = false;
			ArrayList.this.remove(--current);
		}
	}

	@Override
	public void add(int index, T obj) {
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		T res = array[index];
		return res;
	}

	@Override
	public T set(int index, T obj) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		T temperary = array[index];
		array[index] = obj;
		return temperary;
	}

	@Override
	public T remove(int index) {
		indexValidation(index, false);
		T res = array[index];
		size--;
		System.arraycopy(array, 0, array, 0, index);
		System.arraycopy(array, index + 1, array, index, size - index);
		array[size] = null;
		return res;
	}

	private void indexValidation(int index, boolean sizeInclusive) {
		int bounder = sizeInclusive ? size : size - 1;
		if (index < 0 || index > bounder) {
			throw new IndexOutOfBoundsException(index);
		}

	}

	@Override
	public int indexOf(T pattern) {
		int result = -1;
		for (int index = 0; index < size; index++) {
			if (isEqual(array[index], pattern)) {
				result = index;
				break;
			}
		}
		return result;
	}

	private boolean isEqual(T object, T pattern) {

		return pattern == null ? object == pattern : pattern.equals(object);
	}

	@Override
	public int lastIndexOf(T pattern) {
		int result = -1;
		if (pattern == null) {
			for (int i = size - 1; i >= 0; i--) {
				if (array[i] == null)
					result = i;
			}
		} else {
			for (int i = size - 1; i >= 0; i--) {
				if (pattern.equals(array[i]))
					result = i;
			}
		}
		return result;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		int result = -1;
		int index = 0;
		while (index < size && result == -1) {
			if (predicate.test(array[index])) {
				result = index;
			}
			index++;
		}
		return result;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		int res = -1;
		int index = size - 1;
		while (index >= 0 && res == -1) {
			if (predicate.test(array[index])) {
				res = index;
			}
			index--;
		}
		return res;
	}

}