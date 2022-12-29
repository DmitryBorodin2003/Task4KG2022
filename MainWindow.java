package ru.vsu.cs.kg2022.borodin_d_n.task4;

import ru.vsu.cs.kg2022.borodin_d_n.task4.models.Wheel;

import javax.swing.*;

public class MainWindow extends JDialog {
    private JPanel contentPane;
    private DrawPanel drawPanel;
    private JButton ОКButton;
    private JTextField depthField;
    private JLabel depthText;
    private JLabel numberOfZubsText;
    private JTextField numberOfZubsField;
    private JLabel r1Text;
    private JTextField r1Field;
    private JLabel r2Text;
    private JTextField r2Field;
    private JLabel r3Text;
    private JTextField r3Field;
    private JLabel r4Text;
    private JTextField r4Field;

    public MainWindow() {
        setContentPane(contentPane);
        setModal(true);

        ОКButton.addActionListener(e -> {
            drawPanel.setWheel(new Wheel(Float.parseFloat(depthField.getText()), Float.parseFloat(r1Field.getText()), Float.parseFloat(r2Field.getText()), Float.parseFloat(r3Field.getText()), Float.parseFloat(r4Field.getText()), Integer.parseInt(numberOfZubsField.getText())));
            drawPanel.repaint();
        });

    }

    public static void main(String[] args) {
        MainWindow dialog = new MainWindow();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
