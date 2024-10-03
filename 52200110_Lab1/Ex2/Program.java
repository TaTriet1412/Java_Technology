import vn.edu.tdtu.*;

public class Program{
    public static void main (String[] args) {
        int a[] = new int[]{5,3,2,1,5,6,7,4};
        int b[] = new int[]{2,4,5,3,1,6,7,2};
        ArrayOutput.print(a);
        ArrayOutput.print(b);
        int c[] = ArrayHandler.merge(a,b);
        ArrayOutput.print(c);
        ArrayHandler.sort(c);
        ArrayOutput.print(c);
        ArrayOutput.write(c, "output.txt");
    }
}