package com.company;

/**
 * Created by mcsos on 2/13/2016.
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
public class View extends JPanel implements DocumentListener {
    private static final long serialVersionUID = 1L;
    private MatrixR rack;
    private JTextField[][] originalPoints;
    private JTextField[][] transformedPoints;
    private double op[][] = { { 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 }, { 1.0, 1.0, 1.0 } };
    private double tp[][] = { { 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 }, { 1.0, 1.0, 1.0 } };
    public View(MatrixR mr) {
        super();
        setBackground(Color.white);
        setLayout(null);
        this.rack = mr;
        originalPoints = mr.mca[mr.mca.length - 2].jtfaa;
        transformedPoints = mr.mca[mr.mca.length - 1].jtfaa;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                originalPoints[i][j].getDocument().addDocumentListener(this);
                transformedPoints[i][j].getDocument().addDocumentListener(this);
            }
        }
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(512, 512);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        AffineTransform gat = AffineTransform.getTranslateInstance(
                getWidth() / 2.0,
                getHeight() / 2.0
        );
        gat.scale(1.0, -1.0);
        g2d.transform(gat);
        g2d.setPaint(Color.BLACK);
        g2d.setStroke(new BasicStroke(0.0f));
        g2d.drawLine(0, -getHeight() / 2, 0, getHeight() / 2);
        g2d.drawLine(-getWidth() / 2, 0, getWidth() / 2, 0);
        if (rack.mca[3].enable.isSelected()) {
            Ellipse2D e2d = new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0);
            g2d.setPaint(Color.BLUE);
            for (int i = 0; i < 3; i++) {
                AffineTransform at = AffineTransform.getTranslateInstance(op[0][i] * 32.0, op[1][i] * 32.0);
                Shape s = at.createTransformedShape(e2d);
                g2d.fill(s);
                Line2D l2d = new Line2D.Double(op[0][0] * 32.0, op[1][0] * 32.0, op[0][1] * 32.0, op[1][1] * 32.0);
                g2d.draw(l2d);
                l2d = new Line2D.Double(op[0][1] * 32.0, op[1][1] * 32.0, op[0][2] * 32.0, op[1][2] * 32.0);
                g2d.draw(l2d);
                l2d = new Line2D.Double(op[0][2] * 32.0, op[1][2] * 32.0, op[0][0] * 32.0, op[1][0] * 32.0);
                g2d.draw(l2d);
            }
            g2d.setPaint(Color.RED);
            for (int i = 0; i < 3; i++) {
                AffineTransform at = AffineTransform.getTranslateInstance(tp[0][i] * 32.0, tp[1][i] * 32.0);
                Shape s = at.createTransformedShape(e2d);
                g2d.fill(s);
                Line2D l2d = new Line2D.Double(tp[0][0] * 32.0, tp[1][0] * 32.0, tp[0][1] * 32.0, tp[1][1] * 32.0);
                g2d.draw(l2d);
                l2d = new Line2D.Double(tp[0][1] * 32.0, tp[1][1] * 32.0, tp[0][2] * 32.0, tp[1][2] * 32.0);
                g2d.draw(l2d);
                l2d = new Line2D.Double(tp[0][2] * 32.0, tp[1][2] * 32.0, tp[0][0] * 32.0, tp[1][0] * 32.0);
                g2d.draw(l2d);
            }
        }
        g2d.dispose();
    }
    public void refreshPoints() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    op[i][j] = Double.parseDouble(originalPoints[i][j].getText());
                } catch (NumberFormatException ex) {
                    op[i][j] = 0.0;
                }
                try {
                    tp[i][j] = Double.parseDouble(transformedPoints[i][j].getText());
                } catch (NumberFormatException ex) {
                    tp[i][j] = 0.0;
                }
            }
        }
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        refreshPoints();
        repaint();
    }
    @Override
    public void removeUpdate(DocumentEvent e) {
        refreshPoints();
        repaint();
    }
    @Override
    public void changedUpdate(DocumentEvent e) {
        refreshPoints();
        repaint();
    }
}


