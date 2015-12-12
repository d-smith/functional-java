package chapter5;


import static chapter5.List.list;

public class ListSample {
    public static void main(String... args) {
        List<Integer> ex1 = list();
        List<Integer> ex2 = list(1);
        List<Integer> ex3 = list(1, 2);

        System.out.print(ex3);
    }
}
