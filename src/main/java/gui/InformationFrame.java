package main.java.gui;


import main.java.dataBase.DBManager;
import main.java.util.CollectionManager;
import main.java.util.FlashDrive;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;

import static java.lang.Integer.parseInt;

public class InformationFrame {
    private GUI gui;
    private JLabel validate = new JLabel();
    private CollectionManager collectionManager = new CollectionManager();
    private String result;

    public InformationFrame(GUI gui) {
        this.gui = gui;
    }

    public JLabel getValidate() {
        return validate;
    }

    /**
     * Метод создает фрейм для ввода данных
     *
     */
    public void createInformationFrame(String sn) {
        Font fnt = new Font("шрифт", Font.PLAIN, 16);
        Font fnt1 = new Font("шрифт", Font.PLAIN, 12);
        Color color1 =new Color(248, 249, 250);
        Color color3 =new Color(173, 186, 241);
        Color color2 = new Color(108, 136, 226);
        Color color4 = new Color(255, 212, 91);
        validate.setText("");
        int count = 0;

        JFrame addFrame = new JFrame("Подключено новое устройство!");
        addFrame.getContentPane().setBackground(color3);
        addFrame.setLayout(new GridBagLayout());
        addFrame.setSize(600, 450);
        addFrame.setDefaultCloseOperation(addFrame.DISPOSE_ON_CLOSE);
        addFrame.setResizable(false);
        addFrame.setLocationRelativeTo(null);

        FlashDrive flashDrive = gui.getCollectionManager().searchSN(sn);
        if (flashDrive == null){
            JButton buttonAuth1 = new JButton("Добавить");
            buttonAuth1.setBackground(color4);

            JTextField idField = new JTextField();
            JTextField registrationDateField = new JTextField();
            JTextField typeField = new JTextField();
            JTextField volumeField = new JTextField();
            JTextField snField = new JTextField(sn);
            JTextField departmentField = new JTextField();
            JTextField ownerField = new JTextField();
            JTextField phoneNumberField = new JTextField();
            JTextField sendDateField = new JTextField();
            JTextField returnDateField = new JTextField();
            JTextField destroyedField = new JTextField();
            createComponentForAdd(addFrame, "", validate, count++);
            createComponentForAdd(addFrame, "Элемента с таким серийным номером нет в базе.", validate, count++);
            createComponentForAdd(addFrame, "", validate, count++);
            createComponentForAdd(addFrame, "Учетный №", idField, count++);
            createComponentForAdd(addFrame, "Дата поставки на учет", registrationDateField, count++);
            createComponentForAdd(addFrame, "Тип носителя", typeField, count++);
            createComponentForAdd(addFrame, "Объем памяти", volumeField, count++);
            createComponentForAdd(addFrame, "Серийный номер", snField, count++);
            createComponentForAdd(addFrame, "Отдел", departmentField, count++);
            createComponentForAdd(addFrame, "Кому выдан или отправлен ФИО", ownerField, count++);
            createComponentForAdd(addFrame, "Телефон", phoneNumberField, count++);
            createComponentForAdd(addFrame, "Дата получения или отправки", sendDateField, count++);
            createComponentForAdd(addFrame, "Дата возврата", returnDateField, count++);
            createComponentForAdd(addFrame, "Дополнительная информация", destroyedField, count++);
            createComponentForAdd(addFrame, "", validate, count++);

            buttonAuth1.addActionListener(e -> {

                boolean checher = true;

                String id = idField.getText();
                try{
                    parseInt(id);
                }catch (NumberFormatException e1){
                    if (id.equals("")){
                        id = "0";
                    }else {
                        UIManager UI = new UIManager();
                        UI.put("OptionPane.background", new ColorUIResource(color3));
                        UI.put("Panel.background", new ColorUIResource(color3));
                        JOptionPane.showMessageDialog(gui.getMain().getjTable(), "Ошибка! Некорректный учетный номер.");
                        checher = false;
                    }
                }

                String registrationDate = registrationDateField.getText();
                try {
                    if (registrationDate.equals("")) {
                        registrationDate = null;
                    } else {
                        String[] regDate = registrationDate.split("\\.");
                        String regDateNew = regDate[2] + "-" + regDate[1] + "-" + regDate[0];
                        registrationDate = regDateNew;
                    }
                }catch (ArrayIndexOutOfBoundsException e1){
                    UIManager UI = new UIManager();
                    UI.put("OptionPane.background", new ColorUIResource(color3));
                    UI.put("Panel.background", new ColorUIResource(color3));
                    JOptionPane.showMessageDialog(gui.getMain().getjTable(), "Ошибка! Некорректное значение даты регистрации.");
                    checher = false;
                }

                String type = typeField.getText();
                if (type.equals("")){
                    type = "null";
                }

                String volume = volumeField.getText();
                try{
                    parseInt(volume);
                }catch (NumberFormatException e1){
                    if (volume.equals("")){
                        volume = "0";
                    }else {
                        UIManager UI = new UIManager();
                        UI.put("OptionPane.background", new ColorUIResource(color3));
                        UI.put("Panel.background", new ColorUIResource(color3));
                        JOptionPane.showMessageDialog(gui.getMain().getjTable(), "Ошибка! Некорректное значение объема памяти.");
                        checher = false;
                    }
                }
                String sn2 = snField.getText();
                if (sn2.equals("")){
                    sn2 = "null";
                }
                String department = departmentField.getText();
                if (department.equals("")){
                    department = "null";
                }
                String owner = ownerField.getText();
                if (owner.equals("")){
                    owner = "null";
                }

                String returnDate = returnDateField.getText();
                try {
                    if (returnDate.equals("")) {
                        returnDate = null;
                    } else {
                        String[] retDate = returnDate.split("\\.");
                        String retDateNew = retDate[2] + "-" + retDate[1] + "-" + retDate[0];
                        returnDate = retDateNew;
                    }
                }catch (ArrayIndexOutOfBoundsException e1){
                    UIManager UI = new UIManager();
                    UI.put("OptionPane.background", new ColorUIResource(color3));
                    UI.put("Panel.background", new ColorUIResource(color3));
                    JOptionPane.showMessageDialog(gui.getMain().getjTable(), "Ошибка! Некорректное значение даты возврата.");
                    checher = false;
                }
                String destroyed = destroyedField.getText();
                if (destroyed.equals("")){
                    destroyed = "null";
                }

                if (checher) {
                    FlashDrive flashDrive2 = new FlashDrive(parseInt(id), registrationDate, type, parseInt(volume), sn, department, owner, returnDate, destroyed);

                    if (DBManager.addFlash(flashDrive2)) {
                        gui.getCollectionManager().getFlashDrivers().add(flashDrive2);
                        String element = flashDrive2.toString();
                        String[] arguments = element.split(", ");
                        gui.getMain().getTableModel().insertRow(0, arguments);
                        addFrame.setVisible(false);
                    }else {
                        UIManager UI = new UIManager();
                        UI.put("OptionPane.background", new ColorUIResource(color3));
                        UI.put("Panel.background", new ColorUIResource(color3));
                        JOptionPane.showMessageDialog(gui.getMain().getjTable(), "Ошибка! Не удалось добавить элемент с номером " + flashDrive.getId());}
                }

            });
            addFrame.add(buttonAuth1, new GridBagConstraints(0, count++, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 80, 30, 0), 0, 0));
             }else {
            JButton buttonAuth = new JButton("Редактировать");
            buttonAuth.setBackground(color4);
            createComponentForAdd(addFrame, "", validate, count++);
            createComponentForAdd(addFrame, "Элемент с таким серийным номером есть в базе.", validate, count++);
            createComponentForAdd(addFrame, "", validate, count++);
            JTextField idField = new JTextField(String.valueOf(flashDrive.getId()));
            JTextField registrationDateField = new JTextField(flashDrive.getRegistrationDate());
            JTextField typeField = new JTextField(flashDrive.getType());
            JTextField volumeField = new JTextField(String.valueOf(flashDrive.getVolume()));
            JTextField snField = new JTextField(flashDrive.getSn());
            JTextField departmentField = new JTextField(flashDrive.getDepartment());
            JTextField ownerField = new JTextField(flashDrive.getOwner());
            JTextField returnDateField = new JTextField(flashDrive.getReturnDate());
            JTextField destroyedField = new JTextField(flashDrive.getDestroyed());;
            createComponentForAdd(addFrame, "Учетный №", idField, count++);
            createComponentForAdd(addFrame, "Дата поставки на учет", registrationDateField, count++);
            createComponentForAdd(addFrame, "Тип носителя", typeField, count++);
            createComponentForAdd(addFrame, "Объем памяти", volumeField, count++);
            createComponentForAdd(addFrame, "S/N", snField, count++);
            createComponentForAdd(addFrame, "Отдел", departmentField, count++);
            createComponentForAdd(addFrame, "Кому выдан или отправлен ФИО", ownerField, count++);
            createComponentForAdd(addFrame, "Дата возврата", returnDateField, count++);
            createComponentForAdd(addFrame, "Дополнительная информация", destroyedField, count++);
            createComponentForAdd(addFrame, "", validate, count++);
            buttonAuth.addActionListener(e -> {

                boolean checher = true;

                String id = idField.getText();
                try{
                    parseInt(id);
                }catch (NumberFormatException e1){
                    if (id.equals("")){
                        id = "0";
                    }else {
                        UIManager UI = new UIManager();
                        UI.put("OptionPane.background", new ColorUIResource(color3));
                        UI.put("Panel.background", new ColorUIResource(color3));
                        JOptionPane.showMessageDialog(gui.getMain().getjTable(), "Ошибка! Некорректный учетный номер.");
                        checher = false;
                    }
                }

                String registrationDate = registrationDateField.getText();
                try{
                    if (registrationDate.equals("")||registrationDate.equals("null")){
                        registrationDate = null;
                    }else {
                        String[] regDateOld = registrationDate.split("-");
                        if (regDateOld.length == 1) {
                            String[] regDate = registrationDate.split("\\.");
                            registrationDate = regDate[2] + "-" + regDate[1] + "-" + regDate[0];
                        }
                    }
                }catch (ArrayIndexOutOfBoundsException e1){
                    UIManager UI = new UIManager();
                    UI.put("OptionPane.background", new ColorUIResource(color3));
                    UI.put("Panel.background", new ColorUIResource(color3));
                    JOptionPane.showMessageDialog(gui.getMain().getjTable(), "Ошибка! Некорректное значение даты регистрации.");
                    checher = false;
                }

                String type = typeField.getText();
                if (type.equals("")){
                    type = "null";
                }

                String volume = volumeField.getText();
                try{
                    parseInt(volume);
                }catch (NumberFormatException e1){
                    if (volume.equals("")){
                        volume = "0";
                    }else {
                        UIManager UI = new UIManager();
                        UI.put("OptionPane.background", new ColorUIResource(color3));
                        UI.put("Panel.background", new ColorUIResource(color3));
                        JOptionPane.showMessageDialog(gui.getMain().getjTable(), "Ошибка! Некорректное значение объема памяти.");
                        checher = false;
                    }
                }
                String sn2 = snField.getText();
                if (sn2.equals("")){
                    sn2 = "null";
                }
                String department = departmentField.getText();
                if (department.equals("")){
                    department = "null";
                }
                String owner = ownerField.getText();
                if (owner.equals("")){
                    owner = "null";
                }

                String returnDate = returnDateField.getText();
                try {
                    if (returnDate.equals("") || returnDate.equals("null")) {
                        returnDate = null;
                    }else {
                        String[] retDateOld = returnDate.split("-");
                        if (retDateOld.length == 1) {
                            String[] retDate = returnDate.split("\\.");
                            returnDate = retDate[2] + "-" + retDate[1] + "-" + retDate[0];
                        }
                    }
                }catch (ArrayIndexOutOfBoundsException e1){
                    UIManager UI = new UIManager();
                    UI.put("OptionPane.background", new ColorUIResource(color3));
                    UI.put("Panel.background", new ColorUIResource(color3));
                    JOptionPane.showMessageDialog(gui.getMain().getjTable(), "Ошибка! Некорректное значение даты возврата.");
                    checher = false;
                }
                String destroyed = destroyedField.getText();
                if (destroyed.equals("")){
                    destroyed = "null";
                }
                if (checher) {
                    gui.getCollectionManager().removeById(flashDrive.getId());
                    FlashDrive flashDriveNew = new FlashDrive(parseInt(id), registrationDate, type, parseInt(volume), sn, department, owner, returnDate, destroyed);
                    if (DBManager.addFlash(flashDriveNew)) {
                        gui.getCollectionManager().getFlashDrivers().add(flashDriveNew);
                        String element = flashDriveNew.toString();
                        String[] arguments = element.split(", ");
                        gui.getMain().getTableModel().insertRow(0, arguments);
                        addFrame.setVisible(false);
                    } else {
                        gui.getCollectionManager().getFlashDrivers().add(flashDrive);
                        UIManager UI = new UIManager();
                        UI.put("OptionPane.background", new ColorUIResource(color3));
                        UI.put("Panel.background", new ColorUIResource(color3));
                        JOptionPane.showMessageDialog(gui.getMain().getjTable(), "Ошибка! Не удалось редактировать элемент с номером " + flashDrive.getId());
                    }
                }
            });

            addFrame.add(buttonAuth, new GridBagConstraints(0, count++, 1, 1, 1.0, 1.0,
                    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 80, 30, 0), 0, 0));

        }

        addFrame.setVisible(true);
    }

    /**
     * Метод добавляет элементы на фрейм
     *
     * @param addFrame
     * @param arg1
     * @param arg2
     * @param position
     */
    private void createComponentForAdd(JFrame addFrame, String arg1, JComponent arg2, int position) {
        addFrame.add(new JLabel(arg1), new GridBagConstraints(0, position, 1, 1, 1.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 20, 0, 0), 0, 5));
        addFrame.add(arg2, new GridBagConstraints(1, position, 1, 1, 6.0, 1.0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 20), 0, 5));
    }
}
