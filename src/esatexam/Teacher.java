/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esatexam;

/**
 *
 * @author victim
 */
public class Teacher {
    int table_id;
    String table_name,table_senority,table_avaiability,table_subject;

    public Teacher(int table_id, String table_name, String table_senority, String table_avaiability, String table_subject) {
        this.table_id = table_id;
        this.table_name = table_name;
        this.table_senority = table_senority;
        this.table_avaiability = table_avaiability;
        this.table_subject = table_subject;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getTable_senority() {
        return table_senority;
    }

    public void setTable_senority(String table_senority) {
        this.table_senority = table_senority;
    }

    public String getTable_avaiability() {
        return table_avaiability;
    }

    public void setTable_avaiability(String table_avaiability) {
        this.table_avaiability = table_avaiability;
    }

    public String getTable_subject() {
        return table_subject;
    }

    public void setTable_subject(String table_subject) {
        this.table_subject = table_subject;
    }
    
    
}
