package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashSet<T> implements Set<T> {
	private static final int DEFAULT_TABLE_LENGTH = 16;
	private LinkedList<T>[] hashTable;
	private float factor = 0.75f;
	private int size;
	
	@SuppressWarnings("unchecked")
	public HashSet(int tableLength) {
		hashTable = new LinkedList[tableLength];
	}
	public HashSet() {
		this(DEFAULT_TABLE_LENGTH);
	}
	@Override
	public boolean add(T obj) {
		if ((float)size / hashTable.length >= factor) {
			hashTableRecreation();
		}
		int index = getIndex(obj);
		LinkedList<T> list = null;
		if (hashTable[index] == null) {
			hashTable[index] = new LinkedList<>();
		}
		list = hashTable[index];
		boolean res = false;
		if(!list.contains(obj)) {
			res = true;
			list.add(obj);
			size++;
		}
		return res;
	}

	private void hashTableRecreation() {
		HashSet<T> tmp = new HashSet<>(hashTable.length * 2);
		for(LinkedList<T> list: hashTable) {
			if(list != null) {
				for(T obj: list) {
					tmp.add(obj);
				}
			}
		};
		
		hashTable = tmp.hashTable;
		
	}
	private int getIndex(Object obj) {
		int hashCode = obj.hashCode();
		
		return Math.abs(hashCode) % hashTable.length;
	}
	@Override
	public boolean remove(Object pattern) {
		int index = getIndex(pattern);
		boolean res = false;
		LinkedList<T> list = hashTable[index];
		if(list != null) {
			res = list.remove(pattern);
			if(res) {
				size--;
			}
		}
		return res;
	}

	@Override
	public boolean contains(Object pattern) {
		int index = getIndex(pattern);
		boolean res = false;
		LinkedList<T> list = hashTable[index];
		if(list != null) {
			res = list.contains(pattern);
		}
		return res;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new HashSetIterator();
	}
	
	
	private class HashSetIterator implements Iterator<T> {
		Integer error—ode = -1;
		Integer currentIndex;
		Iterator<T> currentIterator;
		Iterator<T> prevIterator;
		boolean flNext = false;
		HashSetIterator() 
		{
			currentIndex = getCurrentIteratorIndex(error—ode);	
			currentIterator = hashTable[currentIndex].iterator();
		}
		
		private int getCurrentIteratorIndex(int currentIndex) {
			currentIndex++;
			while(currentIndex < hashTable.length && 
					(hashTable[currentIndex] == null || hashTable[currentIndex].size() == 0)) {
				currentIndex++;
			}
			return currentIndex < hashTable.length ? currentIndex : error—ode;
		}
		
		
		@Override
		public boolean hasNext() {
			
			return currentIndex >= 0;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = currentIterator.next();
			prevIterator = currentIterator;
			if(!currentIterator.hasNext()) {
				currentIndex =
						getCurrentIteratorIndex(currentIndex);
				if(currentIndex >= 0) {
					currentIterator = hashTable[currentIndex].iterator();}}
			flNext = true;
			return res;
		}
			
		@Override
		public void remove() {
			if(!flNext) {
				throw new IllegalStateException();
			}
			prevIterator.remove();
			size--;
			flNext = false;
		}
	}

	@Override
	public T get(Object pattern) {
		int index = getIndex(pattern);
		T res = null;
		LinkedList<T> list = hashTable[index];
		if(list != null) {
			Iterator<T> it = list.iterator();
			while(it.hasNext() && res == null) {
				T obj = it.next();
				if(Objects.equals(pattern, obj)) {
					res = obj;
				};
			}
		}
		return res;
	}

}