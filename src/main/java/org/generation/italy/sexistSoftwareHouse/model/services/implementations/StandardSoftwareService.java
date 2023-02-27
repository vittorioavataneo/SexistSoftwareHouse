package org.generation.italy.sexistSoftwareHouse.model.services.implementations;

import org.generation.italy.sexistSoftwareHouse.model.data.abstractions.DeveloperRepository;
import org.generation.italy.sexistSoftwareHouse.model.entities.Developer;
import org.generation.italy.sexistSoftwareHouse.model.entities.Level;
import org.generation.italy.sexistSoftwareHouse.model.services.abstractions.AbstractSoftwareService;

import java.util.List;
import java.util.Optional;

public class StandardSoftwareService implements AbstractSoftwareService {

    private DeveloperRepository repo;
    public StandardSoftwareService (DeveloperRepository repo){
        this.repo=repo;
    }
    @Override
    public Developer addDeveloper(Developer developer) {
        return repo.create(developer);
    }

    @Override
    public List<String> showDeveloperInfo() {
        return repo.developerInfo();
    }

    @Override
    public List<Developer> showDevelopersWithCertainNumberOfCompetence(int numMinCompetence) {
        return repo.minNumOfCompetences(numMinCompetence);
    }

    @Override
    public List<Developer> showDevelopersWithCertainNumberOfCompetenceAndLevel(int numMinCompetence, Level minLevel) {
        return repo.findDevsByNumOfCompetenceAndLevel(numMinCompetence, minLevel);
    }

    @Override
    public List<Developer> filterDeveloperByPartAndLevel(String part, Level level) {
        return repo.filterByPartAndLevel(part, level);
    }

    @Override
    public Optional<Level> maxLevelOfACertainCompetence(String competenceName) {
        return repo.findMaxLevelOfACompetence(competenceName);
    }

    @Override
    public List<String> showAllDevelopersCompetence() {
        return repo.allCompetences();
    }

    @Override
    public double mediumDeveloperSalary() {
        return repo.mediumSalary();
    }

    @Override
    public Optional<Developer> maxDeveloperSalary() {
        return repo.maxSalary();
    }

    @Override
    public Optional<Integer> modeOfDevelopersYearsOfWork() {
        return repo.modeYearsOfWork();
    }

    @Override
    public List<Developer> checkHouseSexist() {
        return repo.checkSexist();
    }
}
