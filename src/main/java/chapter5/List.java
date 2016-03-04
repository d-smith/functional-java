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
            return reverse_(list(), this).eval();
        }

        private TailCall<List<A>> reverse_(List<A> acc, List<A> list) {
            return list.isEmpty()
                    ? ret(acc)
                    : sus(() -> reverse_(new Cons<>(list.head(), acc), list.tail()));
        }

        public List<A> init() {
            return reverse().tail().reverse();
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

    public static <A> List<A> concat(List<A> list1, List<A> list2) {
        return list1.isEmpty()
                ? list2
                : new Cons<>(list1.head(), concat(list1.tail(), list2));
    }

    private TailCall<StringBuilder> toString(StringBuilder acc, List<A> list) {
        return list.isEmpty() ?
                ret(acc)
                : sus(()->toString(acc.append(list.head()).append(","),list.tail()));
    }

    public static Integer sum(List<Integer> list) {
        return list.isEmpty()
                ? 0
                : list.head() + sum(list.tail());

    }
}
