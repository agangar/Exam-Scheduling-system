/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esatexam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Harsh
 */
public class Variables {
    public static String name;
    public static int[] count;
    public static ObservableList<String> yearlist=FXCollections.observableArrayList();
     public static ObservableList<String>branchlist=FXCollections.observableArrayList();
    public static ObservableList<String> Semlist=FXCollections.observableArrayList("I","II","III","IV","V","VI","VII","VIII");
    public static String type;
}
