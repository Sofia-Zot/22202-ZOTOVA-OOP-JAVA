package ui;

import factoryManager.InventoryChangedEvent;
import factory.parts.Accessories;
import factory.parts.Car;
import factory.parts.Engine;
import factory.parts.Body;
import messages.Listener;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame implements Listener<InventoryChangedEvent> {
    private static final int height = 480;
    private static final int width = 640;

    private final DataPanel statsSection;
    private final SettingsPanel settingsSection;
    private final JSplitPane splitPane;

    public Menu() {
        super();

        this.setTitle("MT Factory Simulation");
        this.setMinimumSize(new Dimension(width, height));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Установка темы "Nimbus"
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Создание панелей
        statsSection = new DataPanel();
        settingsSection = new SettingsPanel();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, statsSection, settingsSection);
        splitPane.setDividerLocation(width / 2); // Устанавливаем начальное положение разделителя
        splitPane.setDividerSize(5); //  Ширина разделителя
        splitPane.setOneTouchExpandable(false); //  Убираем возможность изменения размеров  "на лету"
        splitPane.setResizeWeight(0.5); //  Обеспечивает, чтобы обе части имели равный размер

        // Добавление панелей на JFrame
        getContentPane().add(splitPane, BorderLayout.CENTER);

        // Стилизация фона
        getContentPane().setBackground(new Color(210, 255, 210));

        this.pack();
    }

    public void display() {
        this.setVisible(true);
    }

    @Override
    public void recieveMessage(InventoryChangedEvent event) {
        Class<?> partClass = event.getPartClass();
        int current = event.getCurrentAmount();
        int total = event.getTotalAmount();

        if (partClass == Body.class) {
            statsSection.setBodyAmount(current, total);
        } else if (partClass == Engine.class) {
            statsSection.setEngineAmount(current, total);
        } else if (partClass == Accessories.class) {
            statsSection.setAccessoryAmount(current, total);
        } else if (partClass == Car.class) {
            statsSection.setCarAmount(current, total);
        }
    }
}