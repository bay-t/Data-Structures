package edu.hsutx;

/**
 * @author Todd Dole
 * @version 1.0
 * Starting Code for the CSCI-3323 Red-Black Tree assignment
 * Students must complete the TODOs and get the tests to pass
 */

/**
 * A Red-Black Tree that takes int key and String value for each node.
 * Follows the properties of a Red-Black Tree:
 * 1. Every node is either red or black.
 * 2. The root is always black.
 * 3. Every leaf (NIL node) is black.
 * 4. If a node is red, then both its children are black.
 * 5. For each node, all simple paths from the node to descendant leaves have the same number of black nodes.
 */
public class RedBlackTree<E> {
    Node root;
    int size;

    protected class Node {
        public String key;
        public E value;
        public Node left;
        public Node right;
        public Node parent;
        public boolean color; // true = red, false = black

        public Node(String key, E value, Node parent, boolean color) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = null;
            this.right = null;
            this.color = color;
        }

        /**
         * a method to calculate the depth of a node
         *
         * @return depth value
         */
        public int getDepth() {
            //establishing initial depth
            int depth = 1;
            Node nd = this;

            //loop to traverse upward until hitting root
            //counts the number of steps
            while(nd.parent != null) {
                depth ++;
                nd = nd.parent;
            }
            return depth;
        }

        /**
         * A method to calculate the depth of a node while
         * only counting black notes
         *
         * @return depth value
         */
        public int getBlackDepth() {
            //establishing initial depth
            int bDepth = 1;
            Node nd = this;

            //loop to traverse upward until hitting root
            //counting the number of black nodes
            while(nd != null) {
                if (nd.color == false ) {
                    bDepth ++;
                }
                nd = nd.parent;
            }
            return bDepth;
        }
    }

    /**
     *  Make a new RedBlackTree
     */
    public RedBlackTree() {
        root = null; // Start with an empty tree.  This is the one time we can have a null ptr instead of a null key node
        size = 0;
    }

    /**
     * Inserts a new node into the RedBlackTree and rebalances the tree
     * with rotations and recoloring of nodes
     *
     * @param key - key of the node
     * @param value - value of the node
     */
    public void insert(String key, E value) {

        //checks to see if root is null
        //if so, creates a new node
        if(isEmpty()) {
            root = new Node(key, value, null, false);
            size++;
        }
        else {
            //make a new pointer node
            Node in = root;

            // loop through the tree
            while (true) {
                // compare key to insert key
                // -1 = key < in.key
                // 0 is equal
                // 1 = key > in.key
                int vsKey = key.compareTo(in.key);
                // check if node key is less than root key
                if (vsKey < 0) {
                    // if there is no left child
                    if (in.left == null) {
                        // insert a new node
                        // fix the unbalanced tree, then return
                        in.left = new Node(key, value, in, true);
                        fixInsertion(in.left);
                        size++;
                        return;
                    }
                    // if not null, go left
                    in = in.left;

                // check if node key is greater than root key
                } else if (vsKey > 0) {
                    // if there is no right child
                    if (in.right == null) {
                        // insert a new node
                        // fix the unbalanced tree, then return
                        in.right = new Node(key, value, in, true);
                        fixInsertion(in.right);
                        size++;
                        return;
                    }
                    // if not null, go right
                    in = in.right;
                }
                //key == current key, duplicate so do nothing
                else return;
            }
        }

    }

    public void delete(String key) {
        // TODO - Implement deletion for a Red-Black Tree
        // Will need to handle three cases similar to the Binary Search Tree
        // 1. Node to be deleted has no children
        // 2. Node to be deleted has one child
        // 3. Node to be deleted has two children
        // Additionally, you must handle rebalancing after deletion to restore Red-Black Tree properties
        // make sure to subtract one from size if node is successfully added
    }

    /**
     * A method to fix the tree after an insert
     * so that the tree is balanced again
     *
     * @param node
     */
    private void fixInsertion(Node node) {

        // check if at root
        // make sure root is black
        if (node == root) {
            node.color = false;
            return;
        }



        while (isRed(node.parent)) {
            // make parent and grandparent node
            Node parent = node.parent;
            Node grand = node.parent.parent;

            // check if grandparent exists
            if (grand == null) {
                return;
            }
            // check if parent is on the left
            if (parent == grand.left) {
                Node uncle = grand.right;

                // Case 1: if Node's Parent & Uncle are Red
                if (isRed(parent) && isRed(uncle)) {
                    // -recolor Parent & Uncle Black
                    // -recolor GrandParent Red
                    parent.color = false;
                    uncle.color = false;
                    grand.color = true;

                    // recursively recolor up the tree
                    fixInsertion(grand);
                }

                // Case 2: Node's Uncle is Black & Node is a right child
                else if (isBlack(uncle) && isRed(parent) && node == parent.right) {
                    // rotate left on parent to a straight line
                    rotateLeft(parent);
                    // the original parent is now offending the balance
                    // as if it was a node inserted as a left child
                    node = parent;
                    fixInsertion(node);

                }

                // case 3: Node's Uncle is Black & Node is left child
                else {
                    // rotate right & recolor original parent & grandparent
                    parent.color = false;
                    grand.color = true;
                    rotateRight(grand);
                }
            }

            // parent is on the right
            else {
                Node uncle = grand.left;

                // Case 1: if Node's Parent & Uncle are Red
                if (isRed(parent) && isRed(uncle)) {
                    // -recolor Parent & Uncle Black
                    // -recolor GrandParent Red
                    parent.color = false;
                    uncle.color = false;
                    grand.color = true;

                    // recursively recolor up the tree
                    fixInsertion(grand);
                }

                // Case 2: Node's Uncle is Black & Node is a left child
                else if (isBlack(uncle) && isRed(parent) && node == parent.left) {
                    // rotate left on parent to a straight line
                    rotateRight(parent);
                    // the original parent is now offending the balance
                    // as if it was a node inserted as a right child
                    node = parent;
                    fixInsertion(node);

                }

                // case 3: Node's Uncle is Black & Node is right child
                else {
                    // rotate left & recolor original parent & grandparent
                    parent.color = false;
                    grand.color = true;
                    rotateLeft(grand);
                }
            }
            node = grand;
        }
    }

    private void fixDeletion(Node node) {
        // TODO - Implement the fix-up procedure after deletion
        // Ensure that Red-Black Tree properties are maintained (recoloring and rotations).
    }

    /**
     * Left Rotation method to help the fixInsert method
     * balance the tree
     *
     * @param node is the node passed to rotate
     */
    private void rotateLeft(Node node) {
        // pivot is the right child node that
        // needs to take node's place
        Node pivot = node.right;
        Node parent = node.parent;

        // if there is no right child return
        if (pivot == null) return;

        // any subtree to the left of pivot
        // needs to be moved up
        node.right = pivot.left;

        // if pivot has a left child
        // that child's parent will
        // now become node's child
        if (pivot.left != null) {
            pivot.left.parent = node;
        }

        // pivots parent is now nodes parent
        pivot.parent = parent;

        // if node was root then
        // pivot is now root
        if (parent == null) {
            root = pivot;
        }
        // otherwise have pivot become
        // parents new child
        else if (node == parent.left) {
            parent.left = pivot;
        }
        else {
            parent.right = pivot;
        }

        // have node become pivot's child
        pivot.left = node;
        node.parent = pivot;
    }

    /**
     * Right Rotation method to help the fixInsert method
     * balance the tree
     *
     * @param node is the node passed to rotate
     */
    private void rotateRight(Node node) {
        // pivot is the left child that
        // needs to take node's place
        Node pivot = node.left;
        Node parent = node.parent;

        // if there is no left child, return
        if (pivot == null) return;

        // any subtree to the right of pivot
        // needs to be moved up
        node.left = pivot.right;

        // if pivot has a right child
        // that child's parent will
        // now become node's child
        if (pivot.right != null) {
            pivot.right.parent = node;
        }

        // pivot's parent is now node's parent
        pivot.parent = parent;

        // if node was root then
        // pivot is now root
        if (parent == null) {
            root = pivot;
        }
        // otherwise have pivot become
        // parent's new child
        else if (node == parent.left) {
            parent.left = pivot;
        } else {
            parent.right = pivot;
        }

        // have node become pivot's child
        pivot.right = node;
        node.parent = pivot;
    }

    Node find(String key) {
        // TODO - Search for the node with the given key
        // If the key exists in the tree, return the Node where it is located
        // Otherwise, return null

        //a search pointer node
        Node search = root;

        if (root == null) return null;

        //check if key is less than current key
        //check if left is null to return
        //or keep going left
        //same with right
        while(search != null) {
            //convert string keys to int keys
            int vsKey = key.compareTo(search.key);

            if (vsKey < 0) {
                search = search.left;
            }

            else if (vsKey > 0) {
                search = search.right;
            }
            //returns null
            else return search;
        }
        // if key is not found
        return null;
    }

    public E getValue(String key) {
        // TODO - Use find() to locate the node with the given key and return its value
        // If the key does not exist, return null

        //store the returned key from find()
        Node found = this.find(key);
        if (found == null) return null;

        //checks to see if the key exists and returns the value
        else
            return found.value;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // returns the depth of the node with key, or 0 if it doesn't exist
    public int getDepth(String key) {
        Node node = find(key);
        if (node != null) return node.getDepth();
        return 0;
    }

    // Helper methods to check the color of a node
    private boolean isRed(Node node) {
        return node != null && node.color == true; // Red is true
    }

    private boolean isBlack(Node node) {
        return node == null || node.color == false; // Black is false, and null nodes are black
    }
    public int getSize() {
        return size;
    }

    // Do not alter this method
    public boolean validateRedBlackTree() {
        // Rule 2: Root must be black
        if (root == null) {
            return true; // An empty tree is trivially a valid Red-Black Tree
        }
        if (isRed(root)) {
            return false; // Root must be black
        }

        // Start recursive check from the root
        return validateNode(root, 0, -1);
    }

    // Do not alter this method
    // Helper method to check if the current node maintains Red-Black properties
    private boolean validateNode(Node node, int blackCount, int expectedBlackCount) {
        // Rule 3: Null nodes (leaves) are black
        if (node == null) {
            if (expectedBlackCount == -1) {
                expectedBlackCount = blackCount; // Set the black count for the first path
            }
            return blackCount == expectedBlackCount; // Ensure every path has the same black count
        }

        // Rule 1: Node is either red or black (implicit since we use a boolean color field)

        // Rule 4: If a node is red, its children must be black
        if (isRed(node)) {
            if (isRed(node.left) || isRed(node.right)) {
                return false; // Red node cannot have red children
            }
        } else {
            blackCount++; // Increment black node count on this path
        }

        // Recurse on left and right subtrees, ensuring they maintain the Red-Black properties
        return validateNode(node.left, blackCount, expectedBlackCount) &&
                validateNode(node.right, blackCount, expectedBlackCount);
    }
}
