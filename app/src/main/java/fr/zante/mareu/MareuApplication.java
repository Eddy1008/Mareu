package fr.zante.mareu;

import android.app.Application;

import androidx.annotation.VisibleForTesting;

import fr.zante.mareu.di.Injection;
import fr.zante.mareu.repository.Repository;

public class MareuApplication extends Application {

    private Repository repository;
    public Repository getRepository() {
        if (repository == null) repository = Injection.getRepository();
        return repository;
    }

    @VisibleForTesting
    public void resetRepository() { repository = null; }

}
