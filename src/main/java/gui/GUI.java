package main.java.gui;

import main.java.util.CollectionManager;

import javax.swing.*;

public class GUI {
    private MainGui main = new MainGui(this);

    private JTextArea result = new JTextArea();
    private AddCommandFrame addCommandFrame = new AddCommandFrame(this);
    private CollectionManager collectionManager;

    private EditFrame editFrame = new EditFrame(this);
    private InformationFrame informationFrame = new InformationFrame(this);
    public  GUI(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    public JTextArea getResult() {
        return result;
    }

    public MainGui getMain() {
        return main;
    }

    public EditFrame getEditorPane() {
        return editFrame;
    }

    public InformationFrame getInformationFrame() {
        return informationFrame;
    }

    public AddCommandFrame getAddCommandFrame() {
        return addCommandFrame;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }
}