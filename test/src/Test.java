public class Test {
    public static void test(int[] x) {
        int [] y = new int[8];
        x[0] = 5;
        x = y;
        System.out.println(y.length);
        System.out.println(x.length);
    }

    public static void main(String[] args) {
        int [] x = new int[3];
        System.out.println("in the beginning, x[0] is " + x[0]);
        test(x);
        System.out.println(x.length);
        System.out.println("after calling test and passing x, x[0] is " + x[0]);
    }
}