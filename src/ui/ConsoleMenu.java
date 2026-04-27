package ui;

import service.AuthService;
import service.AuthService.LoginResult;
import service.AuthService.RegisterResult;

import java.util.Scanner;

public class ConsoleMenu {

    private static final String LINE = "─────────────────────────────────────";

    private final AuthService authService;
    private final Scanner scanner;

    public ConsoleMenu(AuthService authService) {
        this.authService = authService;
        this.scanner     = new Scanner(System.in);
    }

    public void start() {
        printHeader();
        boolean running = true;

        while (running) {
            printMainMenu();
            int option = readMenuOption(1, 3);

            switch (option) {
                case 1 -> handleRegister();
                case 2 -> handleLogin();
                case 3 -> running = false;
            }
        }

        System.out.println("\n¡Hasta luego!");
        scanner.close();
    }

    private void handleRegister() {
        System.out.println("\n" + LINE);
        System.out.println("  REGISTRO DE NUEVO USUARIO");
        System.out.println(LINE);

        String username = readNonBlank("Nombre de usuario: ");
        String password = readNonBlank("Contraseña:        ");

        RegisterResult result = authService.register(username, password);

        switch (result) {
            case SUCCESS ->
                System.out.println("\n✔ Usuario '" + username + "' registrado exitosamente.");
            case USERNAME_ALREADY_EXISTS ->
                System.out.println("\n✖ El nombre de usuario '" + username + "' ya está en uso. Elegí otro.");
            case INVALID_INPUT ->
                System.out.println("\n✖ El nombre de usuario y la contraseña no pueden estar vacíos.");
        }

        pause();
    }

    private void handleLogin() {
        System.out.println("\n" + LINE);
        System.out.println("  INICIAR SESIÓN");
        System.out.println(LINE);

        String username = readNonBlank("Nombre de usuario: ");
        String password = readNonBlank("Contraseña:        ");

        LoginResult result = authService.login(username, password);

        switch (result) {
            case SUCCESS -> {
                System.out.println("\n✔ ¡Bienvenido, " + username + "!");
                showLoggedInMenu(username);
            }
            case ACCOUNT_BLOCKED ->
                System.out.println("\n⚠ La cuenta '" + username + "' está BLOQUEADA por demasiados intentos fallidos.");
            case WRONG_PASSWORD -> {
                int remaining = authService.getRemainingAttempts(username);
                if (authService.isBlocked(username)) {
                    System.out.println("\n⚠ Contraseña incorrecta. Cuenta BLOQUEADA.");
                } else {
                    System.out.println("\n✖ Contraseña incorrecta. Intentos restantes: " + remaining + ".");
                }
            }
            case USER_NOT_FOUND ->
                System.out.println("\n✖ No existe un usuario con ese nombre.");
            case INVALID_INPUT ->
                System.out.println("\n✖ El nombre de usuario y la contraseña no pueden estar vacíos.");
        }

        pause();
    }

    private void showLoggedInMenu(String username) {
        System.out.println("\n" + LINE);
        System.out.println("  SESIÓN ACTIVA: " + username);
        System.out.println(LINE);
        System.out.println("  1. Cerrar sesión");
        System.out.println(LINE);

        readMenuOption(1, 1);
        System.out.println("\n✔ Sesión cerrada correctamente.");
    }

    private void printHeader() {
        System.out.println("\n" + LINE);
        System.out.println("   SISTEMA DE LOGIN — TP 07 Prog. II");
        System.out.println(LINE);
    }

    private void printMainMenu() {
        System.out.println("\n" + LINE);
        System.out.println("  MENÚ PRINCIPAL");
        System.out.println(LINE);
        System.out.println("  1. Registrarse");
        System.out.println("  2. Iniciar sesión");
        System.out.println("  3. Salir");
        System.out.println(LINE);
        System.out.print("  Opción: ");
    }

    private int readMenuOption(int min, int max) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.print("  Opción inválida. Ingresá un número entre " + min + " y " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("  Opción inválida. Ingresá un número entre " + min + " y " + max + ": ");
            }
        }
    }

    private String readNonBlank(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("  Este campo no puede estar vacío.");
        }
    }

    private void pause() {
        System.out.print("\nPresioná ENTER para continuar...");
        scanner.nextLine();
    }
}
