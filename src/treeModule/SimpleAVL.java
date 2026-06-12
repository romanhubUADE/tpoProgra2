package treeModule;

public class SimpleAVL<T extends Comparable<T>> extends SimpleBST<T> {

    @Override
    protected TreeNode<T> insertRecursive(TreeNode<T> node, T element) {
        if (node == null) return new TreeNode<>(element);

        int cmp = element.compareTo(node.value);
        if (cmp < 0) node.left  = insertRecursive(node.left,  element);
        else         node.right = insertRecursive(node.right, element);

        updateHeight(node);
        return rebalance(node);
    }

    @Override
    protected TreeNode<T> removeRecursive(TreeNode<T> node, T element) {
        if (node == null) return null;

        int cmp = element.compareTo(node.value);
        if (cmp < 0) {
            node.left  = removeRecursive(node.left,  element);
        } else if (cmp > 0) {
            node.right = removeRecursive(node.right, element);
        } else {
            if (node.left  == null) return node.right;
            if (node.right == null) return node.left;
            T successor = findMin(node.right);
            node.value  = successor;
            node.right  = removeRecursive(node.right, successor);
        }

        updateHeight(node);
        return rebalance(node);
    }

    private int height(TreeNode<T> node) {
        return node == null ? -1 : node.height;
    }

    private void updateHeight(TreeNode<T> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int balanceFactor(TreeNode<T> node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private TreeNode<T> rebalance(TreeNode<T> node) {
        int balance = balanceFactor(node);

        if (balance > 1) {
            if (balanceFactor(node.left) < 0) node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1) {
            if (balanceFactor(node.right) > 0) node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    private TreeNode<T> rotateRight(TreeNode<T> y) {
        TreeNode<T> x  = y.left;
        TreeNode<T> t2 = x.right;
        x.right = y;
        y.left  = t2;

        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private TreeNode<T> rotateLeft(TreeNode<T> x) {
        TreeNode<T> y  = x.right;
        TreeNode<T> t2 = y.left;
        y.left  = x;
        x.right = t2;

        updateHeight(x);
        updateHeight(y);
        return y;
    }
}
