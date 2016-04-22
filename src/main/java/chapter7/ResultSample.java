package chapter7;


import java.util.logging.Logger;

public class ResultSample {
    public static Result<Integer> getComputation() {
        return Result.failure("fail!");
    }

    public static void main(String[] args) {
        Result<Integer> result = getComputation();
        result.forEachOrException(System.out::println).forEach(System.out::println);
    }
}
