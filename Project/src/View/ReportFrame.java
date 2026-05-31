package View;

import Model.Report.*;

import javax.swing.*;

import java.awt.*;

public class ReportFrame
        extends JFrame {

    private JButton
            generateButton;

    private JTextArea
            outputArea;
    
    private JButton
        backButton;
    
    public ReportFrame(){

        setTitle(
                "Daily Report"
        );

        setSize(
                600,
                500
        );

        setLocationRelativeTo(
                null
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );

        // ======================
        // BUTTON
        // ======================

        generateButton =
                new JButton(
                        "Generate Daily Report"
                );
        
        backButton =
            new JButton(
                    "Back"
            );
        
        // ======================
        // OUTPUT
        // ======================

        outputArea =
                new JTextArea();

        outputArea.setEditable(
                false
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        outputArea
                );

        // ======================
        // ADD COMPONENT
        // ======================

        JPanel topPanel =
                new JPanel();

        topPanel.add(
                generateButton
        );

        topPanel.add(
                backButton
        );

        add(
                topPanel,
                BorderLayout.NORTH
        );
        
        add(
                scrollPane,
                BorderLayout.CENTER
        );

        // ======================
        // ACTION
        // ======================

        generateButton
        .addActionListener(e -> {

            generateReport();

        });
        
        backButton
        .addActionListener(e -> {

            dispose();

        });
        
        setVisible(true);

    }

    // ======================
    // GENERATE REPORT
    // ======================

    private void generateReport(){

        // ======================
        // POLYMORPHISM
        // ======================

        ReportGenerator report =
                new DailyReport();

        String hasil =
                report.generateReport();

        outputArea.setText(
                hasil
        );

    }

}