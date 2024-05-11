import java.util.Scanner;

public class Guessing {

    private int attempt;

    public Guessing() {
        this.attempt = 0;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public void getAttemptFromUser() {
        Scanner scanner = new Scanner(System.in);

        String attemptString = scanner.nextLine();

        try {
            int intAttempt = Integer.parseInt(attemptString);

            if (!attemptString.matches("[0-9]+")) {
                System.out.println("Ошибка: неверный формат. Допускаются только цифры.\n");
                return;
            }

            if (intAttempt < 1000 || intAttempt > 9999) {
                System.out.println("Ошибка: неверный формат. Число должно быть четырехзначным.\n");
                return;
            }

            char[] digits = attemptString.toCharArray();
            for (int i = 0; i < digits.length; i++) {
                for (int j = i + 1; j < digits.length; j++) {
                    if (digits[i] == digits[j]) {
                        System.out.println("Ошибка: неверный формат. Число не должно содержать повторяющихся цифр.\n");
                        return;
                    }
                }
            }

            setAttempt(intAttempt);

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: неверный формат. Введите четырехзначное число без повторяющихся цифр.\n");
        }
    }
}