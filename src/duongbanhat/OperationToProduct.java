package duongbanhat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OperationToProduct {

    public OperationToProduct() {
    }

    // 1. Đọc dữ liệu từ file Data.txt, dữ liệu sẽ được tự
    // động lưu vào danh sách móc nối theo thứ tự phía đuôi
    public void getAllItemsFromFile(String fileName, MyList<Product> list) {

        //lam rong list de khong ghi lap lai
        list.clear();

        //Doc file
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String s = br.readLine();
            while (s != null) {

                //1. nhap thong tin san pham tu file
                String[] arr = s.split(";");
                String id = arr[0];
                String name = arr[1];
                int quantity = Integer.parseInt(arr[2]);
                double price = Double.parseDouble(arr[3]);

                //2. tao san pham moi
                Product product = new Product(id, name, quantity, price);

                //3. them vao cuoi list
                list.addTail(product);

                //4. Doc dong tiep theo
                s = br.readLine();
            }
            //5. dong cac con tro
            br.close();
            fr.close();

        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        }

        //Hien thi list ra man hinh
        this.displayAll(list);

    }

    // 2. Nhập và thêm một sản phẩm vào cuối danh sách móc nối
    public void addLast(MyList<Product> list) {
        Product newProduct = Method.createProduct(list);
        String s;
        if (list.contains(newProduct) == false) {
            list.addTail(newProduct);

            s = "\nSuccessfully !\n";
            System.out.print(s);
            Method.writeOutToFile(s);
        } else {
            s = "\nProduct already exists !\n";
            System.out.print(s);
            Method.writeOutToFile(s);
        }
    }

    //3. Hiển thị thông tin của các sản phẩm trong danh sách móc nối 
    public void displayAll(MyList<Product> list) {
        Method.displayTitle();

        Node<Product> current = list.getHead();
        while (current != null) {
            current.getProduct().displayProduct();
            current = current.getNext();
        }

        String s = "\nSuccessfully!\n";
        System.out.print(s);
        Method.writeOutToFile(s);
    }

    //4.  Lưu danh sách móc nối vào tệp 
    public void writeAllItemsToFile(String fileName, MyList<Product> list) {

        File file = new File(fileName);
        try ( FileWriter fw = new FileWriter(file, false);  BufferedWriter bw = new BufferedWriter(fw)) {

            Node<Product> current = list.getHead();
            while (current != null) {
                //1. chuyen thong tin san pham thang chuoi
                String s = current.getProduct().toString();

                //2. luu chuoi vao file
                bw.write(s);

                //3. chuyen qua node tiep theo trong list
                current = current.getNext();
            }

            bw.close();
            fw.close();

            String s = "\nSuccessfully !\n";
            System.out.print(s);
            Method.writeOutToFile(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //5. Tìm kiếm thông tin của sản phẩm theo ID
    public void searchByCode(MyList<Product> list) {
        Scanner sc = new Scanner(System.in);

        //1. Nhap id tim kiem
        String s = "Input the ID to search: ";
        System.out.print(s);
        Method.writeOutToFile(s);

        String id = sc.nextLine();

        s = id + "\n";
        Method.writeOutToFile(s);

        //2. Tìm kiếm
        Node<Product> current = list.getHead();
        while (current != null) {
            if (current.getProduct().getId().equalsIgnoreCase(id) == true) {
                break;
            }
            current = current.getNext();
        }
        if (current == null) {
            s = "Result: -1\n";
            System.out.print(s);
            Method.writeOutToFile(s);
        } else {

            String s1 = "Result:\n";
            System.out.print(s1);
            Method.writeOutToFile(s1);

            //Hiển thị sản phẩm tìm thấy (tiêu đề và thông tin sản phẩm)
            Method.displayTitle();
            current.getProduct().displayProduct();

            s = "\nSuccessfully !\n";
            System.out.print(s);
            Method.writeOutToFile(s);
        }

    }

    //6. Xóa sản phẩm theo ID trong ds móc nối
    public void deleteByCode(MyList<Product> list) {
        Scanner sc = new Scanner(System.in);

        //Nhap id de xoa
        String s = "Input the id to delete: ";
        System.out.print(s);
        Method.writeOutToFile(s);

        String id = sc.nextLine();

        s = id + "\n";
        Method.writeOutToFile(s);

        //2. tim kiem
        Node<Product> current = list.getHead();
        while (current != null) {
            if (current.getProduct().getId().equalsIgnoreCase(id)) {
                break;
            }
            current = current.getNext();
        }

        if (current == null) {
            s = "\nProduct is not exists !\n";
            System.out.print(s);
            Method.writeOutToFile(s);
        } else {
            list.deleteElement(current.getProduct());
            s = "\nDeleted !\n";
            System.out.print(s);
            Method.writeOutToFile(s);
        }
    }

    //7. Sắp xếp các sản phẩm trong ds móc nối theo ID(đệ quy)
    public void sortByCode(MyList<Product> list) {
        if (list.isEmpty() == true) {
            return;
        }
        Node<Product> current = list.getHead();
        Node<Product> next = current;

        Method.recursionSort(current, next, list);

        String s = "\nSorted !\n";
        System.out.print(s);
        Method.writeOutToFile(s);

    }

    // 8. Biểu diễn số lượng sản phẩm (đang ở hệ đếm cơ số 10)
    // của phần tử đầu tiên trong Linked List ra hệ hếm nhị phân bằng phương
    // pháp đệ quy.
    public void convertBinary(MyList<Product> list) {
        int n = list.getHead().getProduct().getQuantity();
        MyStack<Integer> stack = new MyStack<>();

        Method.convertBinary(n, stack);

        String s = "\nQuantity = " + n + " ==> (" + stack.toString() + ")\n";
        s = s + "\nSuccessfully !\n";
        System.out.print(s);
        Method.writeOutToFile(s);
    }

    // 9. Đọc dữ liệu từ tệp lưu vào stack và hiển thị
    // thông tin trong stack ra màn hình
    public void getAllItemsFromFile(String fileName, MyStack<Product> stack) {
        //Lam rong stack de khong bi ghi trung
        stack.clear();

        //Doc file 
        File file = new File(fileName);
        try ( FileReader fr = new FileReader(file);  BufferedReader br = new BufferedReader(fr)) {
            String s = br.readLine();
            while (s != null) {
                //1. Tach va lay thong tin cua san pham
                String[] arr = s.split(";");
                String id = arr[0];
                String name = arr[1];
                int quantity = Integer.parseInt(arr[2]);
                double price = Double.parseDouble(arr[3]);

                //2. Tao san pham va them vao dau stack
                Product newProduct = new Product(id, name, quantity, price);
                stack.push(newProduct);
                //3. Doc dong tiep theo
                s = br.readLine();
            }

            //4. Dong cac con tro
            br.close();
            fr.close();

            //5. Hien thi thong tin trong stack
            Method.displayAll(stack);

            s = "\nSuccessfully !\n";
            System.out.print(s);
            Method.writeOutToFile(s);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(OperationToProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OperationToProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //10. Đọc dữ liệu từ tệp lưu vào queue và hiển thị
    // thông tin trong ra queue ra màn hình
    public void getAllItemsFromFile(String fileName, MyQueue<Product> queue) {

        //1. Lam rong queue de khong bi ghi trung
        queue.clear();

        //2. Doc thong tin tu file       
        File file = new File(fileName);
        try ( FileReader fr = new FileReader(file);  BufferedReader br = new BufferedReader(fr)) {
            String s = br.readLine();
            while (s != null) {
                //3. Thong tin tung san pham
                String[] arr = s.split(";");
                String id = arr[0];
                String name = arr[1];
                int quantity = Integer.parseInt(arr[2]);
                double price = Double.parseDouble(arr[3]);

                //4. Tao san pham moi
                Product newProduct = new Product(id, name, quantity, price);

                //5. Them san pham vao queue
                queue.enqueue(newProduct);

                //6. Doc dong tiep theo
                s = br.readLine();
            }

            //7. Dong cac con tro
            br.close();
            fr.close();

            //8. Hien thi thong tin len man hinh
            Method.displayAll(queue);

            s = "\nSuccessfully !\n";
            System.out.print(s);
            Method.writeOutToFile(s);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OperationToProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OperationToProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
