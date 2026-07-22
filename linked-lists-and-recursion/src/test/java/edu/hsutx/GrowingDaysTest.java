package edu.hsutx;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class GrowingDaysTest {

    @Test
    public void testConstructorAndInitialValuesWithArray() {
        double[] degrees = {5, 10, 15, 20, 25};
        double threshold = 10;
        GrowingDays growingDays = new GrowingDays(degrees, threshold);

        double[] expectedDegreeDays = {-5, 0, 5, 10, 15};
        double[] expectedCumulativeDegreeDays = {0, 0, 5, 15, 30};

        assertArrayEquals(expectedDegreeDays, growingDays.degreeDays);
        assertArrayEquals(expectedCumulativeDegreeDays, growingDays.cumulativeDegreeDays);
    }

    @Test
    public void testConstructorAndInitialValuesWithCsv() {
        String csv = "data/Abilene-2023-Weather.csv";
        double threshold = 51;
        GrowingDays growingDays = new GrowingDays(csv, threshold);

        assertEquals(15.599999999999994,growingDays.degreeDays[10], 0.0002);
        assertEquals( 18.900000000000006, growingDays.degreeDays[100], 0.0002);
        assertEquals(43.9, growingDays.degreeDays[217], 0.0002);
        assertEquals(89.1, growingDays.cumulativeDegreeDays[21], 0.0002);
        assertEquals(771.6, growingDays.cumulativeDegreeDays[121], 0.0002);
        assertEquals(3877.50, growingDays.cumulativeDegreeDays[221], 0.0002);
    }

    @Test
    public void testGetDayOverCDThreshold() {
        double[] degrees = {5, 10, 15, 20, 25};
        double threshold = 10;
        GrowingDays growingDays = new GrowingDays(degrees, threshold);

        assertEquals(2, growingDays.getDayOverCDThreshold(5));
        assertEquals(3, growingDays.getDayOverCDThreshold(15));
        assertEquals(-1,growingDays.getDayOverCDThreshold(50)); // Expects -1 when threshold is too high
    }

    @Test
    public void testGetDayOverCDThresholdWithCsv() {
        String csv = "data/Abilene-2023-Weather.csv";
        double threshold = 51;
        GrowingDays growingDays = new GrowingDays(csv, threshold);

        assertEquals(64, growingDays.getDayOverCDThreshold(120));
        assertEquals(156, growingDays.getDayOverCDThreshold(1500));
        assertEquals(-1,growingDays.getDayOverCDThreshold(10000)); // Expects -1 when threshold is too high
    }

    @Test
    public void testGetDayOverCDHelper() {
        double[] degrees = {5, 10, 15, 20, 25};
        double threshold = 10;
        GrowingDays growingDays = new GrowingDays(degrees, threshold);

        assertEquals(2, growingDays.getDayOverCDHelper(0, degrees.length - 1, 5));
        assertEquals(3, growingDays.getDayOverCDHelper(0, degrees.length - 1, 15));
    }
}