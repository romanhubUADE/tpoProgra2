package emailModule;

import priorityQueueModule.SimpleLinkedPriorityQueue;
import priorityQueueModule.SimplePriorityQueue;

/**
 * Sistema de emails que maneja una bandeja de entrada y tres pestañas (Principal,
 * Notificaciones, Spam). Cada cola ordena los emails por prioridad (ALTO primero).
 */
public class EmailSystem {

    private final SimplePriorityQueue<Email> inbox;
    private final SimplePriorityQueue<Email> principal;
    private final SimplePriorityQueue<Email> notificaciones;
    private final SimplePriorityQueue<Email> spam;

    /** Inicializa las cuatro colas de prioridad vacías. */
    public EmailSystem() {
        inbox = new SimpleLinkedPriorityQueue<>();
        principal = new SimpleLinkedPriorityQueue<>();
        notificaciones = new SimpleLinkedPriorityQueue<>();
        spam = new SimpleLinkedPriorityQueue<>();
    }

    /** Agrega un email recibido a la bandeja de entrada con su prioridad. */
    public void receiveEmail(Email email) {
        inbox.enqueue(email, email.getPriority().getNumericValue());
    }

    /**
     * Saca el email de mayor prioridad del inbox, le asigna la pestaña indicada
     * y lo mueve a la cola correspondiente.
     */
    public void classifyTopInboxEmail(Tab tab) {
        Email email = inbox.dequeue();
        email.assignTab(tab);
        getQueueForTab(tab).enqueue(email, email.getPriority().getNumericValue());
    }

    /**
     * Saca y devuelve el email de mayor prioridad de la pestaña indicada.
     * El email queda removido de la cola.
     */
    public Email readTopEmail(Tab tab) {
        return getQueueForTab(tab).dequeue();
    }

    /** Devuelve todos los emails del inbox como arreglo, sin modificar la cola. */
    public Email[] listInbox() { return listQueue(inbox); }

    /** Devuelve todos los emails de una pestaña como arreglo, sin modificar la cola. */
    public Email[] listTab(Tab tab) { return listQueue(getQueueForTab(tab)); }

    public boolean isInboxEmpty() { return inbox.isEmpty(); }
    public boolean isTabEmpty(Tab tab) { return getQueueForTab(tab).isEmpty(); }
    public int inboxSize() { return inbox.size(); }
    public int tabSize(Tab tab) { return getQueueForTab(tab).size(); }

    /** Devuelve la cola de prioridad que corresponde a la pestaña dada. */
    private SimplePriorityQueue<Email> getQueueForTab(Tab tab) {
        switch (tab) {
            case PRINCIPAL:      return principal;
            case NOTIFICACIONES: return notificaciones;
            case SPAM:           return spam;
            default: throw new IllegalArgumentException("Pestana desconocida: " + tab);
        }
    }

    /**
     * Recorre la cola vaciándola en un arreglo, y la reconstruye usando una cola
     * auxiliar. Así puede listar sin perder los elementos.
     */
    private Email[] listQueue(SimplePriorityQueue<Email> queue) {
        int n = queue.size();
        Email[] result = new Email[n];
        SimplePriorityQueue<Email> temp = new SimpleLinkedPriorityQueue<>();

        // Vacía la cola original guardando cada email en el arreglo y en la auxiliar
        for (int i = 0; i < n; i++) {
            Email email = queue.dequeue();
            result[i] = email;
            temp.enqueue(email, email.getPriority().getNumericValue());
        }

        // Restaura la cola original desde la auxiliar
        while (!temp.isEmpty()) {
            Email email = temp.dequeue();
            queue.enqueue(email, email.getPriority().getNumericValue());
        }

        return result;
    }
}
