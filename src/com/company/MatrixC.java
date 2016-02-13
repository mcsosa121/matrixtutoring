package com.company;

/**
 * Created by mcsos on 2/13/2016.
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MatrixC extends JPanel {
    private static final long serialVersionUID = 1l;
    MatrixR rack;
    JTextField[][] jtfaa = {
            {new JTextField(new DDoc(), "1.0", 4), new JTextField(new DDoc(), "0.0", 4), new JTextField(new DDoc(), "0.0", 4)},
            {new JTextField(new DDoc(), "0.0", 4), new JTextField(new DDoc(), "1.0", 4), new JTextField(new DDoc(), "0.0", 4)},
            {new JTextField(new DDoc(), "0.0", 4), new JTextField(new DDoc(), "0.0", 4), new JTextField(new DDoc(), "1.0", 4)}
    };
    JCheckBox enable = new JCheckBox("Enable", true);
    public MatrixC(MatrixR rack) {
        this.rack = rack;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(
                new CompoundBorder(
                        new EtchedBorder(),
                        new EmptyBorder(4,4,4,4)
                )
        );
        Insets[][] insets = {
                { new Insets(1, 1, 0, 0), new Insets(1, 1, 0, 1), new Insets(1, 0, 0, 1) },
                { new Insets(1, 1, 0, 0), new Insets(1, 1, 0, 1), new Insets(1, 0, 0, 1) },
                { new Insets(1, 1, 1, 0), new Insets(1, 1, 1, 1), new Insets(1, 0, 1, 1) }
        };
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(3,3));
        for (int i = 0; i < jtfaa.length; i++) {
            for (int j = 0; j < jtfaa[i].length; j++) {
                jtfaa[i][j].setBorder(new MatteBorder(insets[i][j], Color.BLACK));
                jtfaa[i][j].setFont(jtfaa[i][j].getFont().deriveFont(18.0f));
                jtfaa[i][j].setHorizontalAlignment(JTextField.CENTER);
                jp.add(jtfaa[i][j]);
            }
        }
        add(jp);
        add(enable);
        enable.addActionListener(rack);
    }
    static class DDoc extends PlainDocument {
        private static final long serialVersionUID = 1L;
        private static final Pattern p = Pattern.compile("[0-9.-]+");
        public void insertString(int offset, String s, AttributeSet as) throws BadLocationException {
            Matcher m = p.matcher(s);
            if (!m.matches()) {
                s = "";
            }
            super.insertString(offset, s, as);
        }
    }
}