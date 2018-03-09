public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    TreeNode<T> delete(TreeNode<T> n, T key) {
        n = super.delete(n, key);
        if (n != null) {
            // TODO
            // update the height of the tree using the height of the left and right child
            n.height = height(n);
            // return balance(n)
            return balance(n);
        }
        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    TreeNode<T> insert(TreeNode<T> n, T key) {
        n = super.insert(n, key);
        if (n != null) {
            // TODO
            // update the height of the tree using the height of the left and right child
            n.height = height(n);
            // return balance(n)
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    TreeNode<T> deleteMin(TreeNode<T> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = height(n);
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(TreeNode<T> n) {
        // TODO
        if (n == null)
            return -1;
        return 1 + Math.max(height(n.leftChild), height(n.rightChild));
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree. Return the head of the new subtree
    TreeNode<T> balance(TreeNode<T> n) {
        // TODO: (if you're having trouble, use pseudocode provided in slides)
        if (n == null)
            return null;
        int bf = balanceFactor(n);
        if (bf >= 2) {
            // Very right heavy
            int bfRight = balanceFactor(n.rightChild);
            if (bfRight <= -1) {
                // Left heavy
                n.rightChild = rotateRight(n.rightChild);
            }
            n = rotateLeft(n);
        } else if (bf <= -2) {
            // Very left heavy
            int bfLeft = balanceFactor(n.leftChild);
            if (bfLeft >= 1) {
                // Right heavy
                n.leftChild = rotateLeft(n.leftChild);
            }
            n = rotateRight(n);
        }
        return n;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(TreeNode<T> n) {
        // TODO
        return height(n.rightChild) - height(n.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        // TODO
        if (n == null || n.leftChild == null)
            return n;
        TreeNode<T> result = n.leftChild;
        TreeNode<T> temp = result.rightChild;
        result.rightChild = n;
        n.leftChild = temp;
        n.height = height(n);
        result.height = height(result);
        return result;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {
        // TODO
        if (n == null || n.rightChild == null)
            return n;
        TreeNode<T> result = n.rightChild;
        TreeNode<T> temp = result.leftChild;
        result.leftChild = n;
        n.rightChild = temp;
        n.height = height(n);
        result.height = height(result);
        return result;
    }
}
