package chapter4;


import com.fpinjava.common.Function;

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

    static Function<Integer,Function<Integer,Integer>> selfContainedAdd = x -> y ->
    {
        class AddHelper {
            Function<Integer,Function<Integer,TailCall<Integer>>> addHelper = a -> b ->
                    b == 0 ? ret(a)
                            : sus(()->this.addHelper.apply(a + 1).apply(b - 1));
        }

        return new AddHelper().addHelper.apply(x).apply(y).eval();
    };

    public static void main(String... args) {
        System.out.println(add(3, 100000000));
        System.out.println(selfContainedAdd.apply(3).apply(100000000));
    }
}
