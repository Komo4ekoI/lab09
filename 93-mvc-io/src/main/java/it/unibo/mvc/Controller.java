package it.unibo.mvc;

import java.util.List;

/**
 * This interface represents a controller in the MVC pattern,
 * responsible for managing the flow of data between the model and the view.
 */
public interface Controller {

    /**
     * Sets the string to be written on the next print operation.
     * 
     * @param text to be written
     * 
     * @throws NullPointerException if the text is null
     */
    void setTextToBePrinted(String text);

    /**
     * Get current string.
     * 
     * @return the string currently setted. null if the string is unset.
     */
    String getTextToBePrinted();

    /**
     * Get a history of a strings already written by thise controller.
     * 
     * @return {@link List} of written strings
     */
    List<String> getPrintedStringsHistory();

    /**
     * Prints the current string.
     * 
     * @throws IllegalStateException if next string is null
     */
    void printCurrentString();
}
