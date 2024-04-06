public class Game {

    // Класс для подсчета количества быков и коров
    private final Counter counter;

    // Класс для получения предположений от пользователя
    private final Guessing guessing = new Guessing();

    // Сгенерированное число
    private final int generatedNumber;

    // Количество попыток
    private int attemptCount;

    // Конструктор класса
    public Game(int lowerLimit, int upperLimit) {
        // Инициализация генератора, счетчика и угадывания
        // Класс для генерации четырехзначного числа
        Generator generator = new Generator(lowerLimit, upperLimit);

        // Генерация числа и вывод сообщения
        generatedNumber = generator.generateUniqueNumber();
        counter = new Counter(generatedNumber);
        System.out.println("Число сгенерировано.\n");
    }

    // Метод для запуска игры
    public void run() {
        // Цикл для ввода предположений от пользователя
        while (true) {
            // Получение предположения от пользователя
            guessing.getAttemptFromUser();

            // Проверка предположения на валидность
            if (guessing.getAttempt() == 0) {
                continue;
            }

            // Увеличение количества попыток
            attemptCount++;

            // Подсчет количества быков и коров
            int bulls = counter.getAmountOfBulls(guessing.getAttempt());
            int cows = counter.getAmountOfCows(guessing.getAttempt());

            // Проверка, угадал ли пользователь число
            if (bulls == 4) {
                // Вывод сообщения о победе
                System.out.printf("Вы отгадали число %d, попыток: %d\n", generatedNumber, attemptCount);
                break;
            } else {
                // Вывод количества быков и коров
                System.out.printf("Быки: %d, коровы: %d\n", bulls, cows-bulls);
            }
        }
    }
}