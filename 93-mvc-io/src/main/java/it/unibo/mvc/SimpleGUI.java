package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.StringJoiner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {
    private static final String TITLE = "I/O writer";
    private static final int PROPORTION = 3;

    private final Controller controller = new SimpleController();
    private final JFrame frame = new JFrame(TITLE);

    /**
     * Create a new {@code SimpelGUI}.
     */
    public SimpleGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Panels
        final JPanel textFieldsPanel = new JPanel(new BorderLayout());
        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        // Init text fields
        final JTextField textInputField = new JTextField("Entry field");
        final JTextArea textOutputArea = new JTextArea("History...");
        textOutputArea.setEditable(false);
        textFieldsPanel.add(textOutputArea, BorderLayout.CENTER);
        textFieldsPanel.add(textInputField, BorderLayout.NORTH);
        // Init Buttons
        final JButton printButton = new JButton("Print");
        final JButton showButton = new JButton("Show history");
        buttonsPanel.add(printButton);
        buttonsPanel.add(showButton);
        textFieldsPanel.add(buttonsPanel, BorderLayout.SOUTH);
        // Set the content
        frame.setContentPane(textFieldsPanel);
        // Handlers
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setTextToBePrinted(textInputField.getText());
                controller.printCurrentString();
            }
        });
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StringJoiner stringJoiner = new StringJoiner("\n");
                final List<String> strings = controller.getPrintedStringsHistory();
                for (final var string : strings) {
                    stringJoiner.add(string);
                }
                textOutputArea.setText(stringJoiner.toString());
            }
        });
    }

    private void display() {
        // Sets current display configuration
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        // Sets the dafault position
        frame.setLocationByPlatform(true);
        // Set visible whene everything is ready
        frame.setVisible(true);
    }

    /**
     * Starts the application.
     */
    public static void main() {
        new SimpleGUI().display();
    }
}
