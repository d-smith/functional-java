package chapter6;

import com.fpinjava.common.Function;
import com.fpinjava.common.Supplier;

public abstract class Option<A> {
    @SuppressWarnings("rawtypes")
    private static Option none = new None();
    public abstract A getOrThrow();
    public abstract A getOrElse(Supplier<A> defaultValue);
    public abstract <B> Option<B> map(Function<A, B> f);


    private static class None<A> extends Option<A> {
        @Override
        public A getOrThrow() {
            throw new IllegalStateException("getOrThrow called on None");
        }

        private None() {}

        @Override
        public String toString() {
            return "None";
        }

        @Override
        public A getOrElse(Supplier<A> defaultValue) {
            return defaultValue.get();
        }

        @Override
        public <B> Option<B> map(Function<A, B> f) {
            return none();
        }
    }

    private static class Some<A> extends Option<A> {
        private final A value;


        private Some(A a) {
            this.value = a;
        }

        @Override
        public A getOrThrow() {
            return this.value;
        }

        @Override
        public String toString() {
            return String.format("Some(%s)", this.value);
        }

        @Override
        public A getOrElse(Supplier<A> defaultValue) {
            return this.value;
        }

        @Override
        public <B> Option<B> map(Function<A, B> f) {
            return some(f.apply(this.value));
        }
    }

    public static <A> Option<A> some(A a) {
        return new Some<>(a);
    }

    @SuppressWarnings("unchecked")
    public static <A> Option<A> none() {
        return none;
    }

}