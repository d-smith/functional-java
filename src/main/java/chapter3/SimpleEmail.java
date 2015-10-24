package chapter3;


import funciface.Function;

import java.util.regex.Pattern;


//1st version- imperative, mixed processing with effects
//2nd - separate computation from effects
//3rd - introduce something to handle the result of the computation
public class SimpleEmail {
    final static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    final static Function<String, Result> emailChecker = s -> {
        if (s == null) {
            return new Result.Failure("email must not be null");
        }

        if (s.length() == 0) {
            return new Result.Failure("email must not be empty");
        }

        if (emailPattern.matcher(s).matches()) {
            return new Result.Success();
        } else {
            return new Result.Failure("email " + s + " is invalid.");
        }
    };


    static void sendVerificationMail(String email) {
        System.out.println("Verification mail send to " + email);
    }

    static void logError(String s) {
        System.err.println("Error message logged: " + s);
    }

    static void validate(String s) {
        Result result = emailChecker.apply(s);
        if (result instanceof Result.Success) {
            sendVerificationMail(s);
        } else {
            logError(((Result.Failure) result).getMessage());
        }
    }

    public static void main(String[] args) {
        validate("foo@bar.com");
        validate(null);
        validate("");
    }
}
