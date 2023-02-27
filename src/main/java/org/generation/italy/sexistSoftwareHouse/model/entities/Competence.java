package org.generation.italy.sexistSoftwareHouse.model.entities;

public class Competence implements Comparable<Competence>{
    private long id;
    private String name;
    private String description;
    private Level level;
    public Competence(long id, String name, String description, Level level){
        this.id=id;
        this.name=name;
        this.description=description;
        this.level=level;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Level getLevel() {
        return level;
    }
    public boolean hasLevelGreaterOrEqual(Level level){
        return this.level.greaterOrEqual(level);
    }

    @Override
    public String toString(){
        return String.format("%d: %s, description: %s, level: %s. \n "
                ,id,name,description,level);
    }

    @Override
    public int compareTo(Competence c) {
        return this.level.getPower()-c.getLevel().getPower();
    }
}
