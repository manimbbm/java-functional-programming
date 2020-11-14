package car;

public interface Criterion<E> {
    boolean test(E obj);

    default Criterion<E> negate() {
        return x -> !this.test(x);
    }

    default Criterion<E> and(Criterion<E> second) {
        return x -> this.test(x) && second.test(x);
    }

    default Criterion<E> or(Criterion<E> second) {
        return x -> this.test(x) || second.test(x);
    }
}
