import java.util.Scanner;

// Всі методи статичні, бо клас не зберігає жодних даних
public class ConsoleInputManager {
    // Отримання символу з консолі
    public static char getChar(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.next().charAt(0);
    }

    // Отримання цілого числа з консолі
    public static int getInteger(Scanner scanner, String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.next());
    }

    // Отримання дробового числа з консолі
    public static double getDouble(Scanner scanner, String message) {
        System.out.print(message);
        return Double.parseDouble(scanner.next());
    }
}
