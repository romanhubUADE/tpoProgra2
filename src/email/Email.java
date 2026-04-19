package email;

/**
 * Representa un correo electronico dentro del sistema.
 *
 * Un mail tiene tres datos:
 *   - subject: el asunto, ingresado al momento de crear el mail
 *   - priority: nivel de prioridad (ALTO o BAJO), ingresado al crear
 *   - tab: la pestana a la que pertenece, asignada desde la bandeja de entrada
 *
 * El campo tab puede ser null mientras el mail esta en la bandeja de entrada
 * esperando ser clasificado. Una vez asignado, no cambia.
 *
 * Esta clase no tiene logica de negocio. Solo almacena y expone datos.
 */
public class Email {

    // Asunto del correo. No puede ser vacio ni null.
    private final String subject;

    // Nivel de prioridad del correo. Determina el orden en la PriorityQueue.
    private final Priority priority;

    // Pestana a la que fue asignado el mail. Null si todavia esta en bandeja.
    private Tab tab;

    /**
     * Crea un mail con asunto y prioridad. La pestana queda sin asignar (null)
     * hasta que el usuario la defina desde la bandeja de entrada.
     *
     * @param subject  asunto del correo
     * @param priority nivel de prioridad del correo
     */
    public Email(String subject, Priority priority) {
        this.subject = subject;
        this.priority = priority;
        this.tab = null;
    }

    /**
     * Asigna el mail a una pestana. Solo puede hacerse una vez, desde la
     * bandeja de entrada al momento de clasificar el correo.
     *
     * @param tab la pestana destino
     */
    public void assignTab(Tab tab) {
        this.tab = tab;
    }

    public String getSubject() {
        return subject;
    }

    public Priority getPriority() {
        return priority;
    }

    public Tab getTab() {
        return tab;
    }
}
