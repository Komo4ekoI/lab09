package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 */
public final class SimpleGUIWithFileChooser {
    private static final String TITLE = "File Writer With Chouser";
    private static final int PROPORTION = 5;
    private static final int DEFAULT_ROWS_COUNT = 10;
    private static final int DEFAULT_COLUMNS_COUNT = 25;

    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller = new Controller();

    /**
     * Build new GUI with chooser.
     */
    public SimpleGUIWithFileChooser() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel canvas = new JPanel(new BorderLayout());
        final JTextArea textArea = new JTextArea(DEFAULT_ROWS_COUNT, DEFAULT_COLUMNS_COUNT);
        canvas.add(textArea, BorderLayout.CENTER);
        // Save button
        final JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.SOUTH);
        // Path selector
        final JTextField filePath = new JTextField(controller.getFilePath());
        filePath.setEditable(false);
        // Path apply button
        final JButton selectButton = new JButton("Browse...");
        final JPanel selectPanel = new JPanel(new BorderLayout());
        selectPanel.add(filePath, BorderLayout.CENTER);
        selectPanel.add(selectButton, BorderLayout.LINE_END);
        canvas.add(selectPanel, BorderLayout.NORTH);
        // Handlers
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                try {
                    controller.writeToCurrentFile(textArea.getText());
                } catch (final IOException e) {
                    JOptionPane.showMessageDialog(
                        null, 
                        e.getMessage(), 
                        "An error occurred", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser chooser = new JFileChooser(
                    "Choose the destination");
                chooser.setSelectedFile(controller.getCurrentFile());
                final int result = chooser.showSaveDialog(frame);
                switch (result) {
                    case JFileChooser.APPROVE_OPTION:
                        final File newDest = chooser.getSelectedFile();
                        controller.setCurrentFile(newDest);
                        filePath.setText(newDest.getPath());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(
                            frame, 
                            result, 
                            "An error occurred!", 
                            JOptionPane.ERROR_MESSAGE
                        );
                }
            }
        });
        frame.setContentPane(canvas);
    }

    private void display() {
        // Sets current display configuration
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        // Sets the dafault position
        frame.setLocationByPlatform(true);
        frame.pack();
        // Set visible whene everything is ready
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     */
    public static void main() {
        new SimpleGUIWithFileChooser().display();
    }
}
