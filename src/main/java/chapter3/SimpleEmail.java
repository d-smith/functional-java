package chapter3;


import funciface.Function;

import java.util.regex.Pattern;


//1st version- imperative, mixed processing with effects
//2nd - separate computation from effects
public class SimpleEmail {
    final static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    final static Function<String, Boolean> emailChecker = s -> emailPattern.matcher(s).matches();

    static void testMail(String email) {
        if(emailChecker.apply(email)) {
            sendVerificationMail(email);
        } else {
            logError("email " + email + " is invalid.");
        }
    }

    static void sendVerificationMail(String email) {
        System.out.println("Verification mail send to " + email);
    }

    static void logError(String s) {
        System.err.println("Error message logged: " + s);
    }

    public static void main(String[] args) {
        testMail("foo@bar.com");
        testMail("huh?");
    }
}
