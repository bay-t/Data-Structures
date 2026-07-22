package edu.hsutx;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HashTableTest {

    private HashTable<Integer> hashTable;

    @BeforeEach
    public void setUp() {
        hashTable = new HashTable<>();
    }

    @Test
    public void testPutAndGet() {
        hashTable.put("Genesis", 101);
        assertEquals(101, hashTable.get("Genesis"));
    }

    @Test
    public void testGetNonExistentKey() {
        assertNull(hashTable.get("NonExistent"));
    }

    @Test
    public void testRemove() {
        hashTable.put("Exodus", 102);
        hashTable.remove("Exodus");
        assertNull(hashTable.get("Exodus"));
    }

    @Test
    // Tests that less than half of hash entries have collisions.
    public void testCollisions() {
        hashTable.put("Genesis", 101);
        hashTable.put("Exodus", 102);
        hashTable.put("Leviticus", 103);
        hashTable.put("Numbers", 104);
        hashTable.put("Deuteronomy", 105);
        hashTable.put("Joshua", 106);
        hashTable.put("Judges", 107);
        hashTable.put("Ruth", 108);
        hashTable.put("1 Samuel", 109);
        hashTable.put("2 Samuel", 110);
        hashTable.put("1 Kings", 111);
        hashTable.put("2 Kings", 112);
        hashTable.put("1 Chronicles", 113);
        hashTable.put("2 Chronicles", 114);
        hashTable.put("Ezra", 115);
        hashTable.put("Nehemiah", 116);
        hashTable.put("Esther", 117);
        hashTable.put("Job", 118);
        hashTable.put("Psalms", 119);
        hashTable.put("Proverbs", 120);
        hashTable.put("Ecclesiastes", 121);
        hashTable.put("Song of Solomon", 122);
        hashTable.put("Isaiah", 123);
        hashTable.put("Jeremiah", 124);
        hashTable.put("Lamentations", 125);
        hashTable.put("Ezekiel", 126);
        hashTable.put("Daniel", 127);
        hashTable.put("Hosea", 128);
        hashTable.put("Joel", 129);
        hashTable.put("Amos", 130);
        hashTable.put("Obadiah", 131);
        hashTable.put("Jonah", 132);
        hashTable.put("Micah", 133);
        hashTable.put("Nahum", 134);
        hashTable.put("Habakkuk", 135);
        hashTable.put("Zephaniah", 136);
        hashTable.put("Haggai", 137);
        hashTable.put("Zechariah", 138);
        hashTable.put("Malachi", 139);
        hashTable.put("Matthew", 140);
        hashTable.put("Mark", 141);
        hashTable.put("Luke", 142);
        hashTable.put("John", 143);
        hashTable.put("Acts", 144);
        hashTable.put("Romans", 145);
        hashTable.put("1 Corinthians", 146);
        hashTable.put("2 Corinthians", 147);
        hashTable.put("Galatians", 148);
        hashTable.put("Ephesians", 149);
        hashTable.put("Philippians", 150);
        hashTable.put("Colossians", 151);
        hashTable.put("1 Thessalonians", 152);
        hashTable.put("2 Thessalonians", 153);
        hashTable.put("1 Timothy", 154);
        hashTable.put("2 Timothy", 155);
        hashTable.put("Titus", 156);
        hashTable.put("Philemon", 157);
        hashTable.put("Hebrews", 158);
        hashTable.put("James", 159);
        hashTable.put("1 Peter", 160);
        hashTable.put("2 Peter", 161);
        hashTable.put("1 John", 162);
        hashTable.put("2 John", 163);
        hashTable.put("3 John", 164);
        hashTable.put("Jude", 165);
        hashTable.put("Revelation", 166);
        assertTrue(hashTable.getCollisions() < 33);
    }
}
