package funciface;


public class FunctionalInterface {
    public static void main(String[] args) {
        Function triple = new Function() {
            @Override
            public int apply(int arg) {
                return arg * 3;
            }
        };

        System.out.println("Triple 3 is " + triple.apply(3));
    }
}
