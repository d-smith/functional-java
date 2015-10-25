package chapter3;


import funciface.Function;

import java.util.regex.Pattern;


//1st version- imperative, mixed processing with effects
//2nd - separate computation from effects
//3rd - introduce something to handle the result of the computation
//4th - update validate to return an executable value instead of validate just having an effect
//5th - Eliminate instanceof test, allow configuring what to do based on validation result
public class SimpleEmail {
    static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    static Function<String, Result<String>> emailChecker = s -> {
        if (s == null) {
            return Result.failure("email must not be null");
        }

        if (s.length() == 0) {
            return Result.failure("email must not be empty");
        }

        if (emailPattern.matcher(s).matches()) {
            return Result.success(s);
        } else {
            return Result.failure("email " + s + " is invalid.");
        }
    };


    static Effect<String> success = s -> System.out.println("Verification mail send to " + s);

    static Effect<String> failure = s -> System.err.println("Error message logged: " + s);

    public static void main(String[] args) {
        emailChecker.apply("foo@bar.com").bind(success, failure);
        emailChecker.apply(null).bind(success, failure);
        emailChecker.apply("").bind(success, failure);
    }
}
