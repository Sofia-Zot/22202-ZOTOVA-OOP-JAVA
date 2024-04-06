import java.util.Scanner;

public class Guessing {

    // Приватная переменная для хранения попытки
    private int attempt;

    // Конструктор класса
    public Guessing() {
        // Инициализация переменной attempt значением по умолчанию 0
        this.attempt = 0;
    }

    // Метод для получения попытки
    public int getAttempt() {
        return attempt;
    }

    // Метод для установки попытки
    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    // Метод для получения попытки от пользователя
    public void getAttemptFromUser() {
        // Создание объекта Scanner для чтения ввода пользователя
        Scanner scanner = new Scanner(System.in);

        // Чтение строки ввода от пользователя
        String attemptString = scanner.nextLine();

        // Проверка ввода на валидность
        try {
            // Попытка преобразовать строку ввода в целое число
            int intAttempt = Integer.parseInt(attemptString);

            // Проверка на наличие недопустимых символов
            if (!attemptString.matches("[0-9]+")) {
                System.out.println("Ошибка: неверный формат. Допускаются только цифры.\n");
                return;
            }

            // Проверка на длину числа
            if (intAttempt < 1000 || intAttempt > 9999) {
                System.out.println("Ошибка: неверный формат. Число должно быть четырехзначным.\n");
                return;
            }

            // Проверка на повторяющиеся цифры
            char[] digits = attemptString.toCharArray();
            for (int i = 0; i < digits.length; i++) {
                for (int j = i + 1; j < digits.length; j++) {
                    if (digits[i] == digits[j]) {
                        System.out.println("Ошибка: неверный формат. Число не должно содержать повторяющихся цифр.\n");
                        return;
                    }
                }
            }

            // Установка попытки
            setAttempt(intAttempt);

        } catch (NumberFormatException e) {
            // Обработка исключения, возникающего при попытке преобразовать некорректную строку ввода в целое число
            System.out.println("Ошибка: неверный формат. Введите четырехзначное число без повторяющихся цифр.\n");
        }
    }
}