package org.generation.italy.sexistSoftwareHouse.view;

import org.generation.italy.sexistSoftwareHouse.model.entities.Competence;
import org.generation.italy.sexistSoftwareHouse.model.entities.Developer;
import org.generation.italy.sexistSoftwareHouse.model.entities.Level;
import org.generation.italy.sexistSoftwareHouse.model.services.abstractions.AbstractSoftwareService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Stream;

public class UserInterfaceConsole {
    private Scanner sc = new Scanner(System.in);
    private AbstractSoftwareService service;
    public UserInterfaceConsole(AbstractSoftwareService service) {
        this.service = service;
    }
    public void start() {
        System.out.println("""
                Buongiorno, benvenuto nel sistema di gestione developer.
                Questi sono i comandi disponibili:
                add = aggiungere un nuovo developer.
                info = puoi vedere nome, cognome, titolo e numero dei linguaggi del developer.
                n = tutti i developer con almeno n competenze.
                nl = tutti i developer con almeno n competenze e livello minimo.
                ml = il livello massimo di una competenza tra tutti i developer.
                competences = puoi vedere la lista delle competenze dei developer.
                med = puoi vedere il salario medio dei tuoi developer.
                max = puoi vedere il salario massimo dei tuoi developer.
                f = filtra per parte del nome e dal livello della competenza.
                mode = moda degli anni di lavoro.
                sexist = mi dice se il salario minimo degli uomini è maggiore a quelo massimo delle donne, se non lo è le licenzia.
                stop = per terminare il programma.
                """);

        do {
            menu(askCommand("Inserisci il comando che vuoi utilizzare: "));
        } while (true);
    }

    public String askCommand(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }

    public void menu(String command) {
        switch (command) {
            case "add":
                System.out.println("Puoi ora salvare un nuovo developer:");
                service.addDeveloper(developerMenu());
                break;
            case "info":
                System.out.println(service.showDeveloperInfo());
                break;
            case "n":
                int numMinCompetence = Integer.parseInt(askCommand("Inserisci il numero minimo di competenze: "));
                System.out.println(service.showDevelopersWithCertainNumberOfCompetence
                        (numMinCompetence).toString());
                break;
            case "nl":
                int numMinCompetences = Integer.parseInt(askCommand("Inserisci il numero minimo di competenze: "));
                Level minLevel = readLevel(askCommand("inserisci il livello minimo: "));
                System.out.println(service.showDevelopersWithCertainNumberOfCompetenceAndLevel(numMinCompetences, minLevel));
                break;
            case "ml":
                String s = askCommand("Inserisci il nome della competenza di cui vuoi sapere il livello massimo: ");
                Optional<Level> max = service.maxLevelOfACertainCompetence(s);
                if (max.isPresent()){
                    System.out.println(String.format("Il livello massimo di %s tra tutti gli sviluppatori è %s.",s ,
                            max.get()));
                }else {
                    System.out.println(String.format("La competenza %s non è presente tra le competenze dei tuoi sviluppatori",s));
                }
                break;
            case "competences":
                System.out.println("Le competenze dei tuoi sviluppatori sono: "+ service.showAllDevelopersCompetence());
                break;
            case "med":
                System.out.println("Il salario medio è " + service.mediumDeveloperSalary());
                break;
            case "max":
                if (service.maxDeveloperSalary().isPresent()){
                    System.out.println("Il salario massimo è " + service.maxDeveloperSalary().get().getSalary());
                }else{
                    System.out.println("Non ci sono ancora sviluppatori registrati");
                }
                break;
            case "f":
                String part = askCommand("Inserisci la stringa: ");
                Level level = readLevel(askCommand("inserisci il livello: "));
                System.out.println(service.filterDeveloperByPartAndLevel(part,level));
                break;
            case "mode":
                if (service.modeOfDevelopersYearsOfWork().isPresent()){
                    System.out.println("La moda degli anni di lavoro è "+ service.modeOfDevelopersYearsOfWork().get());
                }else {
                    System.out.println("Non ci sono ancora sviluppatori registrati");
                }
                break;
            case "sexist":
                var firedDev= service.checkHouseSexist();
                if(firedDev.isEmpty()){
                    System.out.println("Stai sereno la Software House è già sessista.");
                }else {
                    System.out.println("Pericolo scampato, abbiamo licenziato:");
                    firedDev.forEach(d->System.out.printf("Sviluppatrice %s %s, salario: %.2f%n",
                            d.getFirstname(), d.getLastname(), d.getSalary() ));
                }
                break;
            case "stop":
                System.out.println("Grazie per aver usato il nostro servizio di gestione developer, arrivederci!");
                System.exit(0);
            default:
                System.out.println("Il comando inserito è inesistente, controlla la tabella a inizio programma.");
        }
    }

    public Developer developerMenu() {
        long id = readLong(askCommand("Inserisci id: "));
        String firstname = askCommand("Inserisci il nome:");
        String lastname = askCommand("Inserisci il cognome:");
        String sex = readSex(askCommand("Inserisci il sesso:"));
        LocalDate hiringDate = readDate(askCommand("Inserisci data Assunzione:"));
        double salary = readDouble(askCommand("Inserisci il salario: "));
        String title = askCommand("Inserisci il titolo:");
        List<Competence> competences = competenceMenu();

        return new Developer(id, firstname, lastname, sex, hiringDate, salary, title, competences);
    }

    public List<Competence> competenceMenu() {
        long n = readLong(askCommand("Inserisci il numero di competenze da inserire: "));
        //List<Competence> competences = new ArrayList<>();
        /*for (int i = 0; i < n; i++) {
            long id = readLong(askCommand("Inserisci id: "));
            String name = askCommand("Inserisci il nome:");
            String description = askCommand("Insersci la descrizione: ");
            Level level = readLevel(askCommand("Inserisci il livello di competenza: "));
            competences.add(new Competence(id, name, description, level));
        }*/
        return Stream.generate(() -> new Competence(
                readLong(askCommand("Inserisci id: ")),
                askCommand("Inserisci il nome:"),
                askCommand("Insersci la descrizione: "),
                readLevel(askCommand("Inserisci il livello di competenza: "))))
                .limit(n).toList();
    }

    public String readSex(String s) {
        do{
            if (s.equalsIgnoreCase("m") || s.equalsIgnoreCase("f")) {
                break;
            } else {
                System.out.println("Formato inserito non valido, formati validi 'm' o 'f'.");
                s = askCommand("Inserisci nuovamente il sesso: ");
            }
        }while (true);
        return s;
    }

    public long readLong(String s){
        do {
            try{
                return Long.parseLong(s);
            }catch (NumberFormatException e){
                System.out.println("Formato inserito non valido, rinseriscilo nuovamente: ");
            }
            s = sc.nextLine();
        }while (true);
    }

    public double readDouble(String s){
        do {
            try{
                return Double.parseDouble(s);
            }catch (NumberFormatException e){
                System.out.println("Formato inserito non valido, rinseriscilo nuovamente: ");
            }
            s = sc.nextLine();
        }while (true);
    }

    public Level readLevel(String s){
        do {
            String ss = s;
            boolean isEnum = Arrays.stream(Level.values()).map(Level::name).anyMatch(n -> n.equals(ss));
            if (isEnum) {
                return Level.valueOf(s);
            } else {
                System.out.println("""
                        Livello inserito non valido, sono validi i seguenti livelli:
                        BASE
                        INTERMEDIATE
                        ADVANCED
                        GURU
                        DIVINE
                        Rinserisci il livello:""");
            }
            s = sc.nextLine();
        }while (true);
    }

    public LocalDate readDate(String s){
        do{
            try {
                return LocalDate.parse(s);
            }catch (DateTimeParseException e){
                System.out.println("Formato inserito non valido, rinserisci la data in formato YY-MM-GG:");
            }
            s=sc.nextLine();
        }while(true);
    }
}

