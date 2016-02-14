package com.company;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private static final long serialVersionUID = 1l;
    private MatrixR rack = new MatrixR();
    private JButton jb = new JButton("Multiply");
    public Main() {
        super("Matrix Test");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(rack, BorderLayout.CENTER);
        add(jb, BorderLayout.SOUTH);
        jb.addActionListener(rack);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        getRootPane().setDefaultButton(jb);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(
                () -> new Main()
        );
    }
}
