package factory.suppliers;

import factory.parts.*;
import factory.PartsInventory;

public class EngineSupplier extends Supplier {
    public EngineSupplier(PartsInventory<AbstractPart> storage) {
        super(storage, 1000);
    }

    @Override
    public AbstractPart supplyPart() {
        return new Engine(serialNumber++);
    }
}
