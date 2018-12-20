/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esatexam;

import java.sql.Date;


/**
 *
 * @author Harsh
 */
public class Exam {
    
    int exam_id;
    String sem,branch,year,subject_name;
     Date date;
    String start_time,end_time;

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getExam_id() {
        return exam_id;
    }

    public String getSem() {
        return sem;
    }

    public String getBranch() {
        return branch;
    }

    public String getYear() {
        return year;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public Date getDate() {
        return date;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public Exam(int exam_id, String sem, String branch, String year, String subject_name, Date date, String start_time, String end_time) {
        this.exam_id = exam_id;
        this.sem = sem;
        this.branch = branch;
        this.year = year;
        this.subject_name = subject_name;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
    }
   
    
}
