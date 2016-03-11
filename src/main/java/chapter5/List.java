package chapter5;


import chapter4.TailCall;
import com.fpinjava.common.Function;

import static chapter4.TailCall.ret;
import static chapter4.TailCall.sus;

public abstract class List<A> {
    public abstract A head();
    public abstract List<A> tail();
    public abstract boolean isEmpty();
    public abstract List<A> cons(A a);
    public abstract List<A> setHead(A a);
    public abstract List<A> drop(int n);
    public abstract List<A> dropWhile(Function<A, Boolean> f);
    public abstract List<A> init();
    public abstract List<A> reverse();
    public abstract int length();
    public abstract <B> B foldLeft(B identity, Function<B, Function<A, B>> f);
    public abstract <B> B foldRight(B identity, Function<A, Function<B,B>>  f);


    @SuppressWarnings("rawtypes")
    public static final List NIL = new Nil();

    private List() {}

    private static class Nil<A> extends List<A> {
        private Nil() {}

        public A head() {
            throw new IllegalStateException("head called on Nil list");
        }

        public List<A> tail() {
            throw new IllegalStateException("tail called on Nil list");
        }

        public boolean isEmpty() {
            return true;
        }

        public List<A> cons(A a) {
            return new Cons<>(a, this);
        }

        public List<A> setHead(A a) {
            throw new IllegalStateException("setHead called on empty list");
        }

        public List<A> drop(int n) { return this; }

        public List<A> dropWhile(Function<A, Boolean> f) { return this; }

        public List<A> init() { return this; }

        public List<A> reverse() { return this; }

        public int length() { return 0; }

        @Override
        public <B> B foldLeft(B identity, Function<B, Function<A, B>> f) {
            return identity;
        }

        @Override
        public <B> B foldRight(B identity, Function<A, Function<B, B>> f) { return identity; }
    }

    private static class Cons<A> extends List<A> {
        private final A head;
        private final List<A> tail;

        private Cons(A head, List<A> tail) {
            this.head = head;
            this.tail = tail;
        }

        public A head() {
            return head;
        }

        public List<A> tail() {
            return tail;
        }

        public boolean isEmpty() {
            return false;
        }

        public List<A> cons(A a) {
            return new Cons<>(a,this);
        }

        public List<A> setHead(A a) {
            return new Cons(a,tail());
        }

        public List<A> drop(int n) {
            return n <= 0
                    ? this
                    : drop_(this,n).eval();
        }

        private TailCall<List<A>> drop_(List<A> list, int n) {
            return n <= 0 || list.isEmpty()
                    ? ret(list)
                    : sus(() -> drop_(list.tail(), n - 1));
        }

        public List<A> dropWhile(Function<A, Boolean> f) {
            return dropWhile_(this,f).eval();
        }

        private TailCall<List<A>> dropWhile_(List<A> list, Function<A, Boolean> f) {
            return !list.isEmpty() && f.apply(list.head())
                    ? sus(() -> dropWhile_(list.tail(), f))
                    : ret(list);
        }

        public List<A> reverse() {
            return foldLeft(list(), x -> x::cons);
        }



        public List<A> init() {
            return reverse().tail().reverse();
        }

        public int length() {
           //Internal compiler error -->  return this.foldLeft(0, x -> y -> x + 1);
           //Internal compiler error --> return foldRight(0, x -> y -> y + 1);
            return length_(this, 0).eval();
        }

        private TailCall<Integer> length_(List<A> list, int acc) {
            return list.isEmpty()
                    ? ret(acc)
                    : sus(() -> length_(list.tail(), acc + 1));
        }

        @Override
        public <B> B foldLeft(B identity, Function<B, Function<A, B>> f) {
            return foldLeft_(identity, this, f).eval();
        }

        public <B> TailCall<B> foldLeft_(B acc, List<A> list, Function<B, Function<A, B>> f) {
            return list.isEmpty()
                    ? ret(acc)
                    : sus(() -> foldLeft_(f.apply(acc).apply(list.head()), list.tail(),f));
        }

        @Override
        public <B> B foldRight(B identity, Function<A, Function<B, B>> f) {
            return foldRight_(identity, this, identity, f).eval();
        }

        private <B> TailCall<B> foldRight_(B acc, List<A> list, B identity, Function<A,Function<B,B>> f) {
            return list.isEmpty()
                        ? ret(acc)
                        : sus(() -> foldRight_(f.apply(list.head()).apply(acc), list.tail(),identity,f));
        }
    }

    @SuppressWarnings("unchecked")
    public static <A> List<A> list() {
        return NIL;
    }

    @SafeVarargs
    public static <A> List<A> list(A... a) {
        List<A> n = list();
        for(int i = a.length - 1; i >= 0; i--) {
            n = new Cons<>(a[i], n);
        }

        return n;
    }

    public String toString() {
        return String.format("[%sNIL]",
                toString(new StringBuilder(), this).eval());
    }

    public static <A, B> B foldRight(List<A> list, B n, Function<A, Function<B, B>> f ) {
        return list.foldRight(n, f);
    }

    public static <A> List<A> concat(List<A> list1, List<A> list2) {
        return foldRight(list1, list2, x -> y -> new Cons<>(x, y));
    }

    private TailCall<StringBuilder> toString(StringBuilder acc, List<A> list) {
        return list.isEmpty() ?
                ret(acc)
                : sus(()->toString(acc.append(list.head()).append(","),list.tail()));
    }

    public static <A> List<A> flatten(List<List<A>> list) { return foldRight(list, List.<A>list(), x -> y -> concat(x,y)); }

    public static Integer sum(List<Integer> list) {
        return list.foldLeft(0,x -> y -> x + y);
    }

    public static Double product(List<Double> list) {
        return list.foldLeft(1.0, x -> y -> x * y);
    }

}
