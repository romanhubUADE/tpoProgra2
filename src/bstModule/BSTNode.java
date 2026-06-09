package bstModule;

class BSTNode<T> {
    T value;
    BSTNode<T> left;
    BSTNode<T> right;
    int height = 0;   // altura del nodo. Una hoja vale 0; null vale -1. Lo usa SimpleAVL.

    BSTNode(T value) {
        this.value = value;
    }
}
