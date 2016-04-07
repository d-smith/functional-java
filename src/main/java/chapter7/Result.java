package chapter7;


import java.io.Serializable;

public abstract class Result<V> implements Serializable {
    private static class Failure<V> extends Result<V> {
        private RuntimeException exception;

        private Failure(String message) {
            super();
            this.exception = new IllegalStateException(message);
        }

        private Failure(RuntimeException exception) {
            this.exception = exception;
        }

        private Failure(Exception e) {
            this.exception = new IllegalStateException(e.getMessage(),e);
        }

        @Override
        public String toString() {
            return String.format("Failure(%s)", exception.getMessage());
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
    }

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
}
