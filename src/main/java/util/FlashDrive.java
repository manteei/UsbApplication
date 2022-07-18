package main.java.util;

import java.time.LocalDate;
import java.util.Date;

/**
 * Класс, описывающий элементы базы данных.
 */
public class FlashDrive {
    private int id;
    private String registrationDate;
    private String type;
    private int volume;
    private  String sn;

    private String department;
    private String owner;

    private String returnDate;
    private String destroyed;
    public FlashDrive(int id, String registrationDate, String type, int volume, String sn, String department, String owner,  String returnDate, String destroyed){
        this.id = id;
        this.registrationDate = registrationDate;
        this.type = type;
        this.volume = volume;
        this.sn = sn;
        this.department = department;
        this.owner = owner;

        this.returnDate = returnDate;
        this.destroyed = destroyed;
    }

    public int getId() {
        return id;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getType() {
        return type;
    }

    public int getVolume() {
        return volume;
    }

    public String getSn() {
        return sn;
    }

    public String getOwner() {
        return owner;
    }


    public String getReturnDate() {
        return returnDate;
    }
    public String getDestroyed(){
        return destroyed;
    }

    public String getDepartment() {
        return department;
    }


    @Override
    public String toString(){
        return  id + ", " + registrationDate  + ", " + type +  ", " + volume+ ", " + sn+ ", " +department + ", " +owner + ", " +returnDate+ ", " +destroyed;

    }

}
