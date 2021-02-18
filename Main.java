package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {

    public static void main(String[] args) {

        int xSize = 5;
        int ySize = 5;
        int numberOfMines = 5;

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(xSize * 80, ySize * 80);

        GridLayout layout = new GridLayout(ySize, xSize);

        frame.setLayout(layout);

        PlayField playField = new PlayField(xSize, ySize, numberOfMines);

        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                JButton button = new JButton();
                button.setBackground(Color.LIGHT_GRAY);
                button.setOpaque(true);
                int finalX = x;
                int finalY = y;
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            if (button.getText() != "?") {
                                if (playField.isThereMine(finalX, finalY)) {
                                    button.setBackground(Color.RED);
                                    button.setText("X");
                                    JOptionPane.showMessageDialog(frame, "You lost, better luck next time.");
                                } else {
                                    button.setBackground(Color.white);
                                    button.setText(String.valueOf(playField.getNeighboursWithMine(finalX, finalY)));
                                    playField.decrementOpenFields();
                                    if (playField.hasWon()) {
                                        JOptionPane.showMessageDialog(frame, "Congratulations, you won!");
                                    }
                                }
                                button.setEnabled(false);
                            }
                        }
                        if (SwingUtilities.isRightMouseButton(e)) {
                            if (button.getText() == "?") {
                                button.setText("");
                                button.setBackground(Color.LIGHT_GRAY);
                            } else {
                                button.setText("?");
                                button.setBackground(Color.YELLOW);
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
                frame.add(button);
            }
        }

    }
}
