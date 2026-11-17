package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple implementation of the {@link Controller} interface.
 */
public final class SimpleController implements Controller {
    private final List<String> printedStrings = new ArrayList<>();
    private String nextString;

    /**
     * A new simple I/O controller.
     */
    public SimpleController() {
        // Do not require the initialization.
    }

    @Override
    public void setTextToBePrinted(final String text) {
        Objects.requireNonNull(text, "text can't be null");
        nextString = text;
    }

    @Override
    public String getTextToBePrinted() {
        return nextString;
    }

    @Override
    public List<String> getPrintedStringsHistory() {
        return List.copyOf(printedStrings);
    }

    @Override
    public void printCurrentString() {
        if (nextString == null) {
            throw new IllegalStateException("Next string is not set");
        }
        IO.println(nextString);
        printedStrings.add(nextString);
    }

}
