import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        // TODO
        BinarySearchTree<Integer> result = new BinarySearchTree<>();

        return result;
    }

    public static boolean isIsomorphic(TreeNode<Integer> n1, TreeNode<Integer> n2) {
        // TODO
        if (n1 != null && n2 != null) {
            if (!n1.key.equals(n2.key))
                return false;
            reArrangeLeftAndRight(n1);
            reArrangeLeftAndRight(n2);
            return isIsomorphic(n1.leftChild, n2.leftChild) && isIsomorphic(n1.rightChild, n2.rightChild);
        } else return n1 == null && n2 == null;
    }

    private static void reArrangeLeftAndRight(TreeNode<Integer> n) {
        if (!n.hasLeftChild()) {
            n.leftChild = n.rightChild;
            n.rightChild = null;
        } else if (n.hasRightChild()) {
            if (n.leftChild.key > n.rightChild.key) {
                TreeNode<Integer> temp = n.leftChild;
                n.leftChild = n.rightChild;
                n.rightChild = temp;
            }
        }
    }

}
