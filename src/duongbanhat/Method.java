package duongbanhat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Method {

    //1. Nhap so tu ban phim
    public static double inputNumber() {
        double number;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                number = Double.parseDouble(sc.nextLine());
                return number;
            } catch (NumberFormatException e) {
                String s = "Bạn cần nhập sô: ";
                System.out.print(s);
                Method.writeOutToFile(s);
            }
        }
    }

    //Nhập số lượng
    public static String inputS(String type, MyList<Product> list) {
        String result;
        String s;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                result = sc.nextLine().trim();
                if (result.equals("") == false) {
                    if (type.equalsIgnoreCase("id") && checkId(result, list) == false) {
                        return result;
                    }
                    if (type.equalsIgnoreCase("name")) {
                        return result;
                    }
                }

                s = "Input " + type + ": ";
                System.out.print(s);
                Method.writeOutToFile(s);

            } catch (Exception ex) {
                s = "Input " + type + ": ";
                System.out.print(s);
                Method.writeOutToFile(s);
            }
        }
    }

    public static double inputNumber(String type) {
        double number;
        String s;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                number = Double.parseDouble(sc.nextLine());
                if (type.equalsIgnoreCase("quantity") && number > 0 && (int) number == number) {
                    return (int) number;
                }
                if (type.equalsIgnoreCase("price") && number > 0) {
                    return number;
                }
                if (type.equalsIgnoreCase("int") && (int) number == number) {
                    return (int)number;
                }

                if (type.equalsIgnoreCase("float") && (float) number == number) {
                    return (float)number;
                }
                if (type.equalsIgnoreCase("double")) {
                    return number;
                }
                
                s = "Input " + type + ": ";
                System.out.print(s);
                Method.writeOutToFile(s);

            } catch (NumberFormatException e) {
                s = "Input " + type + ": ";
                System.out.print(s);
                Method.writeOutToFile(s);
            }
        }

    }

    //Tim kiếm
    private static boolean checkNumber(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (Character.isDigit(i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkId(String id, MyList<Product> list) {
        Node<Product> current = list.getHead();
        while (current != null) {
            if (current.getProduct().getId().equalsIgnoreCase(id)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    //3. Nhap id - kiem tra trung
    static String inputId(MyList<Product> list) {
        Scanner sc = new Scanner(System.in);
        String id = "";
        boolean check = false;
        while (check == false) {
            id = sc.nextLine();

            String s = id + "\n";
            Method.writeOutToFile(s);

            Node<Product> current = list.getHead();
            check = true;
            while (current != null) {
                if (current.getProduct().getId().equalsIgnoreCase(id)) {
                    check = false;

                    s = "Id already exists !\nInput id: ";
                    System.out.print(s);
                    Method.writeOutToFile(s);

                    break;
                }

                current = current.getNext();
            }

        }
        return id;
    }

    //4. Sap xep Selection sort dung de quy
    public static void recursionSort(Node<Product> current, Node<Product> next,
            MyList<Product> list) {

        //Khi current chạy đến cuối thì dừng (vì chỉ còn 1 node, đương nhiên nó nhỏ nhất)
        if (current.getNext() == null) {
            return;
        } else if (next == null) {
            //Khi next chạy đến cuối thì tăng current lên 1 node
            //Next lại chạy từ current đến cuối.
            current = current.getNext();
            next = current;
        }

        //Nếu next < current thì hoán đổi giá trị
        if (next.getProduct().compareTo(current.getProduct()) < 0) {
            list.swap(current, next);
        }

        //tăng next lên 1 node (current giữ nguyên)
        recursionSort(current, next.getNext(), list);

    }

    //5. Hien thi tat ca stack
    public static void displayAll(MyStack<Product> stack) {
        try {
            displayTitle();
            Node<Product> current = stack.peek();

            while (current != null) {
                current.getProduct().displayProduct();
                current = current.getNext();
            }
        } catch (Exception ex) {
            Logger.getLogger(Method.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //6. Hien thi tat ca queue
    public static void displayAll(MyQueue<Product> queue) {
        try {
            displayTitle();

            Node<Product> current = queue.peek();
            while (current != null) {
                current.getProduct().displayProduct();
                current = current.getNext();
            }
        } catch (Exception ex) {
            Logger.getLogger(Method.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //7. Luu vao file
    public static void writeOutToFile(String... strings) {
        String fileName = "console_output.txt";
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            File file = new File(fileName);
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);

            for (String string : strings) {
                bw.write(string);
            }

        } catch (IOException ex) {
            Logger.getLogger(Method.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Method.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    //8. Tieu de san pham
    public static void displayTitle() {
        String s1 = String.format("%5s  %-5s|  %-10s|  %-10s|  %-10s\n",
                " ", "ID", "Name", "Quantity", "Price");

        String s2 = String.format("%5s%s\n", " ", "-------|------------|------------|------------");

        System.out.printf(s1 + s2);
        writeOutToFile(s1, s2);
    }

    //9. Tạo một sản phẩm mới
    public static Product createProduct(MyList<Product> list) {
        //1. id
        String s = "Input id: ";
        System.out.print(s);
        Method.writeOutToFile(s);

        String id = Method.inputS("id", list);
        Method.writeOutToFile(s);

        //2. Name
        s = "Input Name: ";
        System.out.print(s);
        Method.writeOutToFile(s);

        String name = Method.inputS("name", list);

        s = name + "\n";
        Method.writeOutToFile(s);

        //3. Quantity
        s = "Input quantity: ";
        System.out.print(s);
        Method.writeOutToFile(s);

        int quantity = (int) Method.inputNumber("quantity");

        s = quantity + "\n";
        Method.writeOutToFile(s);

        //4. Price
        s = "Input price: ";
        System.out.print(s);
        Method.writeOutToFile(s);

        double price = Method.inputNumber("price");

        s = price + "\n";
        Method.writeOutToFile(s);

        //5 create new Product
        Product newProduct = new Product(id, name, quantity, price);

        return newProduct;
    }

    // 10. Biểu diễn số lượng sản phẩm (đang ở hệ đếm cơ số 10)
    // của phần tử đầu tiên trong Linked List ra hệ hếm nhị phân bằng phương
    // pháp đệ quy.
    public static void convertBinary(int n, MyStack<Integer> stack) {
        if (n == 0 || n == 1) {
            stack.push(n);
            return;
        }
        stack.push(n % 2);
        convertBinary(n / 2, stack);
    }
}
