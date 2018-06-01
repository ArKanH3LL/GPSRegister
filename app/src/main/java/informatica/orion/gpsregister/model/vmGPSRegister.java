package informatica.orion.gpsregister.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import informatica.orion.gpsregister.entity.entUsuarios;
import informatica.orion.gpsregister.entity.entWaypoints;

public class vmGPSRegister extends AndroidViewModel {

    private rprGPSRegister mRepository;

    private LiveData<ArrayList<entUsuarios>> mAllUsuarios;
    private LiveData<ArrayList<entWaypoints>> mAllWaypoints;

    public vmGPSRegister(Application application) {
        super(application);
        mRepository = new rprGPSRegister(application);
        mAllUsuarios = mRepository.getAllUsuarios();
        mAllWaypoints=mRepository.getmAllWaypoints();
    }

    LiveData<ArrayList<entUsuarios>> getAllUsuarios() { return mAllUsuarios; }
    public void insert(entUsuarios usuarios) { mRepository.insert(usuarios); }

    LiveData<ArrayList<entWaypoints>> getAllWaypoints() { return mAllWaypoints; }
    public void insert(entWaypoints waypoints) { mRepository.insert(waypoints); }
}
