public class LinkedListDeque<T> {
    public class ListNode {
        public T item;
        public ListNode n;
        public ListNode p;

        public ListNode(T item, ListNode n, ListNode p){
            this.item = item;
            this.n = n;
            this.p = p;
        }
    }

    public ListNode sentinel;
    public int length;

    public LinkedListDeque( ){
        sentinel = new ListNode(null, null, null);
        sentinel.n = sentinel;
        sentinel.p = sentinel;
        length = 0;
    }

    public void addFirst(T item){
        sentinel.n = new ListNode(item, sentinel.n, sentinel);
        sentinel.n.n.p = sentinel.n;
        length++;
    }

    public void addLast(T item){
        sentinel.p = new ListNode(item, sentinel, sentinel.p);
        sentinel.p.p.n = sentinel.p;
        length++;
    }

    public boolean isEmpty(){
        if (sentinel.n == sentinel){
            return true;
        }
        return false;
    }

    public int size(){
        return length;
    }

    public void printDeque(){
        ListNode q = sentinel.n;
        while (q != sentinel){
            System.out.print(q.item + " ");
            q = q.n;
        }
    }

    public T removeFirst(){
        ListNode q = sentinel.n;
        sentinel.n = q.n;
        q.n.p = sentinel;
        return q.item;
    }

    public T removeLast(){
        ListNode q = sentinel.p;
        sentinel.p = q.p;
        q.p.n = sentinel;
        return q.item;
    }

    public T get(int index){
        ListNode q = sentinel.n;
        int i = 0;
        if (index >= this.size() || index < 0){
            return null;
        }
        while (i < index){
            q = q.n;
            i++;
        }
        return q.item;
    }

    public T getRecursive(int index){
        if (index >= this.size() || index < 0){
            return null;
        }else if (index == 0){
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
