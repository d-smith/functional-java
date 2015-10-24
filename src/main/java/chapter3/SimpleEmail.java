package chapter3;


import java.util.regex.Pattern;

public class SimpleEmail {
    final static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    static void testMail(String email) {
        if(emailPattern.matcher(email).matches()) {
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
