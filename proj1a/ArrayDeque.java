public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int front;
    private int rear;

    public ArrayDeque (){
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        rear = 0;
    }

    private void resize(){
        if (size == items.length){
            T[] newItems = (T[]) new Object[2*size];
            System.arraycopy(items, front, newItems, 0, size - front);
            System.arraycopy(items, 0, newItems, size - front, rear);
            front = 0;
            rear = size();
            items = newItems;
        }else if (size < 0.25*items.length && items.length >= 16){
            T[] newItems = (T[]) new Object[size/2];
            if (rear > front){
                System.arraycopy(items, front, newItems, 0, rear - front);
                front = 0;
                rear = size();
                items = newItems;
            }else{
                System.arraycopy(items, front, newItems, 0, items.length - front);
                System.arraycopy(items, 0, newItems, items.length - front, rear);
                front = 0;
                rear = size();
                items = newItems;
            }
        }
    }

    public void addFirst(T item){
        if (items.length == size){
            resize();
        }
        items[(front - 1 + items.length) % items.length] = item;
        front = (front - 1 + items.length) % items.length;
        size++;
    }

    public void addLast(T item){
        if (items.length == size){
            resize();
        }
        items[rear] = item;
        rear = (rear + 1) % items.length;
        size++;
    }

    public boolean isEmpty(){
        if (size() == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int i = front;
        while (i % items.length < rear){
            System.out.print(items[i] + " ");
            i = (i + 1) % items.length;
        }
    }

    public T removeFirst(){
        if (size < 0.25*items.length && items.length >= 16){
            resize();
        }
        if (size < 1){
            return null;
        }
        T result = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        size--;
        return result;
    }

    public T removeLast(){
        if (size < 0.25*items.length && items.length >= 16){
            resize();
        }
        if (isEmpty()){
            return null;
        }
        T result = items[(rear - 1 + items.length) % items.length];
        items[(rear - 1 + items.length) % items.length] = null;
        rear = (rear - 1 + items.length) % items.length;
        size--;
        return result;
    }

    public T get(int index){
        if (index >= this.size() || index < 0){
            return null;
        }
        return items[index];
    }

//    public static void main(String[] args){
//        ArrayDeque<Double> FuckDeque = new ArrayDeque<>();
//        FuckDeque.addFirst(5.5);
//        FuckDeque.addLast(6.6);
//        FuckDeque.addFirst(7.7);
//        int i = 0;
//        while (i < 100){
//            FuckDeque.addFirst(i*1.2);
//            FuckDeque.addLast(-i*1.5);
//            i++;
//        }
//        while (i<100){
//            FuckDeque.removeLast();
//            FuckDeque.removeFirst();
//            i++;
//        }
//    }
}
