package main.java.util;

import main.java.dataBase.DBManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Класс с методами взаимодействия с коллекцией
 */
public class CollectionManager {
    private ArrayList<FlashDrive> collection;

    public CollectionManager() {
        collection = new ArrayList<FlashDrive>();
    }
    public ArrayList<FlashDrive> getFlashDrivers(){
        return collection;
    }

    public String show(){
        if (collection.size()==0){
            return "Коллекция пуста!";
        }
        return collection
                .stream()
                .map(FlashDrive::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Метод удаляет элемент из базы данных и из коллекции по id
     * @param id
     * @return
     */
    public void removeById(int id){
        DBManager.removeById(id);
        collection.remove(getFlashById(id));
        System.out.println(id + "gfgsfjhsdgf");
        System.out.println("Вроде удален");

    }

    public FlashDrive searchSN(String sn){
        if (!sn.equals("") & !sn.equals("null")){
            for (FlashDrive flashDrive : collection) {
                if (flashDrive.getSn() != null) {
                    String collectionSn = flashDrive.getSn().trim();
                    if ((collectionSn).equals(sn.trim())) {
                        return flashDrive;
                    }
                }
            }
            return null;
        } return null;
    }

    public FlashDrive getFlashById(int id){
            for (FlashDrive flashDrive: collection){
                if (flashDrive.getId() == id){
                    return flashDrive;
                }
            }
        return null;
    }
    /**
     * Метод удаляет элемент из коллекции по id
     * @param id
     * @return
     */
    /*public boolean removeById(String sn){
        if (id < 1){
            return false;
        }
        for (FlashDrive flashDrive: collection){
            if(flashDrive.getId() == id){
                collection.remove(flashDrive);
                return true;
            }
        }
        return false;
    }*/
}
