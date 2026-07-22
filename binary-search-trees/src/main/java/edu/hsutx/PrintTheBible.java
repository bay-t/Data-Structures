package edu.hsutx;

/**
 * @author Todd Dole
 * @version 1.0
 * Recursively prints the text of the entire Bible to standard output
 * */

public class PrintTheBible {
    public static void main(String[] args) {
        BibleTree bible = new BibleTree("data/reordered_geneva.txt");
        bible.printBible();
    }
}
