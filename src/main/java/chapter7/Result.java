package chapter7;


import com.fpinjava.common.Function;
import com.fpinjava.common.Supplier;

import java.io.Serializable;

public abstract class Result<V> implements Serializable {
    public abstract V getOrElse(final V defaultValue);
    public abstract V getOrElse(final Supplier<V> defaultValue);
    public abstract <U> Result<U> map(Function<V, U> f);
    public abstract <U> Result<U> flatMap(Function<V, Result<U>> f);
    public abstract Result<V> mapFailure(String s);
    public abstract void forEach(Effect<V> t);
    public abstract void forEachOrThrow(Effect<V> ef);
    public abstract Result<RuntimeException> forEachOrException(Effect<V> ef);


    private static class Empty<V> extends Result<V> {
        public Empty() {
            super();
        }

        @Override
        public V getOrElse(V defaultValue) {
            return defaultValue;
        }

        @Override
        public V getOrElse(Supplier<V> defaultValue) {
            return defaultValue.get();
        }

        @Override
        public <U> Result<U> map(Function<V, U> f) {
            return empty();
        }

        @Override
        public <U> Result<U> flatMap(Function<V, Result<U>> f) {
            return empty();
        }

        @Override
        public String toString() {
            return "Empty()";
        }

        @Override
        public Result<V> mapFailure(String s) {
            return this;
        }


        @Override
        public void forEach(Effect<V> t) {
            //Empty - nothing to do
        }

        @Override
        public void forEachOrThrow(Effect<V> ef) {
            //Do nothing
        }

        @Override
        public Result<RuntimeException> forEachOrException(Effect<V> ef) {
            return empty();
        }
    }

    private static class Failure<V> extends Empty<V> {
        private RuntimeException exception;


        private Failure(String message) {
            super();
            this.exception = new IllegalStateException(message);
        }

        private Failure(RuntimeException exception) {
            super();
            this.exception = exception;
        }

        private Failure(Exception e) {
            super();
            this.exception = new IllegalStateException(e.getMessage(),e);
        }

        @Override
        public String toString() {
            return String.format("Failure(%s)", exception.getMessage());
        }

        @Override
        public V getOrElse(V defaultValue) {
            return defaultValue;
        }

        @Override
        public V getOrElse(Supplier<V> defaultValue) {
            return defaultValue.get();
        }

        @Override
        public <U> Result<U> map(Function<V, U> f) {
            return failure(exception);
        }

        @Override
        public <U> Result<U> flatMap(Function<V, Result<U>> f) {
            return failure(exception);
        }

        @Override
        public Result<V> mapFailure(String s) {
            return failure(new IllegalStateException(s, exception));
        }

        @Override
        public void forEachOrThrow(Effect<V> ef) { throw exception; }

        @Override
        public Result<RuntimeException> forEachOrException(Effect<V> ef) {
            return success(exception);
        }
    }

    private static class Success<V> extends Result<V> {
        private final V value;

        private Success(V value) {
            super();
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Success(%s)", value);
        }

        @Override
        public V getOrElse(V defaultValue) {
            return value;
        }

        @Override
        public V getOrElse(Supplier<V> defaultValue) {
            return value;
        }

        @Override
        public <U> Result<U> map(Function<V, U> f) {
            return success(f.apply(value));
        }

        @Override
        public <U> Result<U> flatMap(Function<V, Result<U>> f) {
            return f.apply(value);
        }

        @Override
        public Result<V> mapFailure(String s) {
            return this;
        }

        @Override
        public void forEach(Effect<V> t) {
            t.apply(value);
        }

        @Override
        public void forEachOrThrow(Effect<V> ef) { ef.apply(value); }

        @Override
        public Result<RuntimeException> forEachOrException(Effect<V> ef) {
            ef.apply(value);
            return empty();
        }
    }

    public static <V> Result<V> empty() { return new Empty<>(); }

    public static <V> Result<V> failure(String message) {
        return new Failure<>(message);
    }

    public static <V> Result<V> failure(Exception e) {
        return new Failure<V>(e);
    }

    public static <V> Result<V> failure(RuntimeException e) {
        return new Failure<V>(e);
    }

    public static <V> Success<V> success(V value) {
        return new Success<>(value);
    }

    public Result<V> orElse(Supplier<Result<V>> defaultValue) {
        return map(x -> this).getOrElse(defaultValue);
    }

    public Result<V> filter(Function<V,Boolean> p) {
        return flatMap(x -> p.apply(x)
                ? this
                : failure("Condition not matches"));
    }

    public Result<V> filter(Function<V,Boolean> p, String msg) {
        return flatMap(x -> p.apply(x)
                ? this
                : failure(msg));
    }

    public boolean exists(Function<V,Boolean> p) {
        return map(p).getOrElse(false);
    }


    public static <V> Result<V> of(V value) {
        return value != null
                ? success(value)
                : empty();
    }

    public static <V> Result<V> of(V value, String message) {
        return value != null
                ? success(value)
                : failure(message);
    }

    public static <A,B> Function<Result<A>,Result<B>> lift(Function<A,B> f) {
        return x -> {
            try {
                return x.map(f);
            } catch (Exception e) {
                return failure(e);
            }
        };
    }

    public static <A, B, C> Function<Result<A>, Function<Result<B>,Result<C>>> lift2(Function<A, Function<B, C>> f) {
        return a -> b -> a.map(f).flatMap(b::map);
    }

    public static <A,B,C,D> Function<Result<A>, Function<Result<B>,Function<Result<C>, Result<D>>>> lift3(Function<A,Function<B,Function<C,D>>> f) {
        return a -> b -> c -> a.map(f).flatMap(b::map).flatMap(c::map);
    }

    public static <A,B,C> Result<C> map2(Result<A> a, Result<B> b, Function<A,Function<B,C>> f) {
        Function<Result<A>,Function<Result<B>,Result<C>>> f2 = lift2(f);
        Function<Result<B>,Result<C>> f3 = f2.apply(a);
        return f3.apply(b);
    }


}
