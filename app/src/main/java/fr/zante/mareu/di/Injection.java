package fr.zante.mareu.di;

import fr.zante.mareu.repository.Repository;
import fr.zante.mareu.service.FakeApiService;

/**
 * responsible for defining how to build the repository allowing access to data
 * @author Eddy GALMAND
 */
public class Injection {

    /**
     * Constructor
     * use FakeApiService for data access and modification
     */
    private static Repository repository = new Repository(new FakeApiService());

    public static Repository getRepository() { return repository; }

    public static Repository createRepository() {
        return new Repository(new FakeApiService());
    }
}
