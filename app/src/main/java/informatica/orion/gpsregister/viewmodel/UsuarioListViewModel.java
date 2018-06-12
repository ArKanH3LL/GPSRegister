package informatica.orion.gpsregister.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import informatica.orion.gpsregister.GPSRegisterApp;
import informatica.orion.gpsregister.database.entity.UsuarioEntity;

import java.util.List;

public class UsuarioListViewModel extends AndroidViewModel {

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<UsuarioEntity>> mObservableUsuarios;

    public UsuarioListViewModel(Application application) {
        super(application);

        mObservableUsuarios = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableUsuarios.setValue(null);

        LiveData<List<UsuarioEntity>> usuarios = ((GPSRegisterApp) application).getRepository()
                .getUsuarios();

        // observe the changes of the products from the database and forward them
        mObservableUsuarios.addSource(usuarios, mObservableUsuarios::setValue);
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<UsuarioEntity>> getUsuarios() {
        return mObservableUsuarios;
    }
}
