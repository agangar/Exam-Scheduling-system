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
public class branch {
        String shortform,name,color;
        int branchid;

    public branch(String shortform, String name, String color, int branchid) {
        this.shortform = shortform;
        this.name = name;
        this.color = color;
        this.branchid=branchid;
    }

    public String getShortform() {
        return shortform;
    }

    public int getBranchid() {
        return branchid;
    }

    public void setBranchid(int branchid) {
        this.branchid = branchid;
    }

    public void setShortform(String shortform) {
        this.shortform = shortform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
        
    
}
