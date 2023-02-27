package org.generation.italy.sexistSoftwareHouse.model.entities;

public enum Level{
    BASE(1), INTERMEDIATE(10), ADVANCED(100), GURU(1000), DIVINE(10000);
    private int power;
    private Level(int power){
        this.power=power;
    }
    public boolean greaterOrEqual(Level other){
        return this.power>=other.power;
    }

    public int getPower() {
        return power;
    }
}
