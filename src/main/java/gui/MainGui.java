package main.java.gui;

import main.java.dataBase.DBManager;
import main.java.util.FlashDrive;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;


public class MainGui   {
    private GUI gui;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JTable jTable = new JTable(tableModel);
    private RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
    private JFrame mainFrame = new JFrame("");

    public MainGui(GUI gui) {
        this.gui = gui;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }


    public JTable getjTable() {
        return jTable;
    }

    public void createMainFrame() {
        Font fnt = new Font("шрифт", Font.PLAIN, 16);
        Font fnt1 = new Font("шрифт", Font.PLAIN, 12);
        Color col1 = new Color(248, 249, 250);
        Color col2 = new Color(173, 186, 241);
        Color col3 = new Color(108, 136, 226);
        Color col4 = new Color(255, 212, 91);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(1000, 600);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.getContentPane().setBackground(col2);

        JPanel headerBag = new JPanel();
        headerBag.setLayout(new GridBagLayout());
        headerBag.setBackground(col2);
        JPanel upperHeader = new JPanel();
        upperHeader.setLayout(new GridBagLayout());
        upperHeader.setBackground(col2);

        JButton addButton = new JButton("Добавить новый элемент");
        addButton.setBackground(col1);
        upperHeader.add(addButton, new GridBagConstraints(12, 10, 11, 11, 11, 1.0,
                GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 10, 10));


        mainFrame.add(upperHeader, BorderLayout.NORTH);
        mainFrame.setVisible(true);

        String[] tableCol = new String[]{"Учетный №", "Дата поставки на учет", "Тип носителя", "Объем памяти", "S/N", "Отдел", "ФИО",
                 "Дата возврата", "Доп. информация"};
        for (String s : tableCol) {
            tableModel.addColumn(s);
        }
        JPanel table = new JPanel();
        table.setLayout(new GridBagLayout());
        table.setFont(fnt);
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("Журнал учета электронных накопителей", new JScrollPane(jTable));


        TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
        jTable.setRowSorter(rowSorter);

        rowSorter.setComparator(0, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1)-Integer.parseInt(o2);
            }
        });
        rowSorter.setComparator(3, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1)-Integer.parseInt(o2);
            }
        });
        rowSorter.setComparator(7, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1)-Integer.parseInt(o2);
            }
        });


        JPanel textArea = new JPanel();
        textArea.setLayout(new GridBagLayout());
        gui.getResult().setWrapStyleWord(true);
        gui.getResult().setEditable(false);

        table.add(jTabbedPane, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 10, 5), 0, 0));

        table.setBackground(col2);
        table.setForeground(col1);


        mainFrame.add(table);
        mainFrame.setLocationRelativeTo(null);
        jTable.setEnabled(false);
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                int row = jTable.rowAtPoint(new Point(e.getX(), e.getY()));
                String id = String.valueOf(jTable.getValueAt(row, 0));
                String registrationDate = String.valueOf(jTable.getValueAt(row, 1));
                String type = String.valueOf(jTable.getValueAt(row, 2));
                String volume = String.valueOf(jTable.getValueAt(row, 3));
                String sn = String.valueOf(jTable.getValueAt(row, 4));
                String department = String.valueOf(jTable.getValueAt(row, 5));
                String owner = String.valueOf(jTable.getValueAt(row, 6));
                String returnDate = String.valueOf(jTable.getValueAt(row, 7));
                String destroyed = String.valueOf(jTable.getValueAt(row, 8));

                FlashDrive flashDrive2 = new FlashDrive(Integer.parseInt(id), registrationDate, type, Integer.parseInt(volume), sn, department, owner,returnDate ,destroyed);

                UIManager UI = new UIManager();
                UI.put("OptionPane.background",new ColorUIResource(col2));
                UI.put("Panel.background",new ColorUIResource(col2));
                Object[] options = {"Редактировать", "Удалить"};
                int result = JOptionPane.showOptionDialog(gui.getMain().getjTable(), "Изменение элемента с номером " + id, "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (result == 0) {
                    gui.getEditorPane().createEditFrame(flashDrive2, row);
                }else if (result == 1){
                    gui.getCollectionManager().removeById(Integer.parseInt(id));
                    gui.getMain().getTableModel().removeRow(row);
                }


            }

        });

        addButton.addActionListener(e -> {
            gui.getAddCommandFrame().createAddFrame();
        });

        String condition = gui.getCollectionManager().show();
        if (!condition.equals("Коллекция пуста!")) {
            Scanner scanner = new Scanner(condition);
            do {
                String elements = scanner.nextLine();
                String[] arguments = elements.split(", ");
                tableModel.insertRow(0, arguments);
            } while (scanner.hasNextLine());
        }


        mainFrame.setVisible(true);;
    }

}