public class Game {
    
    private final Counter counter;

    private final Guessing guessing = new Guessing();

    private final int generatedNumber;

    private int attemptCount;

    public Game(int lowerLimit, int upperLimit) {
        Generator generator = new Generator(lowerLimit, upperLimit);

        generatedNumber = generator.generateUniqueNumber();
        counter = new Counter(generatedNumber);
        System.out.println("Число сгенерировано.\n");
    }

    public void run() {
        while (true) {
            guessing.getAttemptFromUser();

            if (guessing.getAttempt() == 0) {
                continue;
            }

            attemptCount++;

            int bulls = counter.getAmountOfBulls(guessing.getAttempt());
            int cows = counter.getAmountOfCows(guessing.getAttempt());

            if (bulls == 4) {
                System.out.printf("Вы отгадали число %d, попыток: %d\n", generatedNumber, attemptCount);
                break;
            } else {
                System.out.printf("Быки: %d, коровы: %d\n", bulls, cows-bulls);
            }
        }
    }
}
