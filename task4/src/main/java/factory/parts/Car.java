package factory.parts;

public class Car {
    private final Engine engine;
    private final Body body;
    private final Accessories accessory;

    public Car(Engine engine, Body body, Accessories accessory) {
        this.engine = engine;
        this.body = body;
        this.accessory = accessory;
    }

    @Override
    public String toString() {
        return String.format("Car (Engine: %d, Body: %d, Accessories: %d)",
                engine.getId(), body.getId(), accessory.getId());
    }
}
