package com.example.iterable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class BagTest {

    private Bag<String> bag;

    @BeforeEach
    void setUp() {
        bag = new Bag<>();
    }

    @Test
    void testInitialState() {
        assertTrue(bag.isEmpty());
        assertEquals(0, bag.size());
    }

    @Test
    void testAddAndSize() {
        bag.add("Apple");
        assertFalse(bag.isEmpty());
        assertEquals(1, bag.size());

        bag.add("Banana");
        assertEquals(2, bag.size());

        // Bags allow duplicates
        bag.add("Apple");
        assertEquals(3, bag.size());
    }

    @Test
    void testContains() {
        assertFalse(bag.contains("Apple"));

        bag.add("Apple");
        bag.add("Banana");

        assertTrue(bag.contains("Apple"));
        assertTrue(bag.contains("Banana"));
        assertFalse(bag.contains("Cherry"));
    }

    @Test
    void testRemove() {
        bag.add("Apple");
        bag.add("Banana");
        bag.add("Apple"); // Duplicate

        // Remove item that exists
        assertTrue(bag.remove("Apple"));
        assertEquals(2, bag.size());
        assertTrue(bag.contains("Apple")); // The other Apple should still be there

        // Remove the remaining item
        assertTrue(bag.remove("Apple"));
        assertEquals(1, bag.size());
        assertFalse(bag.contains("Apple"));

        // Remove item that doesn't exist
        assertFalse(bag.remove("Cherry"));
        assertEquals(1, bag.size());
    }

    @Test
    void testRemoveFromEmptyBag() {
        assertFalse(bag.remove("Apple"));
        assertTrue(bag.isEmpty());
    }

    @Test
    void testIteratorNormalOperation() {
        bag.add("A");
        bag.add("B");
        bag.add("C");

        Iterator<String> iterator = bag.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("C", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorWithEnhancedForLoop() {
        bag.add("One");
        bag.add("Two");
        bag.add("Three");

        int count = 0;
        for (String item : bag) {
            assertNotNull(item);
            count++;
        }
        assertEquals(3, count);
    }

    @Test
    void testIteratorOnEmptyBag() {
        Iterator<String> iterator = bag.iterator();
        assertFalse(iterator.hasNext());

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testIteratorRemove() {
        bag.add("X");
        bag.add("Y");
        bag.add("Z");

        Iterator<String> iterator = bag.iterator();
        iterator.next(); // Point to X
        iterator.remove(); // Remove X

        assertEquals(2, bag.size());
        assertFalse(bag.contains("X"));
        assertTrue(bag.contains("Y"));
        assertTrue(bag.contains("Z"));
    }

    @Test
    void testAddNull() {
        bag.add(null);
        assertFalse(bag.isEmpty());
        assertEquals(1, bag.size());
        assertTrue(bag.contains(null));

        assertTrue(bag.remove(null));
        assertTrue(bag.isEmpty());
    }

    @Test
    void testForEach() {
        bag.add("One");
        bag.add("Two");
        bag.add("Three");

        List<String> result = new ArrayList<>();
        bag.forEach(result::add);

        assertEquals(3, result.size());
        assertTrue(result.contains("One"));
        assertTrue(result.contains("Two"));
        assertTrue(result.contains("Three"));
    }

    @Test
    void testForEachOnEmptyBag() {
        AtomicInteger count = new AtomicInteger(0);
        bag.forEach(item -> count.incrementAndGet());
        assertEquals(0, count.get());
    }

    @Test
    void testSpliterator() {
        bag.add("A");
        bag.add("B");
        bag.add("C");

        Spliterator<String> spliterator = bag.spliterator();
        assertEquals(3, spliterator.estimateSize());

        // Test tryAdvance
        assertTrue(spliterator.tryAdvance(item -> assertEquals("A", item)));
        assertEquals(2, spliterator.estimateSize());

        // Test forEachRemaining
        List<String> remaining = new ArrayList<>();
        spliterator.forEachRemaining(remaining::add);

        assertEquals(2, remaining.size());
        assertTrue(remaining.contains("B"));
        assertTrue(remaining.contains("C"));
        assertEquals(0, spliterator.estimateSize());
    }

    @Test
    void testSpliteratorOnEmptyBag() {
        Spliterator<String> spliterator = bag.spliterator();
        assertEquals(0, spliterator.estimateSize());
        assertFalse(spliterator.tryAdvance(item -> fail("Should not be called")));
    }
}
