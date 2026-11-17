package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String DEFAULT_PATH = System.getProperty("user.home") + File.separator + "output.txt";

    private File currentFile = new File(DEFAULT_PATH);

    /**
     * Create new application controller.
     */
    public Controller() {
        // Does not require the init.
    }

    /**
     * Set's a new file.
     * 
     * @param file to be setted.
     */
    public void setCurrentFile(final File file) {
        Objects.requireNonNull(file, "file can't be null");
        currentFile = file;
    }

    /**
     * Returns the file currently installed.
     * 
     * @return current file {@link File}.
     */
    public File getCurrentFile() {
        return currentFile;
    }

    /**
     * Returns the file path of the currently installed file.
     * 
     * @return a file of the current file.
     */
    public String getFilePath() {
        return currentFile.getPath();
    }

    /**
     * Write a text passed in input to the file.
     * 
     * @param text that will be written to the file
     * 
     * @throws IOException on {@code PrintStream} errors
     */
    public void writeToCurrentFile(final String text) throws IOException {
        try (PrintStream ps = new PrintStream(getFilePath(), StandardCharsets.UTF_8)) {
            ps.print(text);
        }
    }
}
