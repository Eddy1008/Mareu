package fr.zante.mareu.di;

import fr.zante.mareu.repository.Repository;
import fr.zante.mareu.service.FakeApiService;

public class Injection {

    private static Repository repository = new Repository(new FakeApiService());

    public static Repository getRepository() { return repository; }

    public static Repository createRepository() {
        return new Repository(new FakeApiService());
    }
}
