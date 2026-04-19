package email;

import priorityQueue.LinkedPriorityQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * Nucleo logico del sistema de correo.
 *
 * Administra cuatro colas de prioridad independientes:
 *   - inbox: bandeja de entrada donde llegan todos los mails nuevos
 *   - principal, notificaciones, spam: pestanas destino
 *
 * Responsabilidades:
 *   - Recibir mails nuevos en la bandeja
 *   - Exponer el contenido de cada cola como lista (para mostrar en pantalla)
 *   - Mover un mail de la bandeja a una pestana
 *   - Extraer un mail de una pestana (marcarlo como leido/procesado)
 *
 * La UI no accede a las colas directamente. Toda operacion pasa por esta clase.
 */
public class EmailSystem {

    // Bandeja de entrada: todos los mails nuevos llegan aca antes de clasificarse.
    private final LinkedPriorityQueue<Email> inbox;

    // Pestana Principal: mails personales o importantes del usuario.
    private final LinkedPriorityQueue<Email> principal;

    // Pestana Notificaciones: alertas, actualizaciones, mensajes automaticos.
    private final LinkedPriorityQueue<Email> notificaciones;

    // Pestana Spam: correo no deseado o sospechoso.
    private final LinkedPriorityQueue<Email> spam;

    /**
     * Inicializa el sistema con las cuatro colas vacias.
     */
    public EmailSystem() {
        this.inbox = new LinkedPriorityQueue<>();
        this.principal = new LinkedPriorityQueue<>();
        this.notificaciones = new LinkedPriorityQueue<>();
        this.spam = new LinkedPriorityQueue<>();
    }

    /**
     * Recibe un mail nuevo y lo encola en la bandeja de entrada.
     * La prioridad numerica se obtiene del enum Priority del mail.
     *
     * @param email el mail a recibir, no puede ser null
     */
    public void receiveEmail(Email email) {
        inbox.enqueue(email, email.getPriority().getNumericValue());
    }

    /**
     * Toma el mail de mayor prioridad de la bandeja, le asigna la pestana
     * indicada, y lo encola en la cola correspondiente a esa pestana.
     *
     * Este metodo combina dos operaciones: dequeue de bandeja + enqueue en pestana.
     * Se llama cuando el usuario elige clasificar el primer mail de la bandeja.
     *
     * @param tab la pestana destino para el mail
     * @throws java.util.NoSuchElementException si la bandeja esta vacia
     */
    public void classifyTopInboxEmail(Tab tab) {
        Email email = inbox.dequeue();
        email.assignTab(tab);
        getQueueForTab(tab).enqueue(email, email.getPriority().getNumericValue());
    }

    /**
     * Extrae y retorna el mail de mayor prioridad de una pestana especifica.
     * Representa la accion de abrir y procesar un mail de esa pestana.
     *
     * @param tab la pestana de la cual extraer el mail
     * @return el mail de mayor prioridad en esa pestana
     * @throws java.util.NoSuchElementException si la pestana esta vacia
     */
    public Email readTopEmail(Tab tab) {
        return getQueueForTab(tab).dequeue();
    }

    /**
     * Retorna una lista con todos los mails de la bandeja de entrada,
     * en orden de prioridad (mayor a menor), sin modificar la cola.
     *
     * Para obtener los elementos sin destruir la cola, se usa una cola
     * auxiliar: se desencola todo a la lista y a la auxiliar, luego
     * se restaura la cola original desde la auxiliar.
     *
     * @return lista de mails en orden de prioridad
     */
    public List<Email> listInbox() {
        return listQueue(inbox);
    }

    /**
     * Retorna una lista con todos los mails de una pestana,
     * en orden de prioridad, sin modificar la cola.
     *
     * @param tab la pestana a listar
     * @return lista de mails en orden de prioridad
     */
    public List<Email> listTab(Tab tab) {
        return listQueue(getQueueForTab(tab));
    }

    /**
     * Indica si la bandeja de entrada esta vacia.
     *
     * @return true si no hay mails en bandeja
     */
    public boolean isInboxEmpty() {
        return inbox.isEmpty();
    }

    /**
     * Indica si una pestana especifica esta vacia.
     *
     * @param tab la pestana a verificar
     * @return true si no hay mails en esa pestana
     */
    public boolean isTabEmpty(Tab tab) {
        return getQueueForTab(tab).isEmpty();
    }

    /**
     * Retorna la cantidad de mails en la bandeja de entrada.
     *
     * @return numero de mails en bandeja
     */
    public int inboxSize() {
        return inbox.size();
    }

    /**
     * Retorna la cantidad de mails en una pestana especifica.
     *
     * @param tab la pestana a consultar
     * @return numero de mails en esa pestana
     */
    public int tabSize(Tab tab) {
        return getQueueForTab(tab).size();
    }

    // --- Metodos privados de soporte ---

    /**
     * Devuelve la cola correspondiente a una pestana.
     * Centraliza el mapeo Tab -> LinkedPriorityQueue para no repetirlo
     * en cada metodo publico.
     *
     * @param tab la pestana
     * @return la cola de prioridad asociada a esa pestana
     */
    private LinkedPriorityQueue<Email> getQueueForTab(Tab tab) {
        switch (tab) {
            case PRINCIPAL:      return principal;
            case NOTIFICACIONES: return notificaciones;
            case SPAM:           return spam;
            default: throw new IllegalArgumentException("Pestana desconocida: " + tab);
        }
    }

    /**
     * Vacia una cola en una lista manteniendo el orden, luego la restaura.
     *
     * Proceso:
     *   1. Desencola todos los elementos a la lista y a una cola auxiliar.
     *   2. Desencola la auxiliar para restaurar la cola original.
     *
     * Este metodo existe porque la PriorityQueue no expone iteradores
     * (por diseño del TDA). Para leer sin destruir, hay que reconstruir.
     *
     * @param queue la cola a listar
     * @return lista con los elementos en orden de prioridad
     */
    private List<Email> listQueue(LinkedPriorityQueue<Email> queue) {
        List<Email> result = new ArrayList<>();
        LinkedPriorityQueue<Email> temp = new LinkedPriorityQueue<>();

        // Paso 1: vaciar la cola original a la lista y a la auxiliar.
        while (!queue.isEmpty()) {
            Email email = queue.dequeue();
            result.add(email);
            temp.enqueue(email, email.getPriority().getNumericValue());
        }

        // Paso 2: restaurar la cola original desde la auxiliar.
        while (!temp.isEmpty()) {
            Email email = temp.dequeue();
            queue.enqueue(email, email.getPriority().getNumericValue());
        }

        return result;
    }
}
