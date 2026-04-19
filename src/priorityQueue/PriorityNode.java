package priorityQueue;

/**
 * Nodo utilizado internamente por LinkedPriorityQueue.
 *
 * Cada nodo almacena tres cosas:
 *   - value: el dato que contiene el nodo (de tipo generico T)
 *   - priority: nivel de prioridad numerico (mayor numero = mayor prioridad)
 *   - next: referencia al siguiente nodo en la lista enlazada
 *
 * Es una clase de soporte: no forma parte de la interfaz publica del TDA,
 * solo la implementacion la conoce y la usa.
 */
public class PriorityNode<T> {

    // El dato almacenado en este nodo.
    T value;

    // Nivel de prioridad. Se usa para determinar la posicion del nodo
    // en la lista ordenada. Mayor numero significa mayor prioridad.
    int priority;

    // Referencia al siguiente nodo. Es null si este nodo es el ultimo.
    PriorityNode<T> next;

    /**
     * Crea un nodo con el valor y la prioridad indicados.
     * La referencia next queda en null hasta que se encadene.
     *
     * @param value    el dato a almacenar
     * @param priority el nivel de prioridad del nodo
     */
    public PriorityNode(T value, int priority) {
        this.value = value;
        this.priority = priority;
        this.next = null;
    }
}
