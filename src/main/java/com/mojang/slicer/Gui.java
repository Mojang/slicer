package com.mojang.slicer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public class Gui {
    private String inputFolder;
    private String outputFolder;
    private String leftoverFolder;

    public Gui(String minecraftVersion, List<InputFile> INPUTS) {


        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(panel, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(500, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Minecraft Slicer - " + minecraftVersion);

        JButton inputButton = new JButton("Input");

        JButton outputButton = new JButton("Output");
        JButton leftoverButton = new JButton("Leftover");
        JButton runButton = new JButton("Run");

        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Handle open button action.
                if (e.getSource() == inputButton) {
                    int returnVal = fileChooser.showOpenDialog(frame);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        //This is where a real application would open the file.
                        inputFolder = file.getAbsolutePath();
                        System.out.println("Opening: " + file.getAbsolutePath());
                    } else {
                        System.out.println("Open command cancelled by user.");
                    }
                }
            }
        });

        outputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Handle open button action.
                int returnVal = fileChooser.showOpenDialog(frame);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    //This is where a real application would open the file.
                    outputFolder = file.getAbsolutePath();
                    System.out.println("Opening: " + file.getAbsolutePath());
                } else {
                    System.out.println("Open command cancelled by user.");
                }

            }
        });
        leftoverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Handle open button action.
                int returnVal = fileChooser.showOpenDialog(frame);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    //This is where a real application would open the file.
                    leftoverFolder = file.getAbsolutePath();
                    System.out.println("Opening: " + file.getAbsolutePath());
                } else {
                    System.out.println("Open command cancelled by user.");
                }

            }
        });

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked");
                try {
                    new Slicer(Path.of(inputFolder), Path.of(outputFolder), Path.of(leftoverFolder)).process(INPUTS);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(inputButton);
        panel.add(outputButton);
        panel.add(leftoverButton);
        panel.add(runButton);

        frame.pack();
        frame.setVisible(true);
    }
}
