package factory.parts;

public abstract class AbstractPart {
    private final int id;

    public AbstractPart(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
