public class Counter {
    private final String secretNumber;

    public Counter(int number) {
        this.secretNumber = Integer.toString(number);
    }

    public int getAmountOfCows(int attempt) {
        String attemptString = Integer.toString(attempt);
        int cowCount = 0;
        for (int i = 0; i < attemptString.length(); i++) {
            if (secretNumber.indexOf(attemptString.charAt(i)) != -1) {
                cowCount++;
            }
        }
        return cowCount;
    }

    public int getAmountOfBulls(int attempt) {
        String attemptString = Integer.toString(attempt);
        int bullCount = 0;
        for (int i = 0; i < attemptString.length(); i++) {
            if (attemptString.charAt(i) == secretNumber.charAt(i)) {
                bullCount++;
            }
        }
        return bullCount;
    }
}
