package duongbanhat;

import java.util.Calendar;
import java.util.Date;

/**
 * Viết chương trình quản lý sản phẩm trong kho hàng của một siêu thị
 */
public class ASM2_Main {

    public static void main(String[] args) {
        OperationToProduct otp = new OperationToProduct();
        MyList<Product> list = new MyList<>();     
        MyStack<Product> stack = new MyStack<>();
        MyQueue<Product> queue = new MyQueue<>();
        
        String fileName = "data.txt";

        //Ghi thoi gian vao dau file log
        Calendar cal = Calendar.getInstance();
        Date d = cal.getTime();
        String s = d.toString();
        Method.writeOutToFile("\n\n***** " + s + " *****\n");
        
        while (true) {
            showMenu(otp, fileName, list, stack, queue);
        }
    }

    public static void showMenu(OperationToProduct otp, String fileName, 
            MyList<Product> list, MyStack<Product> stack, MyQueue<Product> queue) {

        String s0 = String.format("\n%5s***** %s *****\n"," ", "PRODUCT MANAGEMENT PROGRAM");
        String s1 = String.format("\n%5s|---%s---------|"," ", "Choose one of this options:");
        String s2 = String.format("\n%5s|   %-36s|", " ", "Product list:");
        String s3 = String.format("\n%5s|   %-36s|", " ", "1. Load data from file and display");
        String s4 = String.format("\n%5s|   %-36s|", " ", "2. Input & add to the end.");
        String s5 = String.format("\n%5s|   %-36s|", " ", "3. Display data");
        String s6 = String.format("\n%5s|   %-36s|", " ", "4. Save product list to file.");
        String s7 = String.format("\n%5s|   %-36s|", " ", "5. Search by ID");
        String s8 = String.format("\n%5s|   %-36s|", " ", "6. Delete by ID");
        String s9 = String.format("\n%5s|   %-36s|", " ", "7. Sort by ID.");
        String s10 = String.format("\n%5s|   %-36s|", " ", "8. Convert to Binary");
        String s11 = String.format("\n%5s|   %-36s|", " ", "9. Load to stack and display");
        String s12 = String.format("\n%5s|   %-36s|", " ", "10. Load to queue and display.");
        String s13 = String.format("\n%5s|   %-36s|", " ", "Exit:");
        String s14 = String.format("\n%5s|   %-36s|", " ", "0. Exit");
        String s15 = String.format("\n%5s%s", " ", "|---------------------------------------|");
        String s16 = String.format("\nChoice: ");
        
        
        System.out.print(s0+s1+s2+s3+s4+s5+s6+s7+s8+s9+s10+s11+s12+s13+s14+s15+s16);
        Method.writeOutToFile(s0, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13,s14, s15, s16);
        
        int choice = (int)Method.inputNumber("int");
        Method.writeOutToFile(choice + "\n");
        switch (choice) {
            case 1:
                String s = "\n1. Load data from file and display.\n";
                System.out.print(s);
                Method.writeOutToFile(s);
                
                otp.getAllItemsFromFile(fileName, list);
                break;
            case 2:
                s = "\n2. Input & add to the end.\n";
                System.out.print(s);
                Method.writeOutToFile(s);
                
                otp.addLast(list);
                break;
            case 3:
                s = "\n3. Display data.\n";
                System.out.print(s);
                Method.writeOutToFile(s);
                
                otp.displayAll(list);
                break;
            case 4:
                s = "\n4. Save product list to file.\n";
                System.out.print(s);
                Method.writeOutToFile(s);
                
                otp.writeAllItemsToFile(fileName, list);
                break;
            case 5:
                s = "\n5. Search by ID.\n";
                System.out.print(s);
                Method.writeOutToFile(s);
                
                otp.searchByCode(list);
                break;
            case 6:
                s = "\n6. Delete by ID.\n";
                System.out.print(s);
                Method.writeOutToFile(s);
                
                otp.deleteByCode(list);
                break;
            case 7:
                s = "\n7. Sort by ID.\n";
                System.out.print(s);
                Method.writeOutToFile(s);
                
                otp.sortByCode(list);
                break;
            case 8:
                s = "\n8. Convert to Binary.\n";
                System.out.print(s);
                Method.writeOutToFile(s);

                otp.convertBinary(list);
                break;
            case 9:
                s = "\n9. Load to stack and display.\n";
                System.out.print(s);
                Method.writeOutToFile(s);
                
                otp.getAllItemsFromFile(fileName, stack);
                break;
            case 10:
                s = "\n10. Load to queue and display.\n";
                System.out.print(s);
                Method.writeOutToFile(s);
                
                otp.getAllItemsFromFile(fileName, queue);
                break;
            case 0:
                s = "\nThanks !\n";
                System.out.print(s);
                Method.writeOutToFile(s);
                
                System.exit(0);
                break;
            default:
                s = "\nPlease, try again!\n";
                System.out.print(s);
                Method.writeOutToFile(s);
                break;
        }
    }
}
