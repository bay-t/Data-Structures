package edu.hsutx;

/**
 * This is a container class to store the filename and
 * number of lines in a file
 */

public class LineCounterContainer {

    // stores file and lines
    public String filename;
    public int count;

    // constructor to set file and lines
    public LineCounterContainer(String filename, int count) {
        this.filename = filename;
        this.count = count;
    }
}
