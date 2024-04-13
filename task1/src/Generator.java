import java.util.Random;

public class Generator {

    private final int lowerLimit;
    private final int upperLimit;

    public Generator(int lowerLimit, int upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public int generateUniqueNumber() {
        Random random = new Random();
        int number;

        do {
            number = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
        } while (!isValid(number));

        return number;
    }

    private boolean isValid(int number) {
        String numberString = Integer.toString(number);
        for (int i = 0; i < numberString.length(); i++) {
            for (int j = i + 1; j < numberString.length(); j++) {
                if (numberString.charAt(i) == numberString.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
