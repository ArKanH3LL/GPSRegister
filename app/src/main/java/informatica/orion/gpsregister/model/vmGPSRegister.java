package informatica.orion.gpsregister.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import informatica.orion.gpsregister.entity.entUsuarios;
import informatica.orion.gpsregister.entity.entWaypoints;

public class vmGPSRegister extends AndroidViewModel {

    private rprGPSRegister mRepository;

    private LiveData<List<entUsuarios>> mAllUsuarios;
    private LiveData<List<entWaypoints>> mAllWaypoints;

    public vmGPSRegister(Application application) {
        super(application);
        mRepository = new rprGPSRegister(application);
        mAllUsuarios = mRepository.getAllUsuarios();
        mAllWaypoints=mRepository.getmAllWaypoints();
    }

    public LiveData<List<entUsuarios>> getAllUsuarios() { return mAllUsuarios; }
    public void insert(entUsuarios entUsuarios) { mRepository.insert(entUsuarios); }

    LiveData<List<entWaypoints>> getAllWaypoints() { return mAllWaypoints; }
    public void insert(entWaypoints entWaypoints) { mRepository.insert(entWaypoints); }
}
