package org.generation.italy.sexistSoftwareHouse.model.entities;

import java.time.LocalDate;
import java.util.List;

public class Developer {
    private long id;
    private String firstname;
    private String lastname;
    private String sex;
    private LocalDate hiringDate;
    private double salary;
    private String title;
    private List<Competence> competence;

    public Developer(long id, String firstname, String lastname, String sex, LocalDate hiringDate,
                     double salary, String title, List<Competence> competence){
        this.id=id;
        this.firstname=firstname;
        this.lastname=lastname;
        this.sex=sex;
        this.hiringDate=hiringDate;
        this.salary=salary;
        this.title =title;
        this.competence=competence;
    }

    public long getId() {
        return id;
    }

    public long setId( long id){
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSex() {
        return sex;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public double getSalary() {
        return salary;
    }

    public String getTitle() {
        return title;
    }

    public List<Competence> getCompetence() {
        return competence;
    }

    public int getNumCompetence(){
        return competence.size();
    }
    public boolean hasNumOfCompetence(int n){
        return getNumCompetence()>=n;
    }
    public List<Competence> getCompetencesAboveLevel(Level level){
        return competence.stream().filter(c->c.hasLevelGreaterOrEqual(level)).toList();
    }
    public int getEmploymentYears(){
        return getHiringDate().until(LocalDate.now()).getYears();
    }
    public boolean isMale(){
        return getSex().equalsIgnoreCase("m");
    }
    @Override
    public String toString(){
        return String.format("""
                Developer %d
                Firstname: %s
                Lastname: %s
                Sex: %s
                Hiring date: %s
                Salary: %.2f
                Title: %s
                Competences:
                """+ competence.toString()
                ,id,firstname,lastname,sex,hiringDate,salary,title);
    }
}
