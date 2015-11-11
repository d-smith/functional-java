package chapter3;


import com.fpinjava.common.Function;

import java.util.List;

import static chapter3.CollectionUtilities.foldLeft;
import static chapter3.Price.price;
import static chapter3.Weight.weight;
import static chapter3.CollectionUtilities.list;

public class Store {
    public static void main(String... args) {
        Product toothpaste = new Product("toothpaste", price(1.5), weight(0.5));
        Product toothBrush = new Product("Tooth brush", price(3.5), weight(0.3));

        List<OrderLine> order = list(
                new OrderLine(toothpaste,2),
                new OrderLine(toothBrush, 3)
        );

        Price price = foldLeft(order, Price.ZERO, Price.sum);
        Weight weight = foldLeft(order, Weight.ZERO, Weight.sum);

        System.out.println(String.format("Total price: %s", price));
        System.out.println(String.format("Total weight: %s", weight));
    }


}
