package edu.hsutx;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/**
 * Sets up GrowingDays using data from CSV file and 51 degree threshold
 * Reads target cumulative GDD from standard input, then calculates the day that cGDD is achieved
 * Calculates the day of the week using a circular LL
 * outputs the day number and the day of the week
 * outputs -1 and "N/A" if the cGDD is not ever reached
 */
public class Main {
    public static void main(String[] args) {
        // The path to your CSV file
        String csvFile = "data/Abilene-2023-Weather.csv";
        Scanner scanner = new Scanner(System.in);
        GrowingDays gd = new GrowingDays(csvFile, 51);

        int threshold = scanner.nextInt();
        int yearDay = gd.getDayOverCDThreshold(threshold);
        System.out.println(yearDay);

        CircularLL<String> weekdays = new CircularLL<String>();
        weekdays.addFirst("Saturday");
        weekdays.addFirst("Friday");
        weekdays.addFirst("Thursday");
        weekdays.addFirst("Wednesday");
        weekdays.addFirst("Tuesday");
        weekdays.addFirst("Monday");
        weekdays.addFirst("Sunday");
        for (int i = 1; i < yearDay; i++) weekdays.rotate();

        if (yearDay>=0) System.out.println(weekdays.first());
        else System.out.print("N/A");

        scanner.close();
    }
}