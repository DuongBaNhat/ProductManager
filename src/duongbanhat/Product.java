package duongbanhat;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Product implements Comparable<Product>{

    private String id;
    private String name;
    private int quantity;
    private double price;

    //1. contructor
    public Product(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product() {
        this(null, null, 0, 0.0);
    }

    //2. setter
    public void setId(String id) {
        this.id = id;
    }
    //3. getter

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    //4. toString
    //5. equals
    @Override
    public boolean equals(Object o) {
        if ((o == null) || this.getClass().equals(o.getClass()) == false) {
            return false;
        } else {
            Product p = (Product) o;
            return (this.id.equalsIgnoreCase(p.getId()) == true
                    && this.name.equals(p.getName()) == true
                    && this.quantity == p.getQuantity()
                    && this.price == p.getPrice());
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + this.quantity;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        return hash;
    }

    //ToString
    @Override
    public String toString() {
        String s = this.id + ";" + this.name + ";" + 
                this.quantity + ";" + this.price + "\n";
        return s;
    }
    
    //display
    public void displayProduct()
    {
        Locale locale = new Locale("vn", "VN");
        NumberFormat nf = NumberFormat.getInstance(locale);
        String s = String.format("%5s  %-5s|  %-10s|  %-10d|  %-10s\n", " ", 
                this.id, this.name, this.quantity, nf.format(this.price));
        
        System.out.print(s);
        Method.writeOutToFile(s);
    }
    
    //CompareTo
    @Override
    public int compareTo(Product o) {
        return this.getId().compareToIgnoreCase(o.getId());
    }
}
