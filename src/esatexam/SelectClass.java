/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esatexam;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Harsh
 */
public class SelectClass {
    CheckBox select;
    int Id;
    Integer Room_no;
    String Branch,Year;
    String Division;

    public SelectClass(int Id, Integer Room_no, String Branch, String Year, String Division) {
           this.select=new CheckBox();
        this.Id = Id;
        this.Room_no = Room_no;
        this.Branch = Branch;
        this.Year = Year;
        
        this.Division = Division;
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select.setSelected(true);
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Integer getRoom_no() {
        return Room_no;
    }

    public void setRoom_no(Integer Room_no) {
        this.Room_no = Room_no;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String Branch) {
        this.Branch = Branch;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }


    public String getDivision() {
        return Division;
    }

    public void setDivision(String Division) {
        this.Division = Division;
    }
    
   
}
