package chapter7;


public class Main {
    public static void main(String[] args) {
        Map<String,String> map = Map.empty();
        map = map.put("foo", "foo thing");
        Result<String> ft = map.get("foo");
        System.out.println(String.format("foo thing - %s", ft));

        Result<String> bt = map.get("bar");
        System.out.println(String.format("bar thing - %s", bt));
    }
}
