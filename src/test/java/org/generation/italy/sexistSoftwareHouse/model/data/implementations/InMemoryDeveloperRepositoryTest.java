package org.generation.italy.sexistSoftwareHouse.model.data.implementations;

import org.generation.italy.sexistSoftwareHouse.model.entities.Competence;
import org.generation.italy.sexistSoftwareHouse.model.entities.Developer;
import org.generation.italy.sexistSoftwareHouse.model.entities.Level;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryDeveloperRepositoryTest {
    private Developer d1 = new Developer(1, "pippo", "baudo","f", LocalDate.now(), 345, "Junior Developer",
            List.of(new Competence(1, "java", "cose", Level.ADVANCED),
                    new Competence(2, "phyton", "cose", Level.BASE),
                    new Competence(3,"SQL", "cose", Level.BASE)));
    private Developer d2 = new Developer(2, "mario", "rossi","m", LocalDate.now(), 346, "Junior Developer",
            List.of(new Competence(1, "java", "cose", Level.DIVINE),
                    new Competence(2, "phyton", "cose", Level.GURU),
                    new Competence(3,"SQL", "cose", Level.BASE)));
    private InMemoryDeveloperRepository repo;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        repo = new InMemoryDeveloperRepository();
        repo.data.put(d1.getId(), d1);
        repo.data.put(d2.getId(), d2);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void create() {
    }

    @org.junit.jupiter.api.Test
    void developerInfo() {
    }

    @org.junit.jupiter.api.Test
    void filterByPartAndLevel() {
    }

    @org.junit.jupiter.api.Test
    void minNumOfCompetences() {
    }

    @org.junit.jupiter.api.Test
    void findDevsByNumOfCompetenceAndLevel() {
        List<Developer> devs= repo.findDevsByNumOfCompetenceAndLevel(2, Level.BASE);
        assertEquals(2,devs.size());
        devs = repo.findDevsByNumOfCompetenceAndLevel(2, Level.GURU);
        assertEquals(1, devs.size());
        assertEquals(d2.getId(), devs.get(0).getId());
        devs = repo.findDevsByNumOfCompetenceAndLevel(3, Level.GURU);
        assertEquals(0, devs.size());
    }

    @org.junit.jupiter.api.Test
    void maxLevelOfACompetence() {
        Optional<Level> ol = repo.findMaxLevelOfACompetence("phyton");
        assertTrue(ol.isPresent());
        assertEquals(Level.GURU, ol.get());
        ol = repo.findMaxLevelOfACompetence("java");
        assertTrue(ol.isPresent());
        assertEquals(Level.DIVINE, ol.get());
        ol=repo.findMaxLevelOfACompetence("javascript");
        assertTrue(ol.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void allCompetences() {
        List<String> nc = repo.allCompetences();
        assertEquals(3,nc.size());
        Set<String> sc = new HashSet<>(nc);
        assertEquals(3, sc.size());
    }

    @org.junit.jupiter.api.Test
    void mediumSalary() {
    }

    @org.junit.jupiter.api.Test
    void maxSalary() {
    }

    @org.junit.jupiter.api.Test
    void modeYearsOfWork() {
    }

    @org.junit.jupiter.api.Test
    void isSexist() {
    }
}