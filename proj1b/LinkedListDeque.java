public class LinkedListDeque<T> implements Deque<T> {
	private class ListNode {
		private T item;
		private ListNode n;
		private ListNode p;

		public ListNode(T item, ListNode n, ListNode p) {
			this.item = item;
			this.n = n;
			this.p = p;
		}
	}

	private ListNode sentinel;
	private int length;

	public LinkedListDeque() {
		sentinel = new ListNode(null, null, null);
		sentinel.n = sentinel;
		sentinel.p = sentinel;
		length = 0;
	}

	@Override
	public void addFirst(T item) {
		sentinel.n = new ListNode(item, sentinel.n, sentinel);
		sentinel.n.n.p = sentinel.n;
		length++;
	}

	@Override
	public void addLast(T item) {
		sentinel.p = new ListNode(item, sentinel, sentinel.p);
		sentinel.p.p.n = sentinel.p;
		length++;
	}

	@Override
	public boolean isEmpty() {
		return length == 0;
	}

	@Override
	public int size() {
		return length;
	}

	@Override
	public void printDeque() {
		ListNode q = sentinel.n;
		while (q != sentinel) {
			System.out.print(q.item + " ");
			q = q.n;
		}
	}

	@Override
	public T removeFirst() {
		if (size() >= 1) {
			ListNode q = sentinel.n;
			sentinel.n = q.n;
			q.n.p = sentinel;
			length--;
			return q.item;
		} else {
			return null;
		}
	}

	@Override
	public T removeLast() {
		if (size() >= 1) {
			ListNode q = sentinel.p;
			sentinel.p = q.p;
			q.p.n = sentinel;
			length--;
			return q.item;
		} else {
			return null;
		}
	}

	@Override
	public T get(int index) {
		if (index >= this.size() || index < 0) {
			return null;
		}
		ListNode q = sentinel.n;
		int i = 0;
		while (i < index) {
			q = q.n;
			i++;
		}
		return q.item;
	}

	public T getRecursive(int index) {
		if (index >= this.size() || index < 0) {
			return null;
		} else if (index == 0) {
			return sentinel.n.item;
		}
		LinkedListDeque<T> newDeque = new LinkedListDeque<>();
		newDeque.sentinel.n = this.sentinel.n.n;
		newDeque.length = this.size() - 1;
		return newDeque.getRecursive(index - 1);
	}


//    public static void main(String[] args){
//        LinkedListDeque<Double> testDeque = new LinkedListDeque<>();
//        testDeque.addFirst(3.14);
//        testDeque.addLast(6.28);
//        System.out.println(testDeque.isEmpty());
//        testDeque.printDeque();
//        Double t000 = testDeque.get(1);
//        Double t111 = testDeque.getRecursive(1);
//    }
}
