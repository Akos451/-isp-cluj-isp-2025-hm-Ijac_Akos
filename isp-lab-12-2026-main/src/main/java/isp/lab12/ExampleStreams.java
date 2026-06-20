package isp.lab12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Radu Miron
 * <p>
 * Run/implement each method from 1. to 8.
 * Implement unit tests for the methods from 1. to 8.
 */
public class ExampleStreams {
    private static final List<Person> PERSONS = new ArrayList<>();

    // loop through the persons in a given list
    private static void printPersonList(Collection<Person> persons) {
        persons.forEach(p -> System.out.println(p)); //<=> persons.forEach(System.out::println);
    }

    // loop through the persons in a given list
    private static void printPersonMap(Map<?, List<Person>> personByCriteria) {
        personByCriteria.forEach((k, l) -> {
            System.out.println(k);
            l.forEach(System.out::println);
        });
    }

    // 1. find all the employed persons
    protected static List<Person> findEmployedPersons(List<Person> persons) {
        return persons.stream()
                .filter(p -> p.isEmployed()) // <=> .filter(Person::isEmployed)
                .collect(Collectors.toList());
    }

    // find the employed persons - met 2. implemented with anonymous classes
    protected static List<Person> findEmployedPersonsWithAnonymousClass(List<Person> persons) {
        Predicate<Person> thePredicate = new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return person.isEmployed();
            }
        };

        return persons.stream()
                .filter(thePredicate)
                .collect(Collectors.toList());
    }

    // 2. find all the persons by last name
    protected static List<Person> findAllPersonsByLastName(List<Person> persons, String lastName) {
        return persons.stream()
                .filter(p -> lastName != null && lastName.equals(p.getLastName()))
                .collect(Collectors.toList());
    }

    // 3. find any person with the given first name
    protected static Person findFirstPersonByFirstName(List<Person> persons, String firstName) {
        return persons.stream()
                .filter(p -> firstName != null && firstName.equals(p.getFirstName()))
                .findFirst()
                .orElse(null);
    }

    // 4. Get a set of the first names from the persons list
    protected static Set<String> getUniqueFirstNames(List<Person> persons) {
        return persons.stream()
                .map(p -> p.getFirstName())
                .collect(Collectors.toSet());
    }

    // 5. Calculate the average salary of all employed persons
    protected static double calculateAverageSalary(List<Person> persons) {
        return persons.stream()
                .filter(p -> p.isEmployed())
                .mapToInt(p -> p.getSalary())
                .average()
                .orElse(0);
    }

    // 6. IMPLEMENTED: Calculate the average age of all unemployed persons
    protected static double calculateAverageAge(List<Person> persons) {
        return persons.stream()
                .filter(p -> !p.isEmployed()) // Target unemployed
                .mapToInt(p -> p.getAge())
                .average()
                .orElse(0);
    }

    // 7. group the persons by employed/unemployed collect to map
    public static Map<Boolean, List<Person>> groupByEmploymentStatus(List<Person> persons) {
        return persons.stream()
                .collect(Collectors.groupingBy(Person::isEmployed));
    }

    // 8. IMPLEMENTED: Group persons by first name
    protected static Map<String, List<Person>> groupByFirstName(List<Person> persons) {
        return persons.stream()
                .collect(Collectors.groupingBy(Person::getFirstName));
    }

    // =========================================================================
    // ADDITIONAL ASSIGNMENT STREAM EXERCISES (3 to 9)
    // =========================================================================

    // Exercise 3: Find the length of the longest string
    public static int getLongestStringLength(List<String> strings) {
        return strings.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    // Exercise 4: Find the sum of all even numbers
    public static int sumOfEvenNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
    }

    // Exercise 5: Convert a list of strings to uppercase
    public static List<String> convertToUppercase(List<String> strings) {
        return strings.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    // Exercise 6: Group a list of words by their first letter
    public static Map<Character, List<String>> groupByFirstLetter(List<String> words) {
        return words.stream()
                .filter(w -> w != null && !w.isEmpty())
                .collect(Collectors.groupingBy(w -> w.charAt(0)));
    }

    // Exercise 7: Count occurrences of a word in a text file
    public static long countWordOccurrences(String filePath, String targetWord) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .map(word -> word.replaceAll("[^a-zA-Z]", ""))
                    .filter(word -> word.equalsIgnoreCase(targetWord))
                    .count();
        }
    }

    // Exercise 8: Filter Product objects by category "Electronics" and sort them by price
    public static List<Product> filterAndSortElectronics(List<Product> products) {
        return products.stream()
                .filter(p -> "Electronics".equalsIgnoreCase(p.getCategory()))
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }

    // Exercise 9: Calculate total revenue of all completed orders using reduce()
    public static double calculateCompletedOrdersRevenue(List<Order> orders) {
        return orders.stream()
                .filter(o -> "completed".equalsIgnoreCase(o.getStatus()))
                .map(Order::getTotal)
                .reduce(0.0, Double::sum);
    }

    public static void main(String[] args) {
        initPersons();

        // Uncommented original test drivers
        System.out.println("--- Employed Persons ---");
        printPersonList(findEmployedPersons(PERSONS));

        System.out.println("\n--- Average Age of Unemployed ---");
        System.out.println(calculateAverageAge(PERSONS));

        System.out.println("\n--- Grouped by First Name ---");
        printPersonMap(groupByFirstName(PERSONS));
    }

    private static void initPersons() {
        PERSONS.add(new Person("John", "Thomson", 25, true, 1200));
        PERSONS.add(new Person("Olivia", "Harris", 18, false, 0));
        PERSONS.add(new Person("Lucas", "Scott", 35, true, 1100));
        PERSONS.add(new Person("Benjamin", "Lee", 15, false, 0));
        PERSONS.add(new Person("Chloe", "Green", 45, true, 2000));
        PERSONS.add(new Person("Lucas", "Patel", 38, true, 1700));
        PERSONS.add(new Person("Ava", "Lee", 14, false, 0));
        PERSONS.add(new Person("Chloe", "Lee", 48, true, 2300));
    }

    public static final class Person {
        private String firstName;
        private String lastName;
        private int age;
        private boolean employed;
        private int salary;

        public Person(String firstName, String lastName, int age, boolean employed, int salary) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.employed = employed;
            this.salary = salary;
        }

        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        public boolean isEmployed() { return employed; }
        public void setEmployed(boolean employed) { this.employed = employed; }
        public int getSalary() { return salary; }
        public void setSalary(int salary) { this.salary = salary; }

        @Override
        public String toString() {
            return "Person{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", age=" + age + ", salary=" + salary + '}';
        }
    }
}

// Supporting Domain Entities
class Product {
    private String name;
    private double price;
    private String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
}

class Order {
    private int id;
    private double total;
    private String status;

    public Order(int id, double total, String status) {
        this.id = id;
        this.total = total;
        this.status = status;
    }
    public double getTotal() { return total; }
    public String getStatus() { return status; }
}