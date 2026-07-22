package edu.hsutx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;

/**
 * @author Todd Dole
 * @version 1.0
 * Creates a binary search tree of all Bible verses indexed by an int representation of the
 * scripture reference.
 *
 *
 */
public class BibleTree {
    BinarySearchTree bst;

    /**
     * Constructor reads Bible from a text file and creates a Binary Search Tree
     * @param filePath - path to the Bible text file
     */
    public BibleTree(String filePath) {
        this.bst = new BinarySearchTree();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Process each line
                SimpleEntry<String, String> keyValuePair = BibleReferenceConverter.parseBibleLine(line);
                String key = keyValuePair.getKey();
                String value = keyValuePair.getValue();

                // Convert key to integer
                int intKey = Integer.parseInt(key);

                // Insert the key and value into the Binary Search Tree
                this.bst.insert(intKey, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Returns the text of a verse for a given reference, or null if not found.
    public String getVerse(String reference) {
        int key = BibleReferenceConverter.convertToKey(reference);
        return this.bst.getValue(key);
    }

    // Deletes a verse.  Use with caution! (see Rev. 22:19)
    public void deleteVerse(String reference) {
        int key = BibleReferenceConverter.convertToKey(reference);
        this.bst.delete(key);
    }

    public void printBible() {
        this.bst.printAllData();
    }
}
