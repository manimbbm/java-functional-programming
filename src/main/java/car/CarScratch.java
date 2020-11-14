package car;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

class PassengerCountOrder implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        return o1.getPassengers().size() - o2.getPassengers().size();
    }
}

//@FunctionalInterface
//interface Predicate<E> {
//    boolean test(E c);
//}

public class CarScratch {
    public static <E> ToIntFunction<E> compareWithThis(E target, Comparator<E> comp) {
        return x -> comp.compare(target, x);
    }

    public static <E> Predicate<E> comparesGreater(ToIntFunction<E> comp){
        return x -> comp.applyAsInt(x) < 0;

    }

    public static <E> void showAll(List<E> items){
        items.forEach(System.out::println);
        System.out.println("------------------------------------------");
    }

    public static <E> List<E> getByCriterion(Iterable<E> in, Predicate<E> crit) {
        List<E> output = new ArrayList<>();
        in.forEach( i -> {
            if(crit.test(i)) { output.add(i); }
        });
        return output;
    }

    public static void main (String[] args){
        List<Car> cars = List.of(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim"),
                Car.withGasColorPassengers(1, "Blue", "Fred"),
                Car.withGasColorPassengers(2, "Red", "Fred", "Jim", "Angela"),
                Car.withGasColorPassengers(12, "Red", "Fred", "Jim", "Mario", "Bowser", "Peter")
        );

        Predicate<Car> level7 = Car.getGasLevelCarCriterion(7);
        showAll(getByCriterion(cars, level7));

        Predicate<Car> isRed = Car.getColorCriterion("Red");
        Predicate<Car> level7AndRed = isRed.and(level7);
        showAll(getByCriterion(cars, level7));

        Car bugatti = Car.withGasColorPassengers(5, "Black", "Isa", "Bela", "Luck");
        Comparator<Car> comparePassengerCount = new PassengerCountOrder();
        ToIntFunction<Car> compareWithBugatti = compareWithThis(bugatti, comparePassengerCount);

//        for (Car c : cars) {
//            System.out.println("comparing " + c.toString() + " with a Bugatti " + compareWithBugatti.applyAsInt(c));
//        }

        showAll(getByCriterion(cars, comparesGreater(compareWithBugatti)));
    }
    
}
