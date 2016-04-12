package chapter7;


import java.io.IOException;

public class ToonMail {
    public static void main(String[] args) {
        Map<String,Toon> toons = new Map<String,Toon>()
                .put("Mickey", new Toon("Mickey","Mouse","mickey@disney.com"))
                .put("Minnie", new Toon("Minnie", "Mouse"))
                .put("Dolan", new Toon("Dolan","Duck", "dolan@troll.org"));

        Result<String> result =
                getName().flatMap(toons::get).flatMap(Toon::getEmail);
        System.out.println(result);
    }

    public static Result<String> getName() {
        //return Result.success("Mickey");
        //return Result.failure(new IOException("error reading stuff"));
        return Result.success("Minnie");
    }
}
