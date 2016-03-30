package chapter6;


import chapter5.List;
import com.fpinjava.common.Function;

public class SeqExample {
    public static void main(String[] args) {
        Function<Integer, Function<String,Integer>> parseWithRadix =
                radix -> string -> Integer.parseInt(string,radix);

        Function<String,Option<Integer>> parse16 =
                Option.hlift(parseWithRadix.apply(16));

        List<String> list = List.list("4","5","6","7","8","9");

        Option<List<Integer>> result = Option.sequence(list.map(parse16));

        System.out.println(result);
    }
}
