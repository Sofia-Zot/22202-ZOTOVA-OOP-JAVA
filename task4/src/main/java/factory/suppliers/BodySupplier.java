package factory.suppliers;

import factory.parts.*;
import factory.PartsInventory;

public class BodySupplier extends Supplier {
    public BodySupplier(PartsInventory<AbstractPart> storage) {
        super(storage, 1000);
    }

    @Override
    public AbstractPart supplyPart() {
        return new Body(serialNumber++);
    }
}
