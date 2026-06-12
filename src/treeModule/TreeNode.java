package treeModule;

class TreeNode<T> {
    T value;
    TreeNode<T> left;
    TreeNode<T> right;
    int height = 0;

    TreeNode(T value) {
        this.value = value;
    }
}
