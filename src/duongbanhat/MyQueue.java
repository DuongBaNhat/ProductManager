package duongbanhat;

public class MyQueue<T> {

    private Node<T> head;
    private Node<T> tail;

    //1. contructor
    public MyQueue(Node<T> head, Node<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public MyQueue(Node<T> head) {
        this(head, null);
    }

    public MyQueue() {
        this.head = this.tail = null;
    }

    //2. isEmpty
    public boolean isEmpty() {
        return (this.head == null);
    }

    //3. enqueue
    public void enqueue(T product) {
        Node<T> newNode = new Node<>(product);
        if (this.isEmpty() == true) {
            this.head = this.tail = newNode;
        } else {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
    }

    //4. dequeue
    public Node<T> dequeue() throws Exception {
        if (this.isEmpty() == true) {
            throw new Exception();
        }

        Node<T> current = this.head;
        this.head.setNext(this.head.getNext());

        current.setNext(null);
        return current;
    }

    //5. peek
    public Node<T> peek() throws Exception {
        if (this.isEmpty() == true) {
            throw new Exception();
        }
        return (this.head);
    }

    //6. clear
    public void clear()
    {
        this.head = this.tail = null;
    }
    //toString
    @Override
    public String toString() {
        String s = "";
        Node<T> current = this.head;
        while (current != null) {
            s = current.toString() + " ";
        }
        return s;
    }

}
