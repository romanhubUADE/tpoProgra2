import service.AuthService;
import ui.ConsoleMenu;

public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        ConsoleMenu menu = new ConsoleMenu(authService);
        menu.start();
    }
}
