package chapter5;


import static chapter5.List.list;

public class ListSample {
    static void printList(List<Integer> l) {

        boolean separate = false;

        while(!l.isEmpty()) {
            if(separate == true) {
                System.out.print(",");
            } else {
                separate = true;
            }

            System.out.print(l.head());
            l = l.tail();
        }

        System.out.println();
    }

    public static void main(String... args) {
        List<Integer> ex1 = list();
        List<Integer> ex2 = list(1);
        List<Integer> ex3 = list(1, 2);

        System.out.println(ex3);

        List<Integer> ex4 = ex2.cons(0);
        printList(ex4);

        System.out.println("set head on ex4 to -1");
        ex4 = ex4.setHead(-1);
        printList(ex4);
    }
}
