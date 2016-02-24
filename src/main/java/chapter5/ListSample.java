package chapter5;


import static chapter5.List.list;

public class ListSample {
    static void printList(List<Integer> l) {
        while(!l.isEmpty()) {
            System.out.println(l.head());
            l = l.tail();
        }
    }
    public static void main(String... args) {
        List<Integer> ex1 = list();
        List<Integer> ex2 = list(1);
        List<Integer> ex3 = list(1, 2);

        System.out.println(ex3);

        List<Integer> ex4 = ex2.cons(0);
        printList(ex4);
    }
}
