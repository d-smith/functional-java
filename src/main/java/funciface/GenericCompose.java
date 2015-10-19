package funciface;


public class GenericCompose {


    public static void main(String[] args) {
        Double cos = Function.<Double,Double,Double>compose().apply(x -> Math.PI / 2 - x).apply(Math::sin).apply(2.0);
        System.out.println("Cosine of 2.0: " + cos);
    }

}
