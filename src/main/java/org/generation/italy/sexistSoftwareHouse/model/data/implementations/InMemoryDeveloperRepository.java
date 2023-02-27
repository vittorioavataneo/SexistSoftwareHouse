package org.generation.italy.sexistSoftwareHouse.model.data.implementations;

import org.generation.italy.sexistSoftwareHouse.model.data.abstractions.DeveloperRepository;
import org.generation.italy.sexistSoftwareHouse.model.entities.Competence;
import org.generation.italy.sexistSoftwareHouse.model.entities.Developer;
import org.generation.italy.sexistSoftwareHouse.model.entities.Level;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryDeveloperRepository implements DeveloperRepository {
    private static long nextId;
    static Map<Long, Developer> data= new HashMap<>();

    @Override
    public Developer create(Developer developer) {
        nextId ++;
        Developer dev = data.put(nextId, developer);
        developer.setId(nextId);
        return dev;
    }

    @Override
    public List<String> developerInfo() {
        return data.values().stream().map(developer -> String.format("""
                    Firstname: %s
                    Lastname: %s
                    Title: %s
                    Number of competences: %d""", developer.getFirstname(), developer.getLastname(),
                    developer.getTitle(), developer.getCompetence().size())).toList();
    }

    @Override
    public List<Developer> filterByPartAndLevel(String part, Level level) {
        return data.values().stream().filter(dev -> dev.getCompetence().stream()
                .anyMatch(c -> c.getName().contains(part) && c.getLevel() == level)).toList();
    }
    @Override
    public List<Developer> minNumOfCompetences(int numMinCompetence) {
        return data.values().stream().filter(d -> d.hasNumOfCompetence(numMinCompetence)).toList();
    }

    @Override
    public List<Developer> findDevsByNumOfCompetenceAndLevel(int numMinCompetence, Level minLevel) {
        //return data.values().stream().filter(d -> d.hasNumOfCompetence(numMinCompetence) &&
                //d.getCompetence().stream().anyMatch(c->c.getLevel().ordinal()>=minLevel.ordinal())).toList();
        return data.values().stream()
                .filter(d -> d.getCompetencesAboveLevel(minLevel).size() >= numMinCompetence)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Level> findMaxLevelOfACompetence(String competenceName) {
        /*Stream<Developer> dev = data.values().stream().filter(d->d.getCompetence().stream()
                        .anyMatch(c->c.getName().equalsIgnoreCase(competenceName)));
        var devLV = dev.flatMap(d->d.getCompetence().stream()).map(Competence::getLevel);
        return devLV.max(Comparator.comparingInt(l->l.ordinal()));*/
        var oc =data.values().stream().flatMap(d->d.getCompetence().stream())
                .filter(c->c.getName().equalsIgnoreCase(competenceName)).max(Competence::compareTo);
        return oc.map(Competence::getLevel);
    }

    @Override
    public List<String> allCompetences() {
            Set<String> competences = data.values().stream()
                    .flatMap(d -> d.getCompetence().stream())
                    .map(Competence::getName)
                    .collect(Collectors.toSet());

            return competences.stream().sorted()
                                       .collect(Collectors.toList());
    }

    @Override
    public double mediumSalary() {
        return data.values().stream().mapToDouble(Developer :: getSalary).sum() / data.size();
    }

    @Override
    public Optional<Developer> maxSalary() {
        return data.values().stream().max(Comparator.comparingDouble(Developer::getSalary));
        //return data.values().stream().max(Comparator.comparingDouble(Developer::getSalary)).get().getSalary();
    }
    @Override
    public Optional<Integer> modeYearsOfWork() {
        //(LocalDate.now().toEpochDay() - d.getHiringDate().toEpochDay())/365)
        var mapTime = data.values().stream().collect(Collectors.groupingBy(Developer::getEmploymentYears, Collectors.counting()));
        var maxYears = mapTime.entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue));
        return maxYears.map(Map.Entry::getKey);
    }

    @Override
    public List<Developer> checkSexist(){
        List<Developer> firedDevelopers = new ArrayList<>();
        OptionalDouble minMaleSalary = data.values().stream().filter(Developer::isMale)
                                                               .mapToDouble(Developer::getSalary)
                                                               .min();
        OptionalDouble maxFemaleSalary = data.values().stream().filter(d->!d.isMale())
                .mapToDouble(Developer::getSalary)
                .max();
        if(minMaleSalary.isPresent() && maxFemaleSalary.isPresent()
                && minMaleSalary.getAsDouble()<maxFemaleSalary.getAsDouble()){
                firedDevelopers = data.values().stream().filter(d -> d.getSalary() > minMaleSalary.getAsDouble()
                        && !d.isMale()).toList();
        }
        firedDevelopers.forEach(d->data.remove(d.getId()));
        return firedDevelopers;
    }
}
