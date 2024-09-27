import org.example.Task_1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task_1_Test {
    List<Task_1.Apple> inventory = new ArrayList<>();

    @BeforeEach
    void setUp(){
        inventory = Arrays.asList(
                new Task_1.Apple(80, Task_1.Color.GREEN),
                new Task_1.Apple(155, Task_1.Color.GREEN),
                new Task_1.Apple(120, Task_1.Color.RED)
        );
    }

    @Test
    void testMainMethod() {
        String[] args = {};
        Task_1.main(args); // Test the main method
    }

    @Test
    void testTask1Constructor() {
        Task_1 task = new Task_1(); // Test the constructor
    }

    @Test
    void testFilterApplesByColor() {

        List<Task_1.Apple> greenApples = Task_1.filterApplesByColor(inventory, Task_1.Color.GREEN);
        assertEquals(2, greenApples.size());
        assertEquals(Task_1.Color.GREEN, greenApples.get(0).getColor());

        List<Task_1.Apple> redApples = Task_1.filterApplesByColor(inventory, Task_1.Color.RED);
        assertEquals(1, redApples.size());
        assertEquals(Task_1.Color.RED, redApples.get(0).getColor());
    }

    @Test
    void testFilterWithAppleWeightPredicate() {
        List<Task_1.Apple> apples = Task_1.filter(inventory, new Task_1.AppleWeightPredicate());
        assertEquals(1, apples.size());
        assertTrue(apples.stream().allMatch(apple -> apple.getWeight() > 120));
    }

    @Test
    void testFilterWithAppleColorAndWeightPredicate() {
        List<Task_1.Apple> apples = Task_1.filter(inventory, new Task_1.AppleColorAndWeightPredicate());
        assertTrue(apples.isEmpty());
    }

    @Test
    void testFilterWithAnonymousPredicate() {
        List<Task_1.Apple> apples = Task_1.filter(inventory, new Task_1.ApplePredicate() {
            @Override
            public boolean test(Task_1.Apple apple) {
                return apple.getColor().equals(Task_1.Color.RED) && apple.getWeight() >= 120;
            }
        });

        assertEquals(1, apples.size());
        assertEquals(Task_1.Color.RED, apples.get(0).getColor());
        assertEquals(120, apples.get(0).getWeight());
    }
}
