package chapter4;


public class TailAdd {
    static TailCall<Integer> add(int x, int y) {
        return y == 0 ?
                new TailCall.Return<>(x)
                : new TailCall.Suspend<>(() -> add(x + 1, y - 1));
    }

    public static void main(String... args) {
        TailCall<Integer> tailCall = add(3, 100000000);
        while(tailCall.isSuspend()) {
            tailCall = tailCall.resume();
        }
        System.out.println(tailCall.eval());
    }
}
