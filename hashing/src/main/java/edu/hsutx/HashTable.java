package edu.hsutx;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Todd Dole
 *
 * @param <V> - Type for the value portion of the Hash class
 */
public class HashTable<V> {

    /**
     * Node class for storing key-value pairs
     *
     * @param <V> value
     */
    private static class Node<V> {
        String key;
        V value;


        Node(String key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Default initial capacity
     */
    private static final int INITIAL_CAPACITY = 167;
    private List<Node<V>>[] table; // Array of linked lists for chaining
    private int size;
    int collisions; //You can use this to keep track of how well your hash table is doing.  Each time you have a new collision, add 1
                    // Reset to 0 whenever you start to resize

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new LinkedList[INITIAL_CAPACITY];
        size = 0;
        collisions = 0;
    }

    /**
     * Basic hash function.
     *
     * @param key
     * @return index value appropriate for table
     */
    private int hashIndex(String key) {
        // establish hashCode int
        int hashCode = 0;

        // loop through each char of the string
        for (int i=0; i<key.length(); i++) {
            // convert string to an int, and lower collision chance
            hashCode = (hashCode * 37) + (key.charAt(i) * (i + 3));
        }

        // lower collision chance even more
        hashCode *= 67;

        // returns a hashCode that fits in the table
        return (Math.abs(hashCode) % table.length);
    }

    /**
     * Method to put a key-value pair into the hash table
     *
     * @param key
     * @param value
     */
    public void put(String key, V value) {

        // make int to contain index key
        int index = hashIndex(key);

        // checks if index is null
        if (table[index] == null ) {
            // add a new index in the array
            // and set key and value of new index
            table[index] = new LinkedList<>();
            table[index].add(new Node<>(key, value));
            size++;
        }

        // if index is not null
        else {
            // iterate through table
            for (Node<V> node : table[index]) {
                // if the node's key equals the given key
                // then change the value to the given value
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
            }
            // otherwise, set key and value to new index
            table[index].add(new Node<>(key, value));
            size++;
            collisions ++;
        }
    }

    /**
     * Method to get a value by its key
     *
     * @param key
     * @return
     */
    public V get(String key) {

        // make an int to contain index key
        int index = hashIndex(key);

        // checks if the index is null
        // if so, return null
        if (table[index] == null) {
            return null;
        }

        // iterate through table
        for (Node<V> node : table[index]) {
            // if the index has a key then
            // return the value
            if(node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }

    /**
     * Method to remove a key-value pair
     *
     * @param key
     */
    public void remove(String key) {

        // create int to contain key
        int index = hashIndex(key);

        // checks to see if index is null
        if (table[index] == null) {
            return;
        }

        // loop through table
        for (int i=0; i<table[index].size(); i++) {
            // get the index for the node
            // checks index key with the given key
            Node<V> node = table[index].get(i);
            if (node.key.equals(key)) {
                // remove the index and decrease the size
                table[index].remove(i);
                size--;
                return;
            }
        }

    }

    /**
     * Optional: Method to resize and rehash the table
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        // TODO - Implement code.  This one is optional, but good practice.
    }

    /**
     * Method to get the current size of the hash table
     *
     * @return size
     */
    public int size() {
        return size;
    }

    public int getCollisions() {
        return this.collisions;
    }
}
