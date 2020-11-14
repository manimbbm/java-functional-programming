package car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E>{
    private Iterable<E> self;

    public SuperIterable(Iterable<E> s) {
        self = s;
    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }

    public SuperIterable<E> filter(Predicate<E> pred) {
        List<E> results = new ArrayList<>();
        self.forEach(e -> {
            if (pred.test(e)) results.add(e);
        });

        return new SuperIterable<>(results);
    }

    public <F> SuperIterable<F> map(Function<E, F> op) {
        List<F> results = new ArrayList<>();
        self.forEach(e -> results.add(op.apply(e)));

        return new SuperIterable<>(results);
    }

//    public void forEvery(Consumer<E> cons) {
//        for (E e : self) {
//            cons.accept(e);
//        }
//    }

    public static void main(String[] args) {
        SuperIterable<String> strings = new SuperIterable<>(Arrays.asList("orange", "pink", "blue", "Gold", "Plum"));
        strings.forEach(System.out::println);

        SuperIterable<String> upperCase = strings.filter(s -> Character.isUpperCase(s.charAt(0)));

        System.out.println("---------------------------");
        upperCase.forEach(System.out::println);

        System.out.println("---------------------------");
        strings.forEach(System.out::println);

        System.out.println("---------------------------");
        strings
            .filter(s -> Character.isUpperCase(s.charAt(0)))
            .map(String::toUpperCase)
            .forEach(System.out::println);

        SuperIterable<Car> carIter = new SuperIterable<>(
                List.of(Car.withGasColorPassengers(6, "Red", "Fred", "Jim"),
                        Car.withGasColorPassengers(1, "Blue", "Bruno"),
                        Car.withGasColorPassengers(2, "Red", "Isa", "Bela", "Angela"),
                        Car.withGasColorPassengers(12, "Red", "Lucas", "Luigi", "Mario", "Bowser", "Peter")
                )
        );

        System.out.println("---------------------------");
        carIter
                .filter(c -> c.getGasLevel() >= 6)
                .map(c -> c.getPassengers().get(0) + " is driving a " + c.getColor() + " car with " + c.getGasLevel() + " of fuel")
                .forEach(c -> System.out.println("> " + c));

//        System.out.println("---------------------------");
//        carIter
//                .map(c -> Car.withGasColorPassengers(
//                        c.getGasLevel() + 10,
//                        c.getColor(),
//                        c.getPassengers().toArray(new String[]{}))
//                )
//                .forEach(c -> System.out.println(">> " + c));

        //Redoing the same as above with and addGas method from the Car
        System.out.println("---------------------------");
        carIter
                .map(c -> c.addGas(10))
                .forEach(c -> System.out.println(">> " + c));

        System.out.println("---------------------------");
        carIter.forEach(c -> System.out.println(">> " + c));
    }
}
