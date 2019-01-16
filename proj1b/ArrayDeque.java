public class ArrayDeque<T> implements Deque<T> {
	private T[] items;
	private int size;
	private int front;
	private int rear;

	public ArrayDeque() {
		items = (T[]) new Object[8];
		size = 0;
		front = 0;
		rear = 0;
	}

	private void resize() {
		if (size == items.length) {
			T[] newItems = (T[]) new Object[2 * items.length];
			System.arraycopy(items, front, newItems, 0, size - front);
			System.arraycopy(items, 0, newItems, size - front, rear);
			items = newItems;
		} else if (size < 0.25 * items.length && items.length >= 16) {
			T[] newItems = (T[]) new Object[items.length / 2];
			if (front < rear) {
				System.arraycopy(items, front, newItems, 0, rear - front);
			} else {
				System.arraycopy(items, front, newItems, 0, items.length - front);
				System.arraycopy(items, 0, newItems, items.length - front, rear);
			}
			items = newItems;
		}
		front = 0;
		rear = size();
	}

	@Override
	public void addFirst(T item) {
		if (items.length == size) {
			resize();
		}
		front = (front - 1 + items.length) % items.length;
		items[front] = item;
		size++;
	}

	@Override
	public void addLast(T item) {
		if (items.length == size) {
			resize();
		}
		items[rear] = item;
		rear = (rear + 1) % items.length;
		size++;
	}

	@Override
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void printDeque() {
		int i = front;
		if (front <= rear) {
			while (i < rear) {
				System.out.print(items[i] + " ");
				i = (i + 1) % items.length;
			}
		} else {
			while (i < items.length) {
				System.out.print(items[i] + " ");
				i = i + 1;
			}
			i = 0;
			while (i < rear) {
				System.out.print(items[i] + " ");
				i++;
			}
		}
	}

	@Override
	public T removeFirst() {
		if (size < 0.25 * items.length && items.length >= 16) {
			resize();
		}
		if (size < 1) {
			return null;
		}
		T result = items[front];
		items[front] = null;
		front = (front + 1) % items.length;
		size--;
		return result;
	}

	@Override
	public T removeLast() {
		if (size < 0.25 * items.length && items.length >= 16) {
			resize();
		}
		if (isEmpty()) {
			return null;
		}
		T result = items[(rear - 1 + items.length) % items.length];
		rear = (rear - 1 + items.length) % items.length;
		items[rear] = null;
		size--;
		return result;
	}

	@Override
	public T get(int index) {
		if (index >= this.size() || index < 0) {
			return null;
		}
		return items[(index + front) % items.length];
	}

//    public static void main(String[] args) {
//        ArrayDeque<Integer> FuckDeque = new ArrayDeque<>();
//        FuckDeque.addFirst(0);
//		FuckDeque.removeLast();
//        FuckDeque.addFirst(2);
//        Integer ttt = FuckDeque.get(0);
//        int i = 0;
//        while (i < 100) {
//            FuckDeque.addFirst(i * 1.2);
//            FuckDeque.addLast(-i * 1.5);
//            i++;
//        }
//        while (i<100) {
//            FuckDeque.removeLast();
//            FuckDeque.removeFirst();
//            i++;
//        }
//    }
}
