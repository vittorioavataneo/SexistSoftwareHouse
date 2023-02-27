package org.generation.italy.sexistSoftwareHouse.model.data.abstractions;

import org.generation.italy.sexistSoftwareHouse.model.entities.Developer;
import org.generation.italy.sexistSoftwareHouse.model.entities.Level;

import java.util.List;
import java.util.Optional;

public interface DeveloperRepository {

    Developer create(Developer developer);
    List<String> developerInfo();
    List<Developer> filterByPartAndLevel (String part, Level level);
    List<Developer> minNumOfCompetences(int numMinCompetence);
    List<Developer> findDevsByNumOfCompetenceAndLevel(int numMinCompetence, Level minLevel);
    Optional<Level> findMaxLevelOfACompetence(String competenceName);
    List<String> allCompetences();
    double mediumSalary();
    Optional<Developer> maxSalary();
    Optional<Integer> modeYearsOfWork();
    List<Developer> checkSexist();

}
