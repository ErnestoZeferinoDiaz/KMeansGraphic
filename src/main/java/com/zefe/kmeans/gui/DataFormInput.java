package com.zefe.kmeans.gui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class DataFormInput extends JFrame{

    public DataFormInput(){
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
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Ernesto
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        label1 = new JLabel();
        txtNumberPoints = new JTextField();
        label2 = new JLabel();
        txtNumberCentroids = new JTextField();
        btnAddData = new JButton();
        btnClear = new JButton();
        btnCalculate = new JButton();
        btnBack = new JButton();
        btnNext = new JButton();
        btnDeleteHistory = new JButton();
        btnRunAnimation = new JButton();
        btnCalculateAndRun = new JButton();

        //======== viewData ========
        {
            Container viewDataContentPane = this.getContentPane();

            //======== tabbedPane1 ========
            {
                tabbedPane1.setBorder(new MatteBorder(0, 0, 0, 1, new Color(0x0c61aa)));
                tabbedPane1.setFont(new Font("Segoe UI", Font.BOLD, 12));

                //======== panel1 ========
                {

                    //---- label1 ----
                    label1.setText("Numero de puntos:");
                    label1.setFont(new Font("Segoe UI", Font.BOLD, 12));

                    //---- label2 ----
                    label2.setText("Numero de centroides:");
                    label2.setFont(new Font("Segoe UI", Font.BOLD, 12));

                    //---- btnAddData ----
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
                                            .addContainerGap(198, Short.MAX_VALUE))
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
                                            .addContainerGap(68, Short.MAX_VALUE))
                    );
                }
                tabbedPane1.addTab("Random", panel1);
            }

            //---- btnClear ----
            btnClear.setText("Limpiar Lienzo");

            //---- btnCalculate ----
            btnCalculate.setText("Calcular");

            //---- btnBack ----
            btnBack.setText("Back");

            //---- btnNext ----
            btnNext.setText("Next");

            //---- btnDeleteHistory ----
            btnDeleteHistory.setText("Eliminar historial");

            //---- btnRunAnimation ----
            btnRunAnimation.setText("Reproducir animacion");

            //---- btnCalculateAndRun ----
            btnCalculateAndRun.setText("Calcular y Reproducir");

            GroupLayout viewDataContentPaneLayout = new GroupLayout(viewDataContentPane);
            viewDataContentPane.setLayout(viewDataContentPaneLayout);
            viewDataContentPaneLayout.setHorizontalGroup(
                    viewDataContentPaneLayout.createParallelGroup()
                            .addGroup(viewDataContentPaneLayout.createSequentialGroup()
                                    .addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(viewDataContentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnDeleteHistory, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnClear, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(viewDataContentPaneLayout.createSequentialGroup()
                                                    .addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnCalculate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnRunAnimation, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnCalculateAndRun, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 21, Short.MAX_VALUE))
            );
            viewDataContentPaneLayout.setVerticalGroup(
                    viewDataContentPaneLayout.createParallelGroup()
                            .addGroup(viewDataContentPaneLayout.createSequentialGroup()
                                    .addGroup(viewDataContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tabbedPane1, GroupLayout.Alignment.LEADING)
                                            .addGroup(GroupLayout.Alignment.LEADING, viewDataContentPaneLayout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(btnClear)
                                                    .addGap(34, 34, 34)
                                                    .addComponent(btnCalculate)
                                                    .addGap(7, 7, 7)
                                                    .addComponent(btnRunAnimation)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(viewDataContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                            .addComponent(btnBack)
                                                            .addComponent(btnNext))
                                                    .addGap(44, 44, 44)
                                                    .addComponent(btnCalculateAndRun)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(btnDeleteHistory)))
                                    .addContainerGap(32, Short.MAX_VALUE))
            );
            this.pack();
            this.setLocationRelativeTo(this.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Ernesto
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    public JTextField txtNumberPoints;
    public JTextField txtNumberCentroids;
    public JButton btnAddData;
    public JButton btnClear;
    public JButton btnCalculate;
    public JButton btnBack;
    public JButton btnNext;
    public JButton btnDeleteHistory;
    public JButton btnRunAnimation;
    public JButton btnCalculateAndRun;
}
