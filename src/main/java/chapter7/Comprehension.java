package chapter7;

import static chapter7.Result.*;

public class Comprehension {

    private static int compute(int p1, int p2, int p3, int p4, int p5) {
        return p1 + p2 + p3 + p4 + p5;
    }

    public static void main(String[] args) {
        Result<Integer> result1 = success(1);
        Result<Integer> result2 = success(2);
        Result<Integer> result3 = success(3);
        Result<Integer> result4 = success(4);
        Result<Integer> result5 = success(5);

        Result<Integer> result = result1
                .flatMap(p1 -> result2
                    .flatMap(p2 -> result3
                        .flatMap(p3 -> result4
                            .flatMap(p4 -> result5
                                .map(p5 -> compute(p1,p2,p3,p4,p5))))));

        System.out.println(result);
    }

}
