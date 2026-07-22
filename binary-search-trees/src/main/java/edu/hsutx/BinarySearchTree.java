package edu.hsutx;

/**
 * @author Todd Dole
 * @version 1.3
 * Starting Code for the CSCI-3323 Binary Search Tree assignment
 * Students must complete the TODOs and get the tests to pass
 */


/**
 * A Binary Search Tree that takes int key and String value for each node
 * @author Bay Tompkins
 * @version 1.1
 */

public class BinarySearchTree {
    Node head;

    private class Node {
        public int key;
        public String value;
        public Node left;
        public Node right;
        public Node parent;
        public Node(int key, String value, Node parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }

        /**
         * method to get the depth of a node
         *
         * @return depth
         */
        public int getDepth() {
            //establishing initial depth
            int depth = 1;
            Node nd = this;
            if (nd == head) return depth;

            //loop to traverse upward until hitting head
            //counts the number of steps
            while(nd.parent != null) {
                depth ++;
                nd = nd.parent;
            }
            return depth;
        }
    }

    /**
     * Constructor class for binary search tree
     * constructs a node for bst and sets it to null
     */
    public BinarySearchTree() {
        this.head = null;
    }

    /**
     * method to insert a new node into the tree
     *
     * @param key the id of the node
     * @param value what is in the node
     */
    public void insert(int key, String value) {
        //checks to see if head is null
        //if so, creates a new node
        if(head == null) {
            head = new Node(key, value, null);
        }
        else {
            //make a new pointer node
            Node in = head;

            //check if key is less than current key
            //check if left is null to insert
            //or keep going left
            //same with right
            while (true) {
                if (key < in.key) {
                    if (in.left == null) {
                        in.left = new Node(key, value, in);
                        return;
                    }
                    in = in.left;
                } else if (key > in.key) {
                    if (in.right == null) {
                        in.right = new Node(key, value, in);
                        return;
                    }
                    in = in.right;
                }
                //key == current key, duplicate so do nothing
                else return;
            }
        }
    }

    /**
     * Deletes a key if it exists in the tree
     * handles 3 cases if there is children
     *
     * @param key
     */
    public void delete(int key) {
        // establish another pointer
        Node del = head;

        while(true) {

            //check if key is less than current key
            //check if node null
            //if not, set left as new current
            if (key < del.key) {
                if (del.left == null) {
                    return;
                }
                del = del.left;
            }

            //check if key is greater than current key
            //check if node is null
            //if not, set right as new current
            else if (key > del.key) {
                if (del.right == null) {
                    return;
                }
                del = del.right;
            }

            //cases of parent nodes having children
            else {
                //parent node, used to redirect what parent is point to
                Node parent = del.parent;

                //checks case 1: if there are no children
                //if current is a parent's left/right
                //will point parent left/right to null
                if(del.left == null && del.right == null) {
                    if (del.parent == null) {
                        head = null;
                        return;
                    }
                    if (parent.left == del) {
                        parent.left = null;
                    }
                    else parent.right = null;
                }

                //checks case 2: if there is 1 child
                //will point parent of current to child of current
                else if (del.left != null && del.right == null) {
                    if (head == del) {
                        head = del.left;
                        return;
                    }
                    if (parent.left == del) {
                        parent.left = del.left;
                    }
                    else parent.right = del.left;
                }
                else if (del.left == null && del.right != null) {
                    if (head == del) {
                        head = del.right;
                        return;
                    }
                    if (parent.left == del) {
                        parent.left = del.right;
                    }
                    else parent.right = del.right;
                }

                //checks case 3: there are 2 children
                else {
                    //establishes the node's successor as the
                    //furthest left child of the first right child
                    Node sux = del.right;
                    while (sux.left != null) {
                        sux = sux.left;
                    }

                    //copy successor's key/value
                    //make temp node and copy delete
                    int tmpkey = del.key;
                    String tmpval = del.value;
                    //make delete the successor
                    del.key = sux.key;
                    del.value = sux.value;
                    //set successor to original delete
                    sux.key = tmpkey;
                    sux.value = tmpval;


                    //delete successor recursively
                    //because I don't want to type anymore
                    delete(sux.key);
                }
            }
        }
    }

    /**
     * a method to search the binary tree via the key
     * it will return the spot where a key should be if there is none
     *
     * @param key the id of the node
     * @return key spot
     */
    Node find(int key) {
        //a search pointer
        Node search = head;

        if (head == null) return null;
        //check if key is less than current key
        //check if left is null to return
        //or keep going left
        //same with right

        while(true) {
            if (key < search.key) {
                if (search.left == null) return search;
                search = search.left;
            }

            else if (key > search.key) {
                if (search.right == null) return search;
                search = search.right;
            }
            //returns where a key should be inserted
            else return search;
        }
    }

    /**
     * a method to get the value from tree using the key
     *
     * @param key the id of the node
     * @return value associated with the key
     */
    public String getValue(int key) {
        //store the returned key from find()
        Node found = this.find(key);
        if (found == null) return null;

        //checks to see if the key exists and returns the value
        if (found.key == key) {
            return found.value;
        }
        else return null;
    }

    /**
     * method to see if the Node is empty
     * @return true/false
     */
    public boolean isEmpty() {
        if (this.head == null) return true;
        else return false;
    }

    // returns the depth of the node with key, or 0 if it doesn't exist
    public int getDepth(int key) {
        Node node=find(key);
        if (node != null) return node.getDepth();
        return 0;
    }

    /**
     * Helper method for printAllData recursive method
     */
    public void printAllData() {
        printAllData(this.head);
    }

    /**
     * recusive method to print all value's in the tree
     * in order from smallest to biggest
     *
     * @param print node pointer for what to print
     */
    private void printAllData(Node print) {
        if (print == null) return;
        printAllData(print.left);
        System.out.println(print.value);
        printAllData(print.right);
    }
}