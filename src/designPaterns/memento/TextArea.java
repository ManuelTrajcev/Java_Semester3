package designPaterns.memento;

import javax.swing.*;

public class TextArea {
    private String text;
    private boolean bold;
    private boolean italic;
    private boolean underline;
    private GroupLayout.Alignment alignment;

    public void set(String text) {
        this.text = text;
    }

    public Memento takeSnapshot() {
        return new Memento(this.text);
    }

    public void restore(Memento memento) {
        this.text = memento.getSavedText();
    }


    public static class Memento {
        private final String  text;
        private Memento(String textToSave) {
            text = textToSave;
        }
        private String getSavedText() {
            return text;
        }
    }

    @Override
    public String toString() {
        return text;
    }
}
