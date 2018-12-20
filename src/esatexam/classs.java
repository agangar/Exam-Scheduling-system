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
public class classs {
   int Id;
Integer Room_no;
    String Branch,Year;
   Integer Nos;
    String Division;
 String Except;
 Integer Start_no;

    public classs(int Id,Integer Room_no, String Branch, String Year, Integer Nos, String Division, String Except,Integer Start_no) {
        this.Id=Id;
        this.Room_no = Room_no;
        this.Branch = Branch;
        this.Year = Year;
        this.Nos = Nos;
        this.Division = Division;
        this.Except = Except;
        this.Start_no=Start_no;
    }

    public Integer getStart_no() {
        return Start_no;
    }

    public void setStart_no(Integer Start_no) {
        this.Start_no = Start_no;
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

    public Integer getNos() {
        return Nos;
    }

    public void setNos(Integer Nos) {
        this.Nos = Nos;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String Division) {
        this.Division = Division;
    }

    public String getExcept() {
        return Except;
    }

    public void setExcept(String Except) {
        this.Except = Except;
    }
    
}