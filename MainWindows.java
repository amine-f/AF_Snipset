package com.AF;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class MainWindows extends JFrame {
    JTextArea ta;
    JButton b1, b2;

    JPanel mainPanel, panel1, panel2;

    Time  time = new Time();
    SaveToFile save = new SaveToFile();

    public MainWindows() {
        super("AF_Snipset");
        this.setSize(600, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        mainPanel = new JPanel(new BorderLayout());
        panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        ta = new JTextArea(30, 48);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setBackground(Color.DARK_GRAY);
        ta.setCaretColor(Color.WHITE);
        ta.setSelectionColor(Color.WHITE);
        ta.setForeground(Color.WHITE);
        ta.setFont(new Font("Arial", Font.PLAIN,14));


        JScrollPane pane = new JScrollPane(ta);

        b1 = new JButton("a3ml save");
        b2 = new JButton("7el l fichier eli fih saves");

        DealWithButtons db = new DealWithButtons();
        b1.addActionListener(db);
        b2.addActionListener(db);

        panel1.add(pane);
        panel2.add(b1);
        panel2.add(b2);

        mainPanel.add(panel1, BorderLayout.CENTER);
        mainPanel.add(panel2, BorderLayout.SOUTH);

        this.add(mainPanel);

        this.setVisible(true);
        ta.requestFocus();
    }
    private class DealWithButtons implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == b1) {
                PrintWriter pr = save.createFile("C:\\AF-Snipset\\Snipset.txt");

                int option = JOptionPane.showConfirmDialog(null, "t7eb ta3ml save?");
                if (option == 0) {

                    save.printToFile(ta.getText().trim(), pr);
                    save.printToFile(time.getDate(), pr);
                    save.printToFile(time.getTime(), pr);
                    save.addSpace();

                    pr.close();

                    ta.setText("");
                }
            }
            if (e.getSource() == b2) {
                File f = new File("C:\\AF-Snipset\\Snipset.txt");
                Desktop desktop = Desktop.getDesktop();

                if (f.exists()) {
                    if (f.length() > 0) {
                        try {
                            desktop.edit(f);
                        }catch (IOException e1) {
                            // TODO mech Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
