package edu.hsutx;

/**
 * A class for counting lines in text files
 *
 * @author  Terry Sergeant, with minor edits by Todd Dole
 * @version for Data Structures
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LineCounter {



    public static void mainAlt(String[] args) throws Exception {
        if (args == null || args.length < 1) {
            System.out.println("Usage: java LineCounter name_of_directory");
            throw new Exception("Invalid number of arguments");
        }

        File folder = new File(args[0]);
        if (!folder.isDirectory()) {
            System.out.println("Folder does not exist");
            throw new Exception("Folder does not exist");
        }

        // We want to work with .txt files
        FilenameFilter txtFileFilter = (dir, name) -> name.endsWith(".txt");

        // get list of files and display their length
        CodeTimer timer = new CodeTimer();
        File[] files = folder.listFiles(txtFileFilter);

        timer.start();

        if (files != null) {
            for (File file : files) {
                countLines(args[0] + "/" + file.getName());
            }
        }

        timer.stop();

        System.out.println("==========================================");
        System.out.println("Files processed: " + (files==null ? 0 : files.length));
        System.out.println("Time elapsed     : " + timer);
        System.out.println("==========================================");


    }

    public static void countLines(String filename) {
        int count = 0;
        try (BufferedReader f = new BufferedReader(new FileReader(filename))) {
            while (f.readLine() != null) count++;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(filename + " has " + count + " lines");
    }

    public static void main(String[] args) throws Exception {
        // TODO - Modify this to use threads
        if (args == null || args.length < 1) {
            System.out.println("Usage: java LineCounter name_of_directory");
            throw new Exception("Invalid number of arguments");
        }

        File folder = new File(args[0]);
        if (!folder.isDirectory()) {
            System.out.println("Folder does not exist");
            throw new Exception("Folder does not exist");
        }

        // We want to work with .txt files
        FilenameFilter txtFileFilter = (dir, name) -> name.endsWith(".txt");
        File[] files = folder.listFiles(txtFileFilter);

        // get list of files and display their length
        CodeTimer timer = new CodeTimer();
        timer.start();

        if (files != null && files.length > 0) {

            // create a synchronized list that can store
            // the results from all threads
            List<LineCounterContainer> results = Collections.synchronizedList(new ArrayList<>());
            // get max number of threads based on available processors
            int maxThreads = Runtime.getRuntime().availableProcessors();
            // create list to track threads
            List<Thread> threadCount = new ArrayList<>();

            // loop through files, assigning a available thread
            for (File file: files) {
                // create and start new thread
                Thread thread = new Thread(new Threads(args[0] + "/" + file.getName(), results));
                thread.start();
                threadCount.add(thread);

                // if using max number of threads available
                if (threadCount.size() >= maxThreads) {
                    // wait for all threads to be done and clear list
                    for (Thread thr : threadCount) {
                        // main thread waits until next thread is done
                        thr.join();
                    }
                    threadCount.clear();
                }
            }

            // continue once all threads are done
            for (Thread thr : threadCount) {
                thr.join();
            }


            // print out results
            for (LineCounterContainer result : results) {
                System.out.println(result.filename + " has " + result.count + " lines");
            }

        }

        timer.stop();

        System.out.println("==========================================");
        System.out.println("Files processed: " + (files==null ? 0 : files.length));
        System.out.println("Time elapsed     : " + timer);
        System.out.println("==========================================");
    }

    public static int countLinesAlternate(String filename) {
        int count = 0;

        try (BufferedReader f = new BufferedReader(new FileReader(filename))) {
            while (f.readLine() != null) count++;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return count;
    }
}
