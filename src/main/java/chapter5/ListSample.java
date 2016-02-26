package chapter5;


import static chapter5.List.list;

public class ListSample {

    public static void main(String... args) {
        List<Integer> ex1 = list();
        List<Integer> ex2 = list(1);
        List<Integer> ex3 = list(1, 2);

        System.out.println(ex3);

        List<Integer> ex4 = ex2.cons(0);
        System.out.println(ex4);

        System.out.println("set head on ex4 to -1");
        ex4 = ex4.setHead(-1);
        System.out.println(ex4);
    }
}
