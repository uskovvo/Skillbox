import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainForm {
    @Getter
    private JPanel mainPanel;
    private JTextField textPatronymic;
    private JTextField textName;
    private JButton button;
    private JTextField textSurname;
    private JLabel labelSurname;
    private JLabel labelName;
    private JLabel labelPatronymic;
    private JTextField textFullName;
    private JPanel panelFullName;
    private JPanel panelSurname;
    private JPanel panelName;
    private JPanel panelPatronymic;
    private JLabel labelFullName;

    public MainForm() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int i = 25;
                if (textSurname.getText().length() >= i) {
                    e.consume();
                }
            }
        };
        textSurname.addKeyListener(keyAdapter);

        textName.addKeyListener(keyAdapter);

        textPatronymic.addKeyListener(keyAdapter);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (button.getText().contains("Collapse")) {
                    if (!textSurname.getText().isEmpty() && !textName.getText().isEmpty()) {
                        if (findMatches(textSurname.getText()) || findMatches(textName.getText()) || findMatches(textPatronymic.getText())) {
                            JOptionPane.showMessageDialog(mainPanel, "Не корректный ввод", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        } else {
                            String fullName = textSurname.getText() + " " + textName.getText() + " " + textPatronymic.getText();
                            textSurname.setVisible(false);
                            textName.setVisible(false);
                            textPatronymic.setVisible(false);

                            labelSurname.setVisible(false);
                            labelName.setVisible(false);
                            labelPatronymic.setVisible(false);

                            labelFullName.setVisible(true);
                            textFullName.setVisible(true);
                            textFullName.setText(fullName);
                            button.setText("Expand");
                        }
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "Не корректный ввод", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if (!textFullName.getText().isEmpty()) {
                        String[] fullName = textFullName.getText().split(" ");
                        if (fullName.length == 2) {
                            if (findMatches(fullName[0]) && findMatches(fullName[1])) {
                                JOptionPane.showMessageDialog(mainPanel, "Не корректный ввод", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            } else {
                                textSurname.setVisible(true);
                                textName.setVisible(true);
                                textPatronymic.setVisible(true);

                                labelSurname.setVisible(true);
                                labelName.setVisible(true);
                                labelPatronymic.setVisible(true);

                                textSurname.setText(fullName[0]);
                                textName.setText(fullName[1]);
                                textPatronymic.setText("");

                                labelFullName.setVisible(false);
                                textFullName.setVisible(false);
                                button.setText("Collapse");
                            }
                        } else if (fullName.length == 3) {
                            if (findMatches(fullName[0]) && findMatches(fullName[1]) && findMatches(fullName[2])) {
                                JOptionPane.showMessageDialog(mainPanel, "Не корректный ввод", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            } else {
                                textSurname.setVisible(true);
                                textName.setVisible(true);
                                textPatronymic.setVisible(true);

                                labelSurname.setVisible(true);
                                labelName.setVisible(true);
                                labelPatronymic.setVisible(true);

                                textSurname.setText(fullName[0]);
                                textName.setText(fullName[1]);
                                textPatronymic.setText(fullName[2]);

                                labelFullName.setVisible(false);
                                textFullName.setVisible(false);
                                button.setText("Collapse");
                            }
                        }
                    }
                }
            }
        });
    }

    public boolean findMatches(String text) {
        Pattern pattern = Pattern.compile("[^А-Яа-я\\-]+");
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
