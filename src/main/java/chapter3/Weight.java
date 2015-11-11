package chapter3;


import com.fpinjava.common.Function;

public class Weight {
    public final double value;

    private Weight(double value) {
        this.value = value;
    }

    public Weight add(Weight that) {
        return new Weight(value + that.value);
    }

    public Weight mult(int count) {
        return new Weight(this.value + count);
    }

    public static final Weight ZERO = new Weight(0.0);

    public static Weight weight(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        } else {
            return new Weight(value);
        }
    }

    public static Function<Weight, Function<OrderLine, Weight>> sum = x -> y -> x.add(y.getWeight());

    public String toString() {
        return Double.toString(this.value);
    }
}
