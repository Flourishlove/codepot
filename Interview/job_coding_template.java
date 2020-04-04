import java.io.*;
import java.util.*;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Main
{
    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        int a, b;
        while(cin.hasNextInt())
        {
            a = cin.nextInt();
            b = cin.nextInt();
            System.out.println(a + b);
        }
    }
}

String data = scanner.nextLine();
String[] pieces = data.split("\\s+");



 Scanner s = new Scanner(input).useDelimiter("\\s*fish\\s*");

 System.out.println(s.nextInt());   // prints: 1
 System.out.println(s.nextInt());   // prints: 2
 System.out.println(s.next());      // prints: red
 System.out.println(s.next());      // prints: blue

 // don't forget to close the scanner!!
 s.close();