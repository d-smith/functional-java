package chapter6;


public abstract class Option<A> {
    @SuppressWarnings("rawtypes")
    private static Option none = new None();
    public abstract A getOrThrow();
    public abstract A getOrElse(A defaultVal);

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
        public A getOrElse(A defaultVal) {
            return defaultVal;
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
        public A getOrElse(A defaultVal) {
            return this.value;
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
