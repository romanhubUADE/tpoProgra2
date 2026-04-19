package ui;

import email.Email;
import email.EmailSystem;
import email.Priority;
import email.Tab;

import java.util.List;
import java.util.Scanner;

/**
 * Maneja toda la interaccion con el usuario por consola.
 *
 * Responsabilidades:
 *   - Mostrar menus y mensajes
 *   - Leer y validar inputs del usuario
 *   - Traducir las acciones del usuario en llamadas al EmailSystem
 *
 * Esta clase no contiene logica de negocio. Solo orquesta la UI.
 * El EmailSystem no sabe que existe una consola, y esta clase
 * no sabe como funciona la PriorityQueue internamente.
 *
 * Manejo de inputs invalidos:
 *   Cada metodo de lectura valida el input en un loop. Si el usuario
 *   ingresa algo invalido, se muestra un mensaje de error y se vuelve
 *   a pedir. No se lanza ninguna excepcion hacia el EmailSystem.
 */
public class ConsoleUI {

    // Sistema de correo que contiene las cuatro colas de prioridad.
    private final EmailSystem system;

    // Scanner compartido para toda la sesion. Se cierra al salir.
    private final Scanner scanner;

    // Separador visual para menus y secciones.
    private static final String SEPARATOR = "----------------------------------------";

    /**
     * Crea la UI con el sistema de correo dado.
     *
     * @param system instancia del sistema de correo a usar
     */
    public ConsoleUI(EmailSystem system) {
        this.system = system;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Inicia el loop principal de la aplicacion.
     * Muestra el menu principal y procesa la opcion elegida.
     * El loop termina cuando el usuario elige la opcion Salir.
     */
    public void start() {
        boolean running = true;

        while (running) {
            printMainMenu();
            int option = readIntInRange(1, 4);

            switch (option) {
                case 1:
                    composeEmail();
                    break;
                case 2:
                    viewInbox();
                    break;
                case 3:
                    viewTabMenu();
                    break;
                case 4:
                    running = false;
                    break;
            }
        }

        System.out.println("\nCerrando sistema. Hasta luego.");
        scanner.close();
    }

    // --- Menus ---

    /**
     * Imprime el menu principal con el contador de mails en bandeja.
     */
    private void printMainMenu() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("       SISTEMA DE CORREO");
        System.out.println(SEPARATOR);
        System.out.println("1. Redactar mail");
        System.out.printf ("2. Bandeja de entrada  (%d mail(s))%n", system.inboxSize());
        System.out.println("3. Ver pestana");
        System.out.println("4. Salir");
        System.out.println(SEPARATOR);
        System.out.print("Opcion: ");
    }

    // --- Redactar mail ---

    /**
     * Flujo completo para redactar un mail nuevo.
     *
     * El usuario ingresa el asunto y la prioridad.
     * La pestana no se elige aqui: el mail va a la bandeja de entrada
     * y se clasifica despues desde ahi.
     *
     * Decision de UX: separar redaccion de clasificacion permite al usuario
     * escribir varios mails sin interrupciones y clasificarlos despues.
     */
    private void composeEmail() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("  REDACTAR MAIL");
        System.out.println(SEPARATOR);

        String subject = readSubject();
        Priority priority = readPriority();

        Email email = new Email(subject, priority);
        system.receiveEmail(email);

        System.out.println("\nMail recibido en bandeja de entrada.");
        System.out.printf("  Asunto:   %s%n", subject);
        System.out.printf("  Prioridad: %s%n", priority.name());
    }

    // --- Bandeja de entrada ---

    /**
     * Muestra todos los mails de la bandeja en orden de prioridad.
     * Si la bandeja esta vacia, informa al usuario y vuelve al menu principal.
     *
     * Si hay mails, ofrece clasificar el mail de mayor prioridad (el primero
     * de la lista). Solo se puede clasificar el primero porque la cola
     * procesa en orden — no tiene sentido clasificar uno del medio.
     *
     * Decision de UX: forzar el procesamiento en orden de prioridad refuerza
     * el concepto del TDA. El mail mas urgente siempre se atiende primero.
     */
    private void viewInbox() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("  BANDEJA DE ENTRADA");
        System.out.println(SEPARATOR);

        if (system.isInboxEmpty()) {
            System.out.println("La bandeja esta vacia.");
            return;
        }

        List<Email> mails = system.listInbox();
        printEmailList(mails);

        System.out.println("\n1. Clasificar el mail de mayor prioridad");
        System.out.println("2. Volver");
        System.out.print("Opcion: ");

        int option = readIntInRange(1, 2);

        if (option == 1) {
            classifyTopEmail();
        }
    }

    /**
     * Permite al usuario elegir a que pestana mover el mail de mayor
     * prioridad de la bandeja. Luego confirma la operacion.
     */
    private void classifyTopEmail() {
        System.out.println("\nElegir pestana destino:");
        System.out.println("1. Principal");
        System.out.println("2. Notificaciones");
        System.out.println("3. Spam");
        System.out.print("Opcion: ");

        Tab tab = readTab();
        system.classifyTopInboxEmail(tab);

        System.out.printf("Mail movido a %s.%n", tab.getDisplayName());
    }

    // --- Ver pestana ---

    /**
     * Muestra el submenu para elegir que pestana ver.
     */
    private void viewTabMenu() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("  VER PESTANA");
        System.out.println(SEPARATOR);
        System.out.printf("1. Principal       (%d mail(s))%n", system.tabSize(Tab.PRINCIPAL));
        System.out.printf("2. Notificaciones  (%d mail(s))%n", system.tabSize(Tab.NOTIFICACIONES));
        System.out.printf("3. Spam            (%d mail(s))%n", system.tabSize(Tab.SPAM));
        System.out.println("4. Volver");
        System.out.print("Opcion: ");

        int option = readIntInRange(1, 4);

        if (option == 4) return;

        Tab tab = tabFromMenuOption(option);
        viewTab(tab);
    }

    /**
     * Muestra todos los mails de una pestana en orden de prioridad.
     * Si la pestana esta vacia, informa al usuario.
     *
     * Si hay mails, ofrece leer el de mayor prioridad, lo cual lo
     * extrae de la cola (queda procesado).
     *
     * Decision de UX: leer un mail lo elimina de la cola porque modelamos
     * la pestana como una cola de procesamiento. Un mail leido es un
     * mail atendido. Esto mantiene la pestana limpia y el TDA coherente.
     */
    private void viewTab(Tab tab) {
        System.out.println("\n" + SEPARATOR);
        System.out.printf("  %s%n", tab.getDisplayName().toUpperCase());
        System.out.println(SEPARATOR);

        if (system.isTabEmpty(tab)) {
            System.out.println("Esta pestana esta vacia.");
            return;
        }

        List<Email> mails = system.listTab(tab);
        printEmailList(mails);

        System.out.println("\n1. Leer el mail de mayor prioridad");
        System.out.println("2. Volver");
        System.out.print("Opcion: ");

        int option = readIntInRange(1, 2);

        if (option == 1) {
            Email email = system.readTopEmail(tab);
            System.out.println("\n" + SEPARATOR);
            System.out.println("  LEYENDO MAIL");
            System.out.println(SEPARATOR);
            System.out.printf("Asunto:    %s%n", email.getSubject());
            System.out.printf("Prioridad: %s%n", email.getPriority().name());
            System.out.printf("Pestana:   %s%n", email.getTab().getDisplayName());
            System.out.println(SEPARATOR);
            System.out.println("Mail marcado como leido y eliminado de la pestana.");
        }
    }

    // --- Helpers de impresion ---

    /**
     * Imprime una lista de mails numerada, mostrando prioridad y asunto.
     * El orden de la lista refleja el orden de la cola (mayor prioridad primero).
     *
     * @param mails lista de mails a mostrar
     */
    private void printEmailList(List<Email> mails) {
        for (int i = 0; i < mails.size(); i++) {
            Email email = mails.get(i);
            System.out.printf("%d. [%s] %s%n",
                i + 1,
                email.getPriority().name(),
                email.getSubject()
            );
        }
    }

    // --- Helpers de lectura y validacion ---

    /**
     * Lee un asunto de mail. Rechaza entradas vacias o con solo espacios.
     * Repite el pedido hasta recibir un valor valido.
     *
     * @return asunto no vacio ingresado por el usuario
     */
    private String readSubject() {
        String subject;
        do {
            System.out.print("Asunto: ");
            subject = scanner.nextLine().trim();
            if (subject.isEmpty()) {
                System.out.println("El asunto no puede estar vacio.");
            }
        } while (subject.isEmpty());
        return subject;
    }

    /**
     * Lee una prioridad valida del usuario.
     * Acepta "1" para ALTO y "2" para BAJO.
     * Repite el pedido ante cualquier otro input.
     *
     * @return Priority elegida por el usuario
     */
    private Priority readPriority() {
        Priority priority = null;
        while (priority == null) {
            System.out.println("Prioridad:");
            System.out.println("  1. Alto");
            System.out.println("  2. Bajo");
            System.out.print("Opcion: ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": priority = Priority.ALTO; break;
                case "2": priority = Priority.BAJO; break;
                default:
                    System.out.println("Opcion invalida. Ingrese 1 o 2.");
            }
        }
        return priority;
    }

    /**
     * Lee una pestana valida del usuario.
     * Acepta "1" para PRINCIPAL, "2" para NOTIFICACIONES, "3" para SPAM.
     * Repite el pedido ante cualquier otro input.
     *
     * @return Tab elegida por el usuario
     */
    private Tab readTab() {
        Tab tab = null;
        while (tab == null) {
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1": tab = Tab.PRINCIPAL; break;
                case "2": tab = Tab.NOTIFICACIONES; break;
                case "3": tab = Tab.SPAM; break;
                default:
                    System.out.println("Opcion invalida. Ingrese 1, 2 o 3.");
                    System.out.print("Opcion: ");
            }
        }
        return tab;
    }

    /**
     * Lee un entero en el rango [min, max] inclusive.
     * Si el input no es un numero o esta fuera del rango, muestra
     * un mensaje de error y repite el pedido.
     *
     * Usa nextLine() en lugar de nextInt() para consumir el salto de linea
     * y evitar que quede basura en el buffer del Scanner.
     *
     * @param min valor minimo aceptado
     * @param max valor maximo aceptado
     * @return entero valido en el rango dado
     */
    private int readIntInRange(int min, int max) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Ingrese un numero entre %d y %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.printf("Entrada invalida. Ingrese un numero entre %d y %d: ", min, max);
            }
        }
    }

    /**
     * Convierte la opcion numerica del submenu de pestanas al enum Tab.
     * Solo se llama con valores 1, 2 o 3, validados previamente.
     *
     * @param option numero de opcion del submenu (1, 2 o 3)
     * @return Tab correspondiente
     */
    private Tab tabFromMenuOption(int option) {
        switch (option) {
            case 1: return Tab.PRINCIPAL;
            case 2: return Tab.NOTIFICACIONES;
            case 3: return Tab.SPAM;
            default: throw new IllegalStateException("Opcion de pestana invalida: " + option);
        }
    }
}
