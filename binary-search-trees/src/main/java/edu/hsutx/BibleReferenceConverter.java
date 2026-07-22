package edu.hsutx;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Todd Dole
 * @version 1.0
 * Methods to convert a bible reference to a unique int key
 * E.G. "Genesis 1:1" becomes 101001001
 * "Psalms 119:121 becomes 119119121
 */
public class BibleReferenceConverter {

    // Map to store book names and their corresponding numbers
    private static final Map<String, Integer> bookNumbers = new HashMap<>();
    private static final Map<Integer, String> bookNames = new HashMap<>();

    static {
        // Populate the book numbers from Genesis (101) to Revelation (166)
        bookNumbers.put("Genesis", 101);
        bookNumbers.put("Exodus", 102);
        bookNumbers.put("Leviticus", 103);
        bookNumbers.put("Numbers", 104);
        bookNumbers.put("Deuteronomy", 105);
        bookNumbers.put("Joshua", 106);
        bookNumbers.put("Judges", 107);
        bookNumbers.put("Ruth", 108);
        bookNumbers.put("1 Samuel", 109);
        bookNumbers.put("2 Samuel", 110);
        bookNumbers.put("1 Kings", 111);
        bookNumbers.put("2 Kings", 112);
        bookNumbers.put("1 Chronicles", 113);
        bookNumbers.put("2 Chronicles", 114);
        bookNumbers.put("Ezra", 115);
        bookNumbers.put("Nehemiah", 116);
        bookNumbers.put("Esther", 117);
        bookNumbers.put("Job", 118);
        bookNumbers.put("Psalms", 119);
        bookNumbers.put("Proverbs", 120);
        bookNumbers.put("Ecclesiastes", 121);
        bookNumbers.put("Song of Solomon", 122);
        bookNumbers.put("Isaiah", 123);
        bookNumbers.put("Jeremiah", 124);
        bookNumbers.put("Lamentations", 125);
        bookNumbers.put("Ezekiel", 126);
        bookNumbers.put("Daniel", 127);
        bookNumbers.put("Hosea", 128);
        bookNumbers.put("Joel", 129);
        bookNumbers.put("Amos", 130);
        bookNumbers.put("Obadiah", 131);
        bookNumbers.put("Jonah", 132);
        bookNumbers.put("Micah", 133);
        bookNumbers.put("Nahum", 134);
        bookNumbers.put("Habakkuk", 135);
        bookNumbers.put("Zephaniah", 136);
        bookNumbers.put("Haggai", 137);
        bookNumbers.put("Zechariah", 138);
        bookNumbers.put("Malachi", 139);
        bookNumbers.put("Matthew", 140);
        bookNumbers.put("Mark", 141);
        bookNumbers.put("Luke", 142);
        bookNumbers.put("John", 143);
        bookNumbers.put("Acts", 144);
        bookNumbers.put("Romans", 145);
        bookNumbers.put("1 Corinthians", 146);
        bookNumbers.put("2 Corinthians", 147);
        bookNumbers.put("Galatians", 148);
        bookNumbers.put("Ephesians", 149);
        bookNumbers.put("Philippians", 150);
        bookNumbers.put("Colossians", 151);
        bookNumbers.put("1 Thessalonians", 152);
        bookNumbers.put("2 Thessalonians", 153);
        bookNumbers.put("1 Timothy", 154);
        bookNumbers.put("2 Timothy", 155);
        bookNumbers.put("Titus", 156);
        bookNumbers.put("Philemon", 157);
        bookNumbers.put("Hebrews", 158);
        bookNumbers.put("James", 159);
        bookNumbers.put("1 Peter", 160);
        bookNumbers.put("2 Peter", 161);
        bookNumbers.put("1 John", 162);
        bookNumbers.put("2 John", 163);
        bookNumbers.put("3 John", 164);
        bookNumbers.put("Jude", 165);
        bookNumbers.put("Revelation", 166);

        // Create the reverse map for converting keys back to book names
        for (Map.Entry<String, Integer> entry : bookNumbers.entrySet()) {
            bookNames.put(entry.getValue(), entry.getKey());
        }
        // TODO - Optional, bonus points.  You can add additional bookNumbers.put for abbreviations:
        // e.g. bookNumbers.put("Gen", 101);


    }

    // Function to convert Bible reference to a unique integer key
    public static int convertToKey(String reference) {
        // Example: reference = "Genesis 3:24" or "1 Corinthians 5:12"
        String[] parts = reference.split(" ");

        // Extract book number
        String bookName = parts[0];
        if (parts.length > 2) {
            // Handling books with multiple words (e.g., "1 Corinthians")
            bookName += " " + parts[1];
        }
        if (parts.length > 3) {
            // Handling books with three words (e.g., "Song of Solomon")
            bookName += " " + parts[2];
        }
        int bookNumber = bookNumbers.getOrDefault(bookName, -1);
        if (bookNumber == -1) {
            throw new IllegalArgumentException("Invalid book name: " + bookName);
        }

        // Extract chapter and verse
        String[] chapterAndVerse = parts[parts.length - 1].split(":");
        int chapter = Integer.parseInt(chapterAndVerse[0]);
        int verse = Integer.parseInt(chapterAndVerse[1]);

        // Calculate key using the updated formula
        return (bookNumber * 1000000) + (chapter * 1000) + verse;
    }

    // Function to convert an integer key back into a string reference (e.g., "Genesis 1:1")
    public static String convertToReference(int key) {
        int bookNumber = key / 1000000;
        int chapter = (key / 1000) % 1000;
        int verse = key % 1000;

        String bookName = bookNames.get(bookNumber);
        if (bookName == null) {
            throw new IllegalArgumentException("Invalid key: " + key);
        }
        return bookName + " " + chapter + ":" + verse;
    }

    // Function to parse a line from the Bible text file
    public static SimpleEntry<String, String> parseBibleLine(String line) {
        // Split the line into reference and verse text
        int firstColon = line.indexOf(':');
        int secondSpace = line.indexOf(' ', firstColon + 1);
        String reference = line.substring(0, secondSpace);
        String verseText = line.substring(secondSpace + 1);

        // Convert the reference to a key
        int key = convertToKey(reference);

        // Return a pair with the key as a string and the verse text
        return new SimpleEntry<>(String.valueOf(key), verseText);
    }

}
