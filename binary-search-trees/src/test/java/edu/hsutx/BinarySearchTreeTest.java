package edu.hsutx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    private BinarySearchTree bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree();
    }

    @Test
    void testIsEmpty() {
        assertTrue(bst.isEmpty(), "Tree should be empty initially");
        bst.insert(10, "Root");
        assertFalse(bst.isEmpty(), "Tree should not be empty after insertion");
    }

    @Test
    void testInsert() {
        bst.insert(10, "Root");
        bst.insert(5, "Left");
        bst.insert(15, "Right");

        assertEquals("Root", bst.getValue(10), "Root node should have value 'Root'");
        assertEquals("Left", bst.getValue(5), "Left child node should have value 'Left'");
        assertEquals("Right", bst.getValue(15), "Right child node should have value 'Right'");

        // Test insertion of duplicate key
        bst.insert(10, "Duplicate");
        assertEquals("Root", bst.getValue(10), "Duplicate key should not change the value");
    }

    @Test
    void testDeleteNoChildren() {
        bst.insert(10, "Root");
        bst.delete(10);

        assertTrue(bst.isEmpty(), "Tree should be empty after deleting the only node");
        assertNull(bst.getValue(10), "Deleted node should not be found");
    }

    @Test
    void testDeleteOneChild() {
        bst.insert(10, "Root");
        bst.insert(5, "Left");
        bst.delete(10);

        assertNull(bst.getValue(10), "Deleted node should not be found");
        assertEquals("Left", bst.getValue(5), "Left child should be promoted to root");
    }

    @Test
    void testDeleteTwoChildren() {
        bst.insert(10, "Root");
        bst.insert(5, "Left");
        bst.insert(15, "Right");
        bst.delete(10);

        assertNull(bst.getValue(10), "Deleted node should not be found");
        assertTrue(bst.getValue(5) != null || bst.getValue(15) != null,
                "One of the children should be promoted to root");
    }

    @Test
    void testGetValue() {
        bst.insert(10, "Root");
        assertEquals("Root", bst.getValue(10), "Should find value 'Root' for key 10");

        assertNull(bst.getValue(5), "Should return null for a non-existent key");
    }

    @Test
    void testFind() {
        bst.insert(10, "Root");
        bst.insert(5, "Left");

        assertNotNull(bst.find(10), "Find should return a node when the key exists");
        assertNotNull(bst.find(5), "Find should return a node when the key exists");
        //assertNull(bst.find(20), "Find should return null when the key does not exist");
    }

    @Test
    void testGetDepth() {
        bst.insert(10, "Root");
        bst.insert(5, "Left");
        bst.insert(15, "Right");
        bst.insert(2, "Left Left");
        bst.insert(7, "Left Right");

        assertEquals(1, bst.getDepth(10), "Depth of root node (key 10) should be 1");
        assertEquals(2, bst.getDepth(5), "Depth of node with key 5 should be 2");
        assertEquals(2, bst.getDepth(15), "Depth of node with key 15 should be 2");
        assertEquals(3, bst.getDepth(2), "Depth of node with key 2 should be 3");
        assertEquals(3, bst.getDepth(7), "Depth of node with key 7 should be 3");
        //assertEquals(0, bst.getDepth(20), "Depth should be 0 for a non-existent key");
    }
}

