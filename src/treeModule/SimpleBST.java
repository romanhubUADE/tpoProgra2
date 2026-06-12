package treeModule;

import java.util.NoSuchElementException;

/**
 * Árbol Binario de Búsqueda (BST) genérico.
 * Para cualquier nodo N: todos los valores del subárbol izquierdo son menores que N,
 * y todos los del derecho son mayores. No admite duplicados.
 * SimpleAVL extiende esta clase y sobreescribe insertRecursive/removeRecursive
 * para mantener el balance automático.
 */
public class SimpleBST<T extends Comparable<T>> {

    private TreeNode<T> root;
    private int size;

    /** Inserta el elemento en el lugar correcto; lanza excepción si ya existe. */
    public void insert(T element) {
        if (element == null) throw new IllegalArgumentException("element cannot be null");
        if (contains(element)) throw new IllegalArgumentException("Duplicate element: " + element);
        root = insertRecursive(root, element);
        size++;
    }

    /**
     * Baja recursivamente por el árbol comparando el elemento con cada nodo
     * hasta encontrar un lugar vacío (null) donde crear el nuevo nodo.
     * Es protected para que SimpleAVL pueda sobreescribirlo y agregar rebalanceo.
     */
    protected TreeNode<T> insertRecursive(TreeNode<T> node, T element) {
        if (node == null) return new TreeNode<>(element);
        int cmp = element.compareTo(node.value);
        if (cmp < 0) node.left  = insertRecursive(node.left,  element);
        else         node.right = insertRecursive(node.right, element);
        return node;
    }

    /** Elimina el elemento; lanza excepción si no se encuentra. */
    public void remove(T element) {
        if (element == null) throw new IllegalArgumentException("element cannot be null");
        if (!contains(element)) throw new NoSuchElementException("Element not found: " + element);
        root = removeRecursive(root, element);
        size--;
    }

    /**
     * Baja recursivamente hasta encontrar el nodo a eliminar.
     * Casos:
     *   - Sin hijo izquierdo: el hijo derecho sube a reemplazarlo.
     *   - Sin hijo derecho: el hijo izquierdo sube.
     *   - Dos hijos: reemplaza el valor con el sucesor inorden (mínimo del subárbol derecho)
     *     y luego elimina ese sucesor del subárbol derecho.
     */
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
            // Nodo con dos hijos: busca el sucesor inorden
            T successor = findMin(node.right);
            node.value = successor;
            node.right = removeRecursive(node.right, successor);
        }
        return node;
    }

    /**
     * Devuelve el valor mínimo del subárbol con raíz en {@code node}.
     * En un BST el mínimo siempre está en el extremo izquierdo.
     */
    protected T findMin(TreeNode<T> node) {
        while (node.left != null) node = node.left;
        return node.value;
    }

    /** Busca por clave y devuelve el elemento completo, o null si no existe. */
    public T search(T key) {
        TreeNode<T> node = searchNode(root, key);
        return node == null ? null : node.value;
    }

    /** Desciende por el árbol comparando la clave en cada nodo hasta encontrarla o llegar a null. */
    private TreeNode<T> searchNode(TreeNode<T> node, T key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.value);
        if (cmp == 0) return node;
        if (cmp < 0)  return searchNode(node.left,  key);
        return             searchNode(node.right, key);
    }

    /** Devuelve true si el elemento existe en el árbol. */
    public boolean contains(T element) {
        return searchNode(root, element) != null;
    }

    /**
     * Recorre el árbol en inorden (izquierda → raíz → derecha) y devuelve
     * un array con todos los elementos ordenados de menor a mayor.
     */
    public Object[] inOrder() {
        Object[] result = new Object[size];
        int[] index = {0};   // array de un elemento para poder modificarlo dentro del recursivo
        inOrderRecursive(root, result, index);
        return result;
    }

    /** Rellena el array en orden ascendente visitando los nodos en inorden. */
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

