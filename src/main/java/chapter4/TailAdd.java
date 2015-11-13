package chapter4;


import static chapter4.TailCall.ret;
import static chapter4.TailCall.sus;

public class TailAdd {
    static int add(int x, int y) {
        return addRec(x,y).eval();
    }

    private static TailCall<Integer> addRec(int x, int y) {
        return y == 0
                ? ret(x)
                : sus(() -> addRec(x + 1, y - 1));
    }

    public static void main(String... args) {
        System.out.println(add(3, 100000000));
    }
}
