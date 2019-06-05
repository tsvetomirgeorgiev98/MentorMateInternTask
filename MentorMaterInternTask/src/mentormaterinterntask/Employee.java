/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mentormaterinterntask;

/**
 *
 * @author Tsvetomir Georgiev
 */
public class Employee {

    private String name;
    private int totalSales, salesPeriod;
    private double experienceMultiplier, score;

    //constructors
    
    public Employee(){
        this.name = "Null";
        this.salesPeriod = 0;
        this.totalSales = 0;
        this.experienceMultiplier = 0.0;
        this.score = 0.0;
    }
    
    
    public Employee(String name, int totalSales, int salesPeriod, double experienceMultiplier, double score) {
        this.name = name;
        this.totalSales = totalSales;
        this.salesPeriod = salesPeriod;
        this.experienceMultiplier = experienceMultiplier;
        this.score = score;
    }

    //getters
    public String getName() {
        return name;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public int getSalesPeriod() {
        return salesPeriod;
    }

    public double getExperienceMultiplier() {
        return experienceMultiplier;
    }

    public double getScore() {
        return score;
    }
    
    

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public void setSalesPeriod(int salesPeriod) {
        this.salesPeriod = salesPeriod;
    }

    public void setExperienceMultiplier(double experienceMultiplier) {
        this.experienceMultiplier = experienceMultiplier;
    }

    //other methods

    @Override
    public String toString() {
        return "Employee{" + "name=" + name + ", totalSales=" + totalSales + ", salesPeriod=" + salesPeriod + ", experienceMultiplier=" + experienceMultiplier + ", score=" + score + '}';
    }

    
    

}
