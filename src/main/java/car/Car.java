package car;

import java.util.*;
import java.util.function.Predicate;
public class Car {
    private int gasLevel;
    private String color;
    private List<String> passengers;
    private List<String> trunkContents;

    private Car(int gasLevel, String color, List<String> passengers, List<String> trunkContents) {
        this.gasLevel = gasLevel;
        this.color = color;
        this.passengers = passengers;
        this.trunkContents = trunkContents;
    }

    //factory
    public static Car withGasColorPassengers(int gasLevel, String color, String... passengers) {
        List<String> p = List.of(passengers);
        return new Car(gasLevel, color, p, null);
    }

    //factory
    public static Car withGasColorPassengersTrunk(int gasLevel, String color, List<String> trunks, String... passengers) {
        List<String> p = List.of(passengers);
        return new Car(gasLevel, color, p, trunks);
    }

    public static Predicate<Car> getGasLevelCarCriterion(int threshold) {
        return c -> c.gasLevel >= threshold;
    }

//    public static Comparator<Car> greaterFuelLevel(int fuelLevel) {
//        return c -> c.gasLevel - fuelLevel;
//
//    }

    public List<String> getPassengers() {
        return this.passengers;
    }

    public int getGasLevel() {
        return this.gasLevel;
    }

    public Car addGas(int g) {
        return new Car(gasLevel + g, color, passengers, trunkContents);
    }

    public String getColor() {
        return this.color;
    }


    public static Predicate<Car> getColorCriterion(String ... colors){
        Set<String> colorSet = new HashSet<String>(Arrays.asList(colors));
        return c -> colorSet.contains(c.color);
    }
    
    public static class GasLevelCarCriterion implements Predicate<Car> {
        private int threshold;

        public GasLevelCarCriterion(int threshold) {
            this.threshold = threshold;
        }

        @Override
        public boolean test(Car c) {
            return c.gasLevel >= threshold;
        }
    }

    @Override
    public String toString() {
        return
                "Car: gasLevel " + gasLevel + " || " +
                    "color " + color + " || " +
                    "passengers " + passengers + " || " +
                    "trunkContents " + trunkContents + ")";
    }
}
