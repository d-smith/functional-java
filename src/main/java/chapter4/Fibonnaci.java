package chapter4;


import java.math.BigInteger;

public class Fibonnaci {

    private static BigInteger fib_(BigInteger acc1, BigInteger acc2, BigInteger x) {
        if(x.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        } else if (x.equals(BigInteger.ONE)) {
            return acc1.add(acc2);
        } else {
            return fib_(acc2, acc1.add(acc2), x.subtract(BigInteger.ONE));
        }
    }

    public static BigInteger fib(int x) {
        return fib_(BigInteger.ONE, BigInteger.ZERO, BigInteger.valueOf(x));
    }

    public static void main(String... args) {
        int n = 10;

        for(int i = 0; i < n; i++) {
            System.out.println(fib(i));
        }
    }
}