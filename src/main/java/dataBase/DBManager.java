package main.java.dataBase;

import main.java.util.CollectionManager;
import main.java.util.FlashDrive;

import java.sql.*;

/**
 * Класс, взаимодействующий с базой данных
 */
public class DBManager {
    private static Connection connection = null;
    private static Statement statement = null;
    private static boolean connected;

    private static final String DELETE_BY_ID = "DELETE from electronic_storage WHERE id=?";
    private static final String LOAD = "SELECT * from electronic_storage";
    private static final String ADD = "INSERT INTO electronic_storage (id, registrationDate, type, volume, sn, department, owner,  returnDate, destroyed) VALUES (?,?,?,?,?,?,?,?,?)";

    static {
        connect();
    }

    public static boolean connect() {
        try {
            connection = ConnectionManager.open();
            if (connection != null) {
                System.out.println("Успешное подключение к базе данных!");
                statement = connection.createStatement();
                connected = true;
                return true;
            }
            if (connection == null) {
                System.out.println("Connection is null!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static void load(CollectionManager collectionManager) {
        try {
            ResultSet resultSet = getStatement().executeQuery(LOAD);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String registrationDate = resultSet.getString("registrationDate");
                String type = resultSet.getString("type");
                int volume = resultSet.getInt("volume");
                String sn = resultSet.getString("sn");
                String department = resultSet.getString("department");
                String owner = resultSet.getString("owner");
                String returnDate = resultSet.getString("returnDate");
                String destroyed = resultSet.getString("destroyed");
                FlashDrive flashDrive = new FlashDrive(id, registrationDate, type, volume, sn, department, owner, returnDate, destroyed);
                collectionManager.getFlashDrivers().add(flashDrive);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static boolean addFlash(FlashDrive flashDrive) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, flashDrive.getId());
            preparedStatement.setString(2,  flashDrive.getRegistrationDate());
            preparedStatement.setString(3, flashDrive.getType());
            preparedStatement.setInt(4, flashDrive.getVolume());
            preparedStatement.setString(5, flashDrive.getSn());
            preparedStatement.setString(6, flashDrive.getDepartment());
            preparedStatement.setString(7, flashDrive.getOwner());
            preparedStatement.setString(8,  flashDrive.getReturnDate());
            preparedStatement.setString(9, flashDrive.getDestroyed());

            if (preparedStatement.executeUpdate() != 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                /*if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    flashDrive.setId(id);
                }*/
                System.out.println("Элемент added!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
    public static boolean removeById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() != 0) {
                System.out.println("Элемент удален!");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Statement getStatement() {
        return statement;
    }
}
