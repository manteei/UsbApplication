package main.java.util;

import main.java.dataBase.DBManager;
import main.java.gui.GUI;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Start {
    public static void main(String[] args) {
        //String file_path = System.getenv("FILE_PATH");
        Color color3 =new Color(173, 186, 241);
        CollectionManager collectionManager = new CollectionManager();
        DBManager.load(collectionManager);
        GUI gui = new GUI(collectionManager);
        gui.getMain().createMainFrame();
        try {
            String file_path = System.getenv("FILE_PATH");
            try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
                String sn = reader.readLine();
                gui.getInformationFrame().createInformationFrame(sn);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }catch (RuntimeException e2){
            UIManager UI = new UIManager();
            UI.put("OptionPane.background", new ColorUIResource(color3));
            UI.put("Panel.background", new ColorUIResource(color3));
            String message = e2.getMessage();
            if (message == null){
                message = "null (Проблема с доступом к файлу sn.txt или с переменной окружения)";
            }
            JOptionPane.showMessageDialog(gui.getMain().getjTable(), message);

        }

    }
}
