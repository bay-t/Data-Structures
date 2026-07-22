package edu.hsutx;

import java.util.List;

/**
 * a thread class to count the number of lines in a file
 */

public class Threads implements Runnable{

    // store the file name and shared results
    private String filename;
    private List<LineCounterContainer> results;

    /**
     * A constructor method to give a newly created Thread
     * a file to process and the shared list to store count
     */
    public Threads(String filename, List<LineCounterContainer> results) {
        this.filename = filename;
        this.results = results;
    }

    @Override
    public void run() {
        // stores the number of lines in a file
        int count = LineCounter.countLinesAlternate(filename);
        // creates new container object holding
        // filename and number of lines
        results.add(new LineCounterContainer(filename, count));
    }
}
