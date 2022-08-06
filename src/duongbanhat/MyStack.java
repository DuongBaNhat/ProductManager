package duongbanhat;

public class MyStack<T> {

    private Node<T> head;

    //1. contructor
    public MyStack(Node<T> head) {
        this.head = head;
    }

    public MyStack() {
        this.head = null;
    }

    //2. isEmpty
    public boolean isEmpty() {
        return (this.head == null);
    }

    //3. push
    public void push(T product) {
        Node<T> newNode = new Node<>(product);
        if (this.isEmpty() == true) {
            this.head = newNode;
        } else {
            newNode.setNext(this.head);
            this.head = newNode;
        }
    }

    //4. pop
    public Node<T> pop() throws Exception {
        if (this.isEmpty() == true) {
            throw new Exception();
        }

        Node<T> current = this.head;
        this.head.setNext(this.head.getNext());
        return current;
    }

    //5. peek
    public Node<T> peek() throws Exception {
        if (this.isEmpty() == true) {
            throw new Exception();
        }
        return this.head;
    }

    
    //6. clear
    public void clear()
    {
        this.head = null;
    }

    // toString
    @Override
    public String toString() {
        String s = "";
        Node<T> current = this.head;
        while (current != null) {
            s += current.toString();
            current = current.getNext();
        }
        return s;
    }

}
