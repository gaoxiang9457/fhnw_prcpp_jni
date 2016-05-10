public class Test {
    public static native void display();

    public static native int increment(int value);

    static {
        System.loadLibrary("sharedFunctions");
    }

    public static void main(String[] args) {
        display();
        System.out.println(increment(1));
    }
}
