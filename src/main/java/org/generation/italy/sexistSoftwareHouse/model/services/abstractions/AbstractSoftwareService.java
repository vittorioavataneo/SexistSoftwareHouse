package org.generation.italy.sexistSoftwareHouse.model.services.abstractions;

import org.generation.italy.sexistSoftwareHouse.model.entities.Developer;
import org.generation.italy.sexistSoftwareHouse.model.entities.Level;

import java.util.List;
import java.util.Optional;

public interface AbstractSoftwareService {
    Developer addDeveloper (Developer developer);
    List<String> showDeveloperInfo();
    List<Developer> showDevelopersWithCertainNumberOfCompetence(int numMinCompetence);
    List<Developer> showDevelopersWithCertainNumberOfCompetenceAndLevel(int numMinCompetence, Level minLevel);
    List<Developer> filterDeveloperByPartAndLevel(String part, Level level);
    Optional<Level> maxLevelOfACertainCompetence(String competenceName);
    List<String> showAllDevelopersCompetence();
    double mediumDeveloperSalary();
    Optional<Developer> maxDeveloperSalary();
    Optional<Integer> modeOfDevelopersYearsOfWork();
    List<Developer> checkHouseSexist();

}
