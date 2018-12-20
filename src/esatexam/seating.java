/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esatexam;

/**
 *
 * @author Harsh
 */
public class seating {
    int room_no;
    String classname;
    String nos;
    String exceptee;

    public seating(int room_no, String classname, String nos, String exceptee) {
        this.room_no = room_no;
        this.classname = classname;
        this.nos = nos;
        this.exceptee=exceptee;
    }

    public String getExceptee() {
        return exceptee;
    }

    public void setExceptee(String exceptee) {
        this.exceptee = exceptee;
    }

    public int getRoom_no() {
        return room_no;
    }

    public void setRoom_no(int room_no) {
        this.room_no = room_no;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getNos() {
        return nos;
    }

    public void setNos(String nos) {
        this.nos = nos;
    }
}
