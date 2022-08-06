package duongbanhat;

public class Node<T> {
   private T product;
   private Node<T> next;

   //1. contructor
    public Node(T product, Node<T> next) {
        this.product = product;
        this.next = next;
    }

    public Node(T product) {
        this(product, null);
    }

    public Node() {
        this(null, null);
    }
    
    
    //2. setter
    public void setProduct(T product) {
        this.product = product;
    }

    
    public void setNext(Node<T> next) {
        this.next = next;
    }
    
    //3. getter
    public T getProduct() {
        return product;
    }

    public Node<T> getNext() {
        return next;
    }
    
    //. toString
    @Override
    public String toString() {
        return this.product.toString();
    }
    
   
           
}
