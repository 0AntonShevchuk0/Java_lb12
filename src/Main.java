import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.BinaryOperator;
import java.util.regex.Pattern;

public class Main {
    // Початок програми
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Отримання символу
        char symbol = ConsoleInputManager.getChar(scanner, "Enter any symbol: ");
        recognizeChar(symbol);
        System.out.println();

        // Робота з днями тижня в блоці для обробки можливих помилок
        try {
            int number = ConsoleInputManager.getInteger(scanner, "Enter number of day of week: ");
            recognizeWeekDay(number);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        System.out.println();

        // Калькулятор
        runCalculator(scanner);
        System.out.println();

        // Розрахунок коефіцієнтів в блоці обробки можливих помилок
        try {
            int number = ConsoleInputManager.getInteger(scanner, "Enter integer number (N > 3): ");
            calculateMathExpression(number);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    // Розпізнавання типу символів за допомогою Regexp
    private static void recognizeChar(char symbol) {
        // Перевірка кожного патерну, і виведення повідомлення, якщо жоден не співпав
        if (!checkPattern("[0-9]{1}", String.valueOf(symbol), "This symbol is a number") &&
                !checkPattern("[a-z]{1}", String.valueOf(symbol), "This symbol is a small latin letter") &&
                        !checkPattern("[A-Z]{1}", String.valueOf(symbol), "This symbol is a latin letter")) {
            System.out.println("This symbol is not number or latin letter");
        }
    }

    // Допоміжна функція для зменшення об'єму коду
    private static boolean checkPattern(String pattern, String value, String message) {
        if (Pattern.matches(pattern, value)) {
            System.out.println(message);
            return true;
        }
        return false;
    }

    // Розпізнавання та пошук наступного дня тижня по числу
    private static void recognizeWeekDay(int number) {
        if (number < 1 || number > WeekDay.values().length) {
            System.out.println("Wrong week day number.");
            return;
        }

        System.out.println("Today is " + WeekDay.values()[number - 1]);
        System.out.println("Tomorrow is " + WeekDay.values()[number == WeekDay.values().length ? 0 : number]);
    }

    // Калькулятор для 2 чисел з 5-ма типами операцій
    private static void runCalculator(Scanner scanner) {
        // Збереження операцій у вигляді: команда - операція
        HashMap<Character, BinaryOperator<Double>> operations = new HashMap<>();
        operations.put('+', Double::sum);
        operations.put('-', (Double a, Double b) -> a - b);
        operations.put('*', (Double a, Double b) -> a * b);
        operations.put('/', (Double a, Double b) -> a / b);
        operations.put('^', Math::pow);

        try {
            // Введення даних
            double number1 = ConsoleInputManager.getDouble(scanner, "Enter the first number: ");
            double number2 = ConsoleInputManager.getDouble(scanner, "Enter the second number: ");

            char operation = ConsoleInputManager.getChar(scanner, "Enter a math operation ( + - * / ^): ");
            if (!operations.containsKey(operation)) {
                System.out.println("Wrong operation.");
                return;
            }

            // Розрахунок
            System.out.println("Result: " + operations.get(operation).apply(number1, number2));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    // Розрахунок коефіцієнтів для виразу 5A - 2B = N
    private static void calculateMathExpression(int number) {
        // Обмеження N > 3
        if (number <= 3) {
            System.out.println("Wrong number.");
            return;
        }

        // Розрахунок коефіцієнтів та вивденення інформації
        int coef1;
        int coef2;
        if (number % 5 == 0) {
            coef1 = number / 5;
            coef2 = 0;
        }
        else {
            if (number % 2 == 0) {
                coef1 = number / 5 + 1;
            }
            else {
                coef1 = number / 5 + 2;
            }
            coef2 = (coef1 * 5 - number) / 2;
        }

        System.out.println("5A - 2B = " + number);
        System.out.println("A = " + coef1);
        System.out.println("B = " + coef2);
    }
}
