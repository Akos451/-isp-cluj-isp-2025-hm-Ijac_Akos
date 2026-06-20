package isp.lab12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class ExampleStreamsTest {

    private List<ExampleStreams.Person> testPersons;

    @BeforeEach
    public void setUp() {
        testPersons = new ArrayList<>();
        testPersons.add(new ExampleStreams.Person("John", "Thomson", 25, true, 1200));
        testPersons.add(new ExampleStreams.Person("Olivia", "Harris", 18, false, 0));
        testPersons.add(new ExampleStreams.Person("Lucas", "Scott", 35, true, 1100));
        testPersons.add(new ExampleStreams.Person("Benjamin", "Lee", 15, false, 0));
        testPersons.add(new ExampleStreams.Person("Chloe", "Green", 45, true, 2000));
    }

    // =========================================================================
    // TESTS FOR METHODS 1 TO 8 (Radu Miron's Methods)
    // =========================================================================

    @Test
    public void testFindEmployedPersons() {
        List<ExampleStreams.Person> employed = ExampleStreams.findEmployedPersons(testPersons);
        assertEquals(3, employed.size());
        assertTrue(employed.stream().allMatch(ExampleStreams.Person::isEmployed));
    }

    @Test
    public void testFindAllPersonsByLastName() {
        List<ExampleStreams.Person> lees = ExampleStreams.findAllPersonsByLastName(testPersons, "Lee");
        assertEquals(1, lees.size());
        assertEquals("Benjamin", lees.get(0).getFirstName());
    }

    @Test
    public void testFindFirstPersonByFirstName() {
        ExampleStreams.Person person = ExampleStreams.findFirstPersonByFirstName(testPersons, "Lucas");
        assertNotNull(person);
        assertEquals("Scott", person.getLastName());
    }

    @Test
    public void testGetUniqueFirstNames() {
        Set<String> uniqueNames = ExampleStreams.getUniqueFirstNames(testPersons);
        assertEquals(5, uniqueNames.size());
        assertTrue(uniqueNames.contains("Olivia"));
    }

    @Test
    public void testCalculateAverageSalary() {
        double avgSalary = ExampleStreams.calculateAverageSalary(testPersons);
        assertEquals(1433.33, avgSalary, 0.01);
    }

    @Test
    public void testCalculateAverageAge() {
        double avgAgeUnemployed = ExampleStreams.calculateAverageAge(testPersons);
        assertEquals(16.5, avgAgeUnemployed, 0.01);
    }

    @Test
    public void testGroupByEmploymentStatus() {
        Map<Boolean, List<ExampleStreams.Person>> grouped = ExampleStreams.groupByEmploymentStatus(testPersons);
        assertEquals(3, grouped.get(true).size());
        assertEquals(2, grouped.get(false).size());
    }

    @Test
    public void testGroupByFirstName() {
        Map<String, List<ExampleStreams.Person>> grouped = ExampleStreams.groupByFirstName(testPersons);
        assertEquals(1, grouped.get("John").size());
        assertEquals(1, grouped.get("Lucas").size());
    }

    // =========================================================================
    // TESTS FOR THE ADDITIONAL ASSIGNMENT EXERCISES (3 TO 9)
    // =========================================================================

    @Test
    public void testGetLongestStringLength() {
        List<String> strings = Arrays.asList("Java", "Stream", "API", "LambdaExpressions");
        assertEquals(17, ExampleStreams.getLongestStringLength(strings));
    }

    @Test
    public void testSumOfEvenNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertEquals(30, ExampleStreams.sumOfEvenNumbers(numbers));
    }

    @Test
    public void testConvertToUppercase() {
        List<String> strings = Arrays.asList("hello", "world");
        List<String> expected = Arrays.asList("HELLO", "WORLD");
        assertEquals(expected, ExampleStreams.convertToUppercase(strings));
    }

    @Test
    public void testGroupByFirstLetter() {
        List<String> words = Arrays.asList("apple", "apricot", "banana", "cherry");
        Map<Character, List<String>> result = ExampleStreams.groupByFirstLetter(words);
        assertEquals(2, result.get('a').size());
        assertEquals(1, result.get('b').size());
    }

    @Test
    public void testCountWordOccurrences() throws IOException {
        Path tempFile = Files.createTempFile("test_stream", ".txt");
        Files.write(tempFile, Arrays.asList("Java stream API is awesome.", "Count word occurrences in java file."));

        long count = ExampleStreams.countWordOccurrences(tempFile.toString(), "java");
        assertEquals(2, count);

        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testFilterAndSortElectronics() {
        List<Product> products = Arrays.asList(
                new Product("Laptop", 1200.0, "Electronics"),
                new Product("Shoes", 80.0, "Footwear"),
                new Product("Phone", 600.0, "Electronics")
        );

        List<Product> result = ExampleStreams.filterAndSortElectronics(products);
        assertEquals(2, result.size());
        assertEquals(600.0, result.get(0).getPrice());
    }

    @Test
    public void testCalculateCompletedOrdersRevenue() {
        List<Order> orders = Arrays.asList(
                new Order(1, 250.0, "completed"),
                new Order(2, 100.0, "pending"),
                new Order(3, 450.5, "completed")
        );

        assertEquals(700.5, ExampleStreams.calculateCompletedOrdersRevenue(orders));
    }
}