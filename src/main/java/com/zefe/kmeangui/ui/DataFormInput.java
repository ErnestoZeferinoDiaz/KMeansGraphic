package com.zefe.kmeangui.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

public class DataFormInput extends JFrame {

    public JTextField txtNumberPoints;
    public JTextField txtNumberCentroids;
    public JButton btnAddData;
    public JButton btnCalculate;
    public JButton btnClear;

    public DataFormInput() {
        this.initComponents();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
        }
    }

    private void initComponents() {
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        label1 = new JLabel();
        txtNumberPoints = new JTextField();
        label2 = new JLabel();
        txtNumberCentroids = new JTextField();
        btnAddData = new JButton();
        btnCalculate = new JButton();
        btnClear = new JButton();

        Container content = this.getContentPane();

        tabbedPane1.setBorder(new MatteBorder(0, 0, 0, 1, new Color(0x0c61aa)));
        tabbedPane1.setFont(new Font("Segoe UI", Font.BOLD, 12));

        label1.setText("Numero de puntos:");
        label1.setFont(new Font("Segoe UI", Font.BOLD, 12));

        label2.setText("Numero de centroides:");
        label2.setFont(new Font("Segoe UI", Font.BOLD, 12));

        btnAddData.setText("A\u00f1adir datos");

        GroupLayout panel1Layout = new GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                        .addComponent(txtNumberPoints, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                        .addComponent(txtNumberCentroids, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                        .addComponent(btnAddData, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumberPoints, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(label2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumberCentroids, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAddData)
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        tabbedPane1.addTab("Random", panel1);

        btnCalculate.setText("Calcular");
        btnClear.setText("Limpiar Lienzo");

        GroupLayout layout = new GroupLayout(content);
        content.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnClear, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnCalculate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(tabbedPane1, GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(btnClear)
                                                .addGap(34, 34, 34)
                                                .addComponent(btnCalculate)))
                                .addContainerGap(32, Short.MAX_VALUE))
        );
        this.pack();
        this.setLocationRelativeTo(this.getOwner());
    }

    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
}
