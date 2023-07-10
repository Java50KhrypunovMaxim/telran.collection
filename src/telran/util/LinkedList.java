package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	private static class Node<T> {
		T obj;
		Node<T> next;
		Node<T> prev;

		Node(T obj) {
			this.obj = obj;
		}
	}

	Node<T> head;
	Node<T> tail;
	int size;

	@Override
	public boolean add(T obj) {
		Node<T> node = new Node<>(obj);
		addNode(size, node);
		return true;
	}

	private void addNode(int index, Node<T> node) {

		if (index == size) {
			addTail(node);
		} else if (index == 0) {
			addHead(node);
		} else {
			addMiddle(index, node);
		}
		size++;

	}

	private void addMiddle(int index, Node<T> node) {

		Node<T> nextNode = getNode(index);
		Node<T> prevNode = nextNode.prev;
		node.next = nextNode;
		nextNode.prev = node;
		prevNode.next = node;
		node.prev = prevNode;

	}

	private void addHead(Node<T> node) {
		node.next = head;
		head.prev = node;
		head = node;

	}

	private void addTail(Node<T> node) {
		if (tail == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
		}
		tail = node;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public Iterator<T> iterator() {

		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;
		int currentIndex = 0;
		boolean isNext = false;

		@Override
		public boolean hasNext() {

			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			isNext = true;
			T currentObj = current.obj;
			current = current.next;
			currentIndex++;
			return currentObj;
		}

		@Override
		public void remove() {
			if (!isNext) {
				throw new IllegalStateException();
			}
			LinkedList.this.remove(--currentIndex);
			isNext = false;
		}
	}

	@Override
	public void add(int index, T obj) {
		indexValidation(index, true);
		Node<T> node = new Node<>(obj);
		addNode(index, node);

	}

	@Override
	public T get(int index) {
		indexValidation(index, false);
		Node<T> node = getNode(index);
		return node.obj;
	}

	private Node<T> getNode(int index) {

		return index < size / 2 ? getNodeFromHead(index) : getNodeFromTail(index);
	}

	private Node<T> getNodeFromTail(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromHead(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	@Override
	public T set(int index, T obj) {

		indexValidation(index, false);
		T oldValue = get(index);
		getNode(index).obj = obj;
		return oldValue;
	}

	@Override
	public T remove(int index) {
		indexValidation(index, false);
		Node<T> node = getNode(index);
		removeNode(node);
		return node.obj;
	}
	private void removeNode(Node<T> node) {
		if (node == head) {
			removeHead();
		} else if (node == tail) {
			removeTail();
		} else {
			removeMiddle(node);
		}
		size--;
	}

		private void removeHead() {
		Node<T> newHead = head.next;
		if(newHead != null)
		{
			newHead.prev = null;
		}
		head.next = null;
		head = newHead;
		
	}

			private void removeTail() {
			Node<T> newTail = tail.prev;
			if (newTail != null) {
				newTail.next = null;
			}
			tail.prev = null;
			tail = newTail;
}

		private void removeMiddle(Node<T> node) 
			{
			Node<T> beforeNode = node.prev;
			Node<T> afterNode =  node.next;
			beforeNode.next = afterNode;
			afterNode.prev = beforeNode;
			node.prev = null;
			node.next = null;
			}

	@Override
	public int indexOf(T pattern) {
		return indexOf(obj -> isEqual(obj, pattern));
	}

	@Override
	public int lastIndexOf(T pattern) {

		return lastIndexOf(obj -> isEqual(obj, pattern));
	}

	private boolean isEqual(T object, T pattern) {

		return pattern == null ? object == pattern : pattern.equals(object);
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		int index = 0;
		Node<T> current = head;
		while (current != null && !predicate.test(current.obj)) {
			current = current.next;
			index++;
		}
		return current == null ? -1 : index;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		int index = size - 1;
		Node<T> current = tail;
		while (current != null && !predicate.test(current.obj)) {
			current = current.prev;
			index--;
		}
		return current == null ? -1 : index;
	}


}
