package ui;

import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {
    private final PartStockPanel bodyPanel;
    private final PartStockPanel enginePanel;
    private final PartStockPanel accessoryPanel;
    private final PartStockPanel carPanel;

    public DataPanel() {
        super(new GridLayout(4, 1, 10, 10));

        // Создание панелей для деталей
        bodyPanel = new PartStockPanel("Body");
        enginePanel = new PartStockPanel("Engines");
        accessoryPanel = new PartStockPanel("Accessories");
        carPanel = new PartStockPanel("Cars");

        // Добавление панелей на DataPanel
        add(bodyPanel);
        add(enginePanel);
        add(accessoryPanel);
        add(carPanel);

        // Настройка фона DataPanel
        setBackground(new Color(245, 245, 245));
    }

    public void setBodyAmount(int currentAmount, int totalAmount) {
        bodyPanel.setAmounts(currentAmount, totalAmount);
    }

    public void setEngineAmount(int currentAmount, int totalAmount) {
        enginePanel.setAmounts(currentAmount, totalAmount);
    }

    public void setAccessoryAmount(int currentAmount, int totalAmount) {
        accessoryPanel.setAmounts(currentAmount, totalAmount);
    }

    public void setCarAmount(int currentAmount, int totalAmount) {
        carPanel.setAmounts(currentAmount, totalAmount);
    }
}