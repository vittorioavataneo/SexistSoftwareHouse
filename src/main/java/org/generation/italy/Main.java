package org.generation.italy;

import org.generation.italy.sexistSoftwareHouse.model.data.implementations.InMemoryDeveloperRepository;
import org.generation.italy.sexistSoftwareHouse.model.services.implementations.StandardSoftwareService;
import org.generation.italy.sexistSoftwareHouse.view.UserInterfaceConsole;

public class Main {
    public static void main(String[] args) {
        var main = new UserInterfaceConsole( new StandardSoftwareService(new
                InMemoryDeveloperRepository()));
        main.start();
    }
}