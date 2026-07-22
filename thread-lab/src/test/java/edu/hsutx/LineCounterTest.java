package edu.hsutx;

/**
 * A class for testing LineCounter.java
 *
 * @author  Todd Dole, adapted from Terry Sergeant
 * @version for Data Structures
 *
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LineCounterTest {

    @Test
    void testMain() {
        try {
            LineCounter.main(new String[]{});
            fail("Should have thrown 'Invalid Number of Arguments' Exception");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testMain2() {
        try {
            LineCounter.main(new String[] {"textfiles"});

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testMainAlternate() {
        try {
            LineCounter.mainAlternate(new String[] {"textfiles"});

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
