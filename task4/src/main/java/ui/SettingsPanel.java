package ui;

import factoryManager.FactoryManager;
import factoryManager.ProductionSpeedChangedEvent;
import factory.Dealer;
import factory.parts.Accessories;
import factory.parts.Engine;
import factory.parts.Body;
import messages.Messenger;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel implements Messenger<ProductionSpeedChangedEvent> {
    private final FactoryManager controller = FactoryManager.getInstance();
    private static final int min = 1;
    private static final int max = 10;

    public SettingsPanel() {
        super(new GridLayout(4, 1, 10, 10));

        // Создание слайдеров
        addSlider("Body Production Speed", Body.class);
        addSlider("Engine Production Speed", Engine.class);
        addSlider("Accessories Production Speed", Accessories.class);
        addSlider("Dealer Speed", Dealer.class);

        // Стилизация фона
        setBackground(new Color(245, 245, 245));
    }

    private void addSlider(String title, Class<?> partClass) {
        JSlider slider = new JSlider(SettingsPanel.min, SettingsPanel.max);

        slider.addChangeListener(e -> {
            JSlider slider1 = (JSlider) e.getSource();
            int speed = slider1.getValue();
            int sleepMs = 1000 / speed;
            sendMessage(new ProductionSpeedChangedEvent(partClass, sleepMs));
        });
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setForeground(Color.WHITE);
        slider.setBackground(new Color(180, 200, 180));

        JLabel label = new JLabel(title);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(180, 200, 180));
        wrapper.add(label, BorderLayout.NORTH);
        wrapper.add(slider, BorderLayout.CENTER);


        add(wrapper);
    }

    @Override
    public void sendMessage(ProductionSpeedChangedEvent event) {
        controller.recieveMessage(event);
    }
}