package ui;

import javax.swing.*;
import java.awt.*;

public class PartStockPanel extends JPanel {
    private final JLabel titleLabel;
    private final JLabel currentLabel;
    private final JLabel totalLabel;

    public PartStockPanel(String title) {
        super(new BorderLayout());

        // Создание элементов
        titleLabel = new JLabel(title);
        currentLabel = new JLabel();
        totalLabel = new JLabel();

        // Настройка шрифтов
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        currentLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Инициализация contentPanel
        JPanel contentPanel = new JPanel(); // Создаем пустой JPanel
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Устанавливаем BoxLayout

        // Добавление элементов в contentPanel
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(currentLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(totalLabel);

        // Добавление contentPanel на PartStockPanel
        add(contentPanel, BorderLayout.CENTER);

        // Стилизация
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 100, 0)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    }

    public void setAmounts(int currentAmount, int totalAmount) {
        SwingUtilities.invokeLater(() -> {
            currentLabel.setText("Current stock: " + currentAmount);
            totalLabel.setText("Total: " + totalAmount);
        });
    }
}