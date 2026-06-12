package treeModule;

import java.util.NoSuchElementException;

public class SimpleBST<T extends Comparable<T>> {

    private TreeNode<T> root;
    private int size;

    public void insert(T element) {
        if (element == null) throw new IllegalArgumentException("element cannot be null");
        if (contains(element)) throw new IllegalArgumentException("Duplicate element: " + element);
        root = insertRecursive(root, element);
        size++;
    }

    protected TreeNode<T> insertRecursive(TreeNode<T> node, T element) {
        if (node == null) return new TreeNode<>(element);
        int cmp = element.compareTo(node.value);
        if (cmp < 0) node.left  = insertRecursive(node.left,  element);
        else         node.right = insertRecursive(node.right, element);
        return node;
    }

    public void remove(T element) {
        if (element == null) throw new IllegalArgumentException("element cannot be null");
        if (!contains(element)) throw new NoSuchElementException("Element not found: " + element);
        root = removeRecursive(root, element);
        size--;
    }

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
            node.value = successor;
            node.right = removeRecursive(node.right, successor);
        }
        return node;
    }

    protected T findMin(TreeNode<T> node) {
        while (node.left != null) node = node.left;
        return node.value;
    }

    public T search(T key) {
        TreeNode<T> node = searchNode(root, key);
        return node == null ? null : node.value;
    }

    private TreeNode<T> searchNode(TreeNode<T> node, T key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.value);
        if (cmp == 0) return node;
        if (cmp < 0)  return searchNode(node.left,  key);
        return             searchNode(node.right, key);
    }

    public boolean contains(T element) {
        return searchNode(root, element) != null;
    }

    public Object[] inOrder() {
        Object[] result = new Object[size];
        int[] index = {0};
        inOrderRecursive(root, result, index);
        return result;
    }

    private void inOrderRecursive(TreeNode<T> node, Object[] result, int[] index) {
        if (node == null) return;
        inOrderRecursive(node.left,  result, index);
        result[index[0]++] = node.value;
        inOrderRecursive(node.right, result, index);
    }

    public int     size()    { return size; }
    public boolean isEmpty() { return size == 0; }
    public void    clear()   { root = null; size = 0; }
}
