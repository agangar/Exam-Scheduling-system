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
public class Subject {
    int Id;
 String subject_id,name,sem,year,branch;
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
   

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public String getName() {
        return name;
    }

    public String getSem() {
        return sem;
    }

    public String getYear() {
        return year;
    }

    public String getBranch() {
        return branch;
    }

    public Subject(int Id,String subject_id, String name, String sem, String year, String branch) {
        this.Id=Id;
        this.subject_id = subject_id;
        this.name = name;
        this.sem = sem;
        this.year = year;
        this.branch = branch;
    }
    
}
