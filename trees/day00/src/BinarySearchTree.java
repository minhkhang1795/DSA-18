import jdk.nashorn.api.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    /**
     * Add a node to the BST. Internally calls insert to recursively find the new node's place
     */
    public boolean add(T key) {
        if (find(root, key) != null) return false;
        root = insert(root, key);
        size++;
        return true;
    }

    public void addAll(T[] keys) {
        for (T k : keys)
            add(k);
    }

    /**
     * Worst case: O(n)
     *
     * @return list of in-order traversal
     */
    public List<T> inOrderTraversal() {
        // TODO
        return inOrderTraversalHelper(root);
    }


    public List<T> inOrderTraversalHelper(TreeNode<T> root) {
        if (root == null)
            return new ArrayList<>();
        List<T> result = new ArrayList<>();
        result.addAll(inOrderTraversalHelper(root.leftChild));
        result.add(root.key);
        result.addAll(inOrderTraversalHelper(root.rightChild));
        return result;
    }

    /**
     * Deletes a node from the BST using the following logic:
     * 1. If the node has a left child, replace it with its predecessor
     * 2. Else if it has a right child, replace it with its successor
     * 3. If it has no children, simply its parent's pointer to it
     */
    public boolean delete(T key) {
        TreeNode<T> toDelete = find(root, key);
        if (toDelete == null) {
            System.out.println("Key does not exist");
            return false;
        }
        TreeNode<T> deleted = delete(toDelete);
        if (toDelete == root) {
            root = deleted;
        }
        size--;
        return true;
    }

    private TreeNode<T> delete(TreeNode<T> n) {
        // Recursive base case
        if (n == null) return null;

        TreeNode<T> replacement;

        if (n.isLeaf())
            // Case 1: no children
            replacement = null;
        else if (n.hasRightChild() != n.hasLeftChild())
            // Case 2: one child
            replacement = (n.hasRightChild()) ? n.rightChild : n.leftChild; // replacement is the non-null child
        else {
            // Case 3: two children
            // TODO
            TreeNode<T> successor = findSuccessor(n);
            n.key = successor.key;
            replacement = n;
            delete(successor);
        }

        // Put the replacement in its correct place, and set the parent.
        n.replaceWith(replacement);
        return replacement;
    }

    /**
     * Worst case: O(n)
     *
     * @param key node that has a specific key
     * @return predecessor of the node
     */
    public T findPredecessor(T key) {
        TreeNode<T> n = find(root, key);
        if (n != null) {
            TreeNode<T> predecessor = findPredecessor(n);
            if (predecessor != null)
                return predecessor.key;
        }
        return null;
    }

    /**
     * Worst case: O(n)
     *
     * @param key node that has a specific key
     * @return successor of the node
     */
    public T findSuccessor(T key) {
        TreeNode<T> n = find(root, key);
        if (n != null) {
            TreeNode<T> successor = findSuccessor(n);
            if (successor != null)
                return successor.key;
        }
        return null;
    }

    private TreeNode<T> findPredecessor(TreeNode<T> n) {
        // TODO
        if (n.hasLeftChild())
            return findMaxChild(n.leftChild);
        while (n.isLeftChild()) {
            if (n.parent.isRightChild())
                return n.parent.parent;
            n = n.parent;
        }
        return n.parent;
    }

    private TreeNode<T> findSuccessor(TreeNode<T> n) {
        // TODO
        if (n.hasRightChild())
            return findMinChild(n.rightChild);
        while (n.isRightChild()) {
            if (n.parent.isLeftChild())
                return n.parent.parent;
            n = n.parent;
        }
        return n.parent;
    }

    private TreeNode<T> findMinChild(TreeNode<T> n) {
        if (n == null || n.leftChild == null)
            return n;
        return findMinChild(n.leftChild);
    }

    private TreeNode<T> findMaxChild(TreeNode<T> n) {
        if (n == null || n.rightChild == null)
            return n;
        return findMaxChild(n.rightChild);
    }

    /**
     * Returns a node with the given key in the BST, or null if it doesn't exist.
     */
    private TreeNode<T> find(TreeNode<T> currentNode, T key) {
        if (currentNode == null)
            return null;
        int cmp = key.compareTo(currentNode.key);
        if (cmp < 0)
            return find(currentNode.leftChild, key);
        else if (cmp > 0)
            return find(currentNode.rightChild, key);
        return currentNode;
    }

    /**
     * Recursively insert a new node into the BST
     */
    private TreeNode<T> insert(TreeNode<T> node, T key) {
        if (node == null) return new TreeNode<>(key);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.leftChild = insert(node.leftChild, key);
            node.leftChild.parent = node;
        } else {
            node.rightChild = insert(node.rightChild, key);
            node.rightChild.parent = node;
        }
        return node;
    }
}
