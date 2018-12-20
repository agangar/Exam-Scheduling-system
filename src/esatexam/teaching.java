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
public class teaching {
    String name;
    String[] duties=new String[3];
    int nod;
    

    public teaching(String name, String[] duties, int nod) {
        this.name = name;
        this.duties = duties;
        this.nod = nod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getDuties() {
        return duties;
    }

    public void setDuties(String[] duties) {
        this.duties = duties;
    }

    public int getNod() {
        return nod;
    }

    public void setNod(int nod) {
        this.nod = nod;
    }
    
}
