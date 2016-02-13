package com.company;

/**
 * Created by mcsos on 2/13/2016.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class MatrixR extends Box implements ActionListener {
    private static final long serialVersionUID = 1L;
    MatrixC[] mca = new MatrixC[] {
            new MatrixC(this),
            new MatrixC(this),
            new MatrixC(this),
            new MatrixC(this),
            new MatrixC(this)
    };
    public MatrixR() {
        super(BoxLayout.X_AXIS);
        setBorder(
                new CompoundBorder(
                        new EtchedBorder(),
                        new EmptyBorder(8, 8, 8, 8)
                )
        );
        initRack();
        actionPerformed(null);
    }
    private void initRack() {
        for (int i = 0; i < mca.length; i++) {
            add(mca[i]);
            if (i != mca.length - 1) {
                add(Box.createHorizontalStrut(8));
            }
        }
        mca[mca.length - 2].jtfaa[2][0].setText("1.0");
        mca[mca.length - 2].jtfaa[2][1].setText("1.0");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mca[mca.length - 1].jtfaa[i][j].setEditable(false);
            }
        }
        for (int i = 0; i < mca.length; i++) {
            for (int j = 0; j < 3; j++) {
                mca[i].jtfaa[2][j].setEditable(false);
            }
        }
        mca[mca.length - 1].enable.setEnabled(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        double result[][] = {
                { 1.0, 0.0, 0.0 },
                { 0.0, 1.0, 0.0 },
                { 0.0, 0.0, 1.0 }
        };
        double matrixLeft[][] = {
                { 1.0, 0.0, 0.0 },
                { 0.0, 1.0, 0.0 },
                { 0.0, 0.0, 1.0 }
        };
        double matrixRight[][] = {
                { 1.0, 0.0, 0.0 },
                { 0.0, 1.0, 0.0 },
                { 0.0, 0.0, 1.0 }
        };
        for (int m = 0; m < mca.length - 1; m++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    try {
                        matrixRight[i][j] = Double.parseDouble(mca[m].jtfaa[i][j].getText());
                    } catch (NumberFormatException ex) {
                        mca[m].jtfaa[i][j].setText("0.0");
                        matrixRight[i][j] = Double.parseDouble(mca[m].jtfaa[i][j].getText());
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (mca[m].enable.isSelected()) {
                        result[i][j] = matrixLeft[i][0] * matrixRight[0][j] +
                                matrixLeft[i][1] * matrixRight[1][j] +
                                matrixLeft[i][2] * matrixRight[2][j];
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    matrixLeft[i][j] = result[i][j];
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mca[mca.length - 1].jtfaa[i][j].setText(String.format("%.2f", result[i][j]));
            }
        }
    }
}
