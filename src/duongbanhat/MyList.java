package duongbanhat;

public class MyList<T> {

    private Node<T> head;
    private Node<T> tail;

    //1. contructor
    public MyList(Node<T> head, Node<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public MyList(Node<T> head) {
        this(head, null);
    }

    public MyList() {
        this.head = this.tail = null;
    }

    //2. isEmpty
    public boolean isEmpty() {
        return (this.head == null);
    }

    //3. addHead
    public void addHead(T product) {
        Node<T> newNode = new Node<>(product);
        if (this.isEmpty() == true) {
            this.head = this.tail = newNode;
        } else {
            newNode.setNext(this.head);
            this.head = newNode;
        }
    }

    //4. addTail
    public void addTail(T product) {
        Node<T> newNode = new Node<>(product);
        if (this.isEmpty() == true) {
            this.head = this.tail = newNode;
        } else {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
    }

    //5. insert
    public void addAfter(int position, T product) {
        Node<T> newNode = new Node<>(product);
        Node<T> current = this.head;
        if (current == null) {
            this.addHead(product);
        } else {
            int count = 0;
            while (current != null) {
                if (count == position) {
                    Node<T> nextNode = current.getNext();

                    newNode.setNext(nextNode);
                    current.setNext(newNode);
                    break;
                }

                current = current.getNext();
                count++;
            }
        }

    }

    //6. length - size
    public int length() {
        int count = 0;
        Node<T> current = this.head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    //7. deleteHead
    public void deleteHead() {

        Node<T> current = this.head;
        if (this.isEmpty() == false) {
            this.head = this.head.getNext();
            current.setNext(null);
        }
    }

    //8. deleteTail
    public void deleteTail() {
        Node<T> current = this.head;
        Node<T> previous = null;
        if (this.isEmpty() == false) {
            while (current.getNext() != null) {
                previous = current;
                current = current.getNext();
            }
            if (current == this.head) {
                this.deleteHead();
            } else {
                previous.setNext(null);
                this.tail = previous;
            }
        }
    }

    //9. deleteElement
    public void deleteElement(T product) {
        if (this.isEmpty() == true) {
            return;
        }

        Node<T> previous = this.head;
        Node<T> current = this.head;
        while (current != null) {
            if (current.getProduct().equals(product)) {
                if (current == this.head) {
                    this.deleteHead();
                    current = this.head;
                } else if (current == this.tail) {
                    this.deleteTail();
                    current = this.tail;
                } else {
                    previous.setNext(current.getNext());
                    current.setNext(null);
                    current = previous;
                }
            }
            previous = current;
            current = current.getNext();
        }
    }

    //10. clear
    public void clear() {
        this.head = this.tail = null;
    }

    //11. swap
    public void swap(Node<T> node1, Node<T> node2) {
        T temp;
        temp = node1.getProduct();
        node1.setProduct(node2.getProduct());
        node2.setProduct(temp);
    }

    //12. getter
    public Node<T> getHead() {
        return head;
    }

    //13. contains
    public boolean contains(T product) {
        boolean check = false;
        Node<T> current = this.head;
        while (current != null) {
            if (current.getProduct().equals(product)) {
                check = true;
                break;
            }
            current = current.getNext();
        }
        return check;
    }

}
