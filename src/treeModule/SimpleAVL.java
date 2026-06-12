package treeModule;

/**
 * AVL Tree: un BST que se mantiene siempre balanceado.
 *
 * Hereda toda la lógica de {@link SimpleBST} (insert/remove/search/inOrder) y solo
 * overridea la recursión de inserción y remoción para, después de cada cambio,
 * actualizar alturas y rebalancear con rotaciones. El factor de balance de cada
 * nodo queda siempre en {-1, 0, 1}.
 *
 * Convención de altura (igual que en clase): altura(null) = -1, una hoja = 0.
 */
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

    // ── Alturas y factor de balance ─────────────────────────────────────────

    private int height(TreeNode<T> node) {
        return node == null ? -1 : node.height;
    }

    private void updateHeight(TreeNode<T> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    /** Factor de balance: altura del subárbol izquierdo menos la del derecho. */
    private int balanceFactor(TreeNode<T> node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // ── Rebalanceo ──────────────────────────────────────────────────────────

    private TreeNode<T> rebalance(TreeNode<T> node) {
        int balance = balanceFactor(node);

        // Pesado a la izquierda
        if (balance > 1) {
            if (balanceFactor(node.left) < 0) node.left = rotateLeft(node.left); // Caso LR
            return rotateRight(node);                                            // Caso LL
        }
        // Pesado a la derecha
        if (balance < -1) {
            if (balanceFactor(node.right) > 0) node.right = rotateRight(node.right); // Caso RL
            return rotateLeft(node);                                                 // Caso RR
        }
        return node; // ya está balanceado
    }

    // ── Rotaciones ────────────────────────────────────────────────────────────

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
