package com.mojang.slicer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class AppGui extends JPanel {
    private JButton inputButton;
    private JButton leftoverButton;
    private JTextField inputField;
    private JButton outputButton;
    private JButton runButton;
    private JTextField outputField;
    private JTextField leftoverField;
    private JLabel jcomp8;
    private JLabel jcomp9;
    private JLabel jcomp10;
    private String inputFolder;
    private String outputFolder;
    private String leftoverFolder;

    public AppGui(String minecraftVersion, List<InputFile> INPUTS) {


        //construct components
        JFrame frame = new JFrame("Minecraft Slicer - " + minecraftVersion);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        inputButton = new JButton("...");
        leftoverButton = new JButton("Button 3...");
        inputField = new JTextField(5);
        outputButton = new JButton("...");
        runButton = new JButton("Run");
        outputField = new JTextField(5);
        leftoverField = new JTextField(5);
        jcomp8 = new JLabel("Input directory or .zip file");
        jcomp9 = new JLabel("Output directory");
        jcomp10 = new JLabel("Leftover directory");

        //adjust size and set layout
        setPreferredSize(new Dimension(578, 186));
        setLayout(null);

        //add components
        add(inputButton);
        add(leftoverButton);
        add(inputField);
        add(outputButton);
        add(runButton);
        add(outputField);
        add(leftoverField);
        add(jcomp8);
        add(jcomp9);
        add(jcomp10);

        //set component bounds (only needed by Absolute Positioning)
        inputButton.setBounds(500, 10, 50, 25);
        leftoverButton.setBounds(500, 70, 50, 25);
        inputField.setBounds(170, 10, 330, 25);
        outputButton.setBounds(500, 40, 50, 25);
        runButton.setBounds(235, 145, 100, 25);
        outputField.setBounds(170, 40, 330, 25);
        leftoverField.setBounds(170, 70, 330, 25);
        jcomp8.setBounds(20, 10, 150, 25);
        jcomp9.setBounds(70, 40, 100, 25);
        jcomp10.setBounds(55, 70, 115, 25);


        inputButton.addActionListener(e -> {
            int returnVal = fileChooser.showOpenDialog(frame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                inputField.setText(file.getAbsolutePath());
            }

        });

        outputButton.addActionListener(e -> {
            int returnVal = fileChooser.showOpenDialog(frame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                outputField.setText(file.getAbsolutePath());
            }

        });

        leftoverButton.addActionListener(e -> {
            int returnVal = fileChooser.showOpenDialog(frame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                leftoverField.setText(file.getAbsolutePath());
            }
        });

        runButton.addActionListener(e -> {
            inputFolder = inputField.getText();
            outputFolder = outputField.getText();
            leftoverFolder = leftoverField.getText();
            try {
                new Slicer(Path.of(inputFolder), Path.of(outputFolder), Path.of(leftoverFolder)).process(INPUTS);
                JOptionPane.showMessageDialog(frame, "Slicing finished");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.setResizable(false);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
