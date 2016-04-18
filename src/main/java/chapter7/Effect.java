package chapter7;

public interface Effect<T> {
    void apply(T t);
}
