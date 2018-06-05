package informatica.orion.gpsregister.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import informatica.orion.gpsregister.entity.entUsuarios;
import informatica.orion.gpsregister.repository.rpsUsuarios;

public class vmUsuarios extends AndroidViewModel {

    private rpsUsuarios mRepository;

    private LiveData<List<entUsuarios>> mAllUsuarios;

    public vmUsuarios(Application application) {
        super(application);
        mRepository = new rpsUsuarios(application);
        mAllUsuarios = mRepository.getmAllUsuarios();
    }

    public LiveData<List<entUsuarios>> getAllUsuarios() { return mAllUsuarios; }
    public void insert(entUsuarios usuarios) { mRepository.insert(usuarios); }
}
