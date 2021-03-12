import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = null;
        int debug = 1;
        try {
            in = new Scanner(System.in);
//            in = new Scanner(
//                    new File("C:\\Users\\duckingss\\Desktop\\university\\oo\\submit\\hw\\unit1\\test.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String s = in.nextLine();
            String os = "";
            NewGet get = new NewGet(s);
            System.out.println(get.Exp.simpify().diff().reform());
        } catch (Exception e) {
            if (debug == 1)
                e.printStackTrace();
            else
                System.out.println("WRONG FORMAT! ");
            return;
        }
    }
}
