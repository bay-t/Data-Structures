[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/cOCU-Vt7)
# Binary Search Trees

### Assignment by Todd Dole for CSCI-3323 at Hardin-Simmons University.

This assignment requires the student to implement code for a binary search tree class (BinarySearchTree).

Junit5 unit tests are included for each method in the BinarySearchTree class.  Students must get all the tests to pass.  Tests can be run using the Gradle command:  
  
```./gradlew build```

BinarySearchTree is used by the BibleTree class to store the text of the Bible, with each verse of the Bible being stored as one node in the tree.



The project includes two applications:

```./gradlew runVerseLookup  ```
  
  Accepts a verse reference from Standard Input, and displays the text of the verse.  

```./gradlew runBibleTree  ```
  
  Recursively searches the tree and prints each node, producing the entire text of the Bible as output.


## A note on the translation
This project uses the Geneva Bible, a non-copywrited translation which was produced in 1599.  
This translation was brought to America on the Mayflower, and widely used by the Pilgrims and early protestant settlers.  
The data directory contains two files:
* geneva.txt is the original text file containing the full text of the Geneva Bible, one verse per line.
* reordered_geneva.txt contains the full text, but verses are reordered in a sequence that is optimal for producing a balanced tree

For reference, the python script wich produced reordered_geneva.txt is included in the data directory.