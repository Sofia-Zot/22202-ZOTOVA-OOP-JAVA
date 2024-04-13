import org.junit.Test;
import org.junit.Assert;

public class CounterTest {

    @Test
    public void getAmountOfCows() {
        Counter counter = new Counter(1234);
        Assert.assertEquals(counter.getAmountOfCows(1234), 4);
        Assert.assertEquals(counter.getAmountOfCows(2345), 3);
        Assert.assertEquals(counter.getAmountOfCows(7563), 1);
        Assert.assertEquals(counter.getAmountOfCows(3456), 2);
    }

    @Test
    public void getAmountOfBulls() {
        Counter counter = new Counter(1234);
        Assert.assertEquals(counter.getAmountOfBulls(1098), 1);
        Assert.assertEquals(counter.getAmountOfBulls(4321), 0);
        Assert.assertEquals(counter.getAmountOfBulls(1234), 4);
    }
}