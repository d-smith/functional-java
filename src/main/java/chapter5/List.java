package chapter5;


public abstract class List<A> {
    public abstract A head();
    public abstract List<A> tail();
    public abstract boolean isEmpty();
    public abstract List<A> cons(A a);
    public abstract List<A> setHead(A a);

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
}
