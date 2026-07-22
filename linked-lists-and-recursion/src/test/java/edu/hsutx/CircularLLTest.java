package edu.hsutx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CircularLLTest {

    private CircularLL<String> circularLL;

    @BeforeEach
    public void setUp() {
        circularLL = new CircularLL<>();
    }

    @Test
    public void testIsEmpty_OnNewList_ShouldReturnTrue() {
        // Verify that a new list is empty
        assertTrue(circularLL.isEmpty());
    }

    @Test
    public void testAddFirst_OnEmptyList_ShouldAddElement() {
        // Add an element to the front
        circularLL.addFirst("Monday");
        // Verify that the list is no longer empty and contains the correct element
        assertFalse(circularLL.isEmpty());
        assertEquals("Monday", circularLL.first());
    }

    @Test
    public void testAddFirst_AddMultipleElements_ShouldAddInCorrectOrder() {
        // Add multiple elements
        circularLL.addFirst("Tuesday");
        circularLL.addFirst("Monday");
        // Verify that elements are added in the correct order
        assertEquals("Monday", circularLL.first());
        circularLL.rotate(); // Move to the next element
        assertEquals("Tuesday", circularLL.first());
    }

    @Test
    public void testAddLast_OnEmptyList_ShouldAddElement() {
        // Add an element to the end
        circularLL.addLast("Monday");
        // Verify that the list is no longer empty and contains the correct element
        assertFalse(circularLL.isEmpty());
        assertEquals("Monday", circularLL.first());
    }

    @Test
    public void testAddLast_AddMultipleElements_ShouldAddInCorrectOrder() {
        // Add multiple elements to the end
        circularLL.addLast("Monday");
        circularLL.addLast("Tuesday");
        // Verify that elements are added in the correct order
        assertEquals("Monday", circularLL.first());
        circularLL.rotate(); // Move to the next element
        assertEquals("Tuesday", circularLL.first());
    }

    @Test
    public void testRemoveFirst_OnNonEmptyList_ShouldRemoveAndReturnFirstElement() {
        // Add elements and remove the first one
        circularLL.addLast("Monday");
        circularLL.addLast("Tuesday");

        // Verify that the correct element is removed
        assertEquals("Monday", circularLL.removeFirst());
        assertEquals("Tuesday", circularLL.first());
        // Verify that the list size is reduced
        assertFalse(circularLL.isEmpty());
    }

    @Test
    public void testRemoveFirst_OnSingleElementList_ShouldEmptyList() {
        // Add a single element and remove it
        circularLL.addLast("Monday");
        assertEquals("Monday", circularLL.removeFirst());
        // Verify that the list is now empty
        assertTrue(circularLL.isEmpty());
    }

    @Test
    public void testRemoveFirst_OnEmptyList_ShouldReturnNull() {
        // Verify that removing from an empty list returns null
        assertNull(circularLL.removeFirst());
    }

    @Test
    public void testRotate_OnNonEmptyList_ShouldRotateElements() {
        // Add elements and rotate
        circularLL.addLast("Monday");
        circularLL.addLast("Tuesday");
        circularLL.addLast("Wednesday");

        // Verify that rotation works as expected
        assertEquals("Monday", circularLL.first());
        circularLL.rotate();
        assertEquals("Tuesday", circularLL.first());
        circularLL.rotate();
        assertEquals("Wednesday", circularLL.first());
        circularLL.rotate();
        assertEquals("Monday", circularLL.first());
    }

    @Test
    public void testRotate_OnEmptyList_ShouldDoNothing() {
        // Verify that rotating an empty list does nothing
        circularLL.rotate();
        assertTrue(circularLL.isEmpty());
    }

    @Test
    public void testFirst_OnNonEmptyList_ShouldReturnFirstElement() {
        // Add elements and check the first element
        circularLL.addLast("Monday");
        circularLL.addLast("Tuesday");
        // Verify that the first element is correct
        assertEquals("Monday", circularLL.first());
    }

    @Test
    public void testFirst_OnEmptyList_ShouldReturnNull() {
        // Verify that first() on an empty list returns null
        assertNull(circularLL.first());
    }
}
