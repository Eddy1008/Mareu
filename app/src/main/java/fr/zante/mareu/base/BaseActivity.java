package fr.zante.mareu.base;

import androidx.appcompat.app.AppCompatActivity;

import fr.zante.mareu.MareuApplication;
import fr.zante.mareu.repository.Repository;

public abstract class BaseActivity extends AppCompatActivity {

    public Repository getRepository() {
        return ((MareuApplication) getApplication()).getRepository();
    }
}
