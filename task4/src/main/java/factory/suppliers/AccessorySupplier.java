package factory.suppliers;

import factory.parts.*;
import factory.PartsInventory;

public class AccessorySupplier extends Supplier {
    public AccessorySupplier(PartsInventory<AbstractPart> storage) {
        super(storage, 1000);
    }

    @Override
    public AbstractPart supplyPart() {
        return new Accessories(serialNumber++);
    }
}
