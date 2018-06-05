package informatica.orion.gpsregister.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import informatica.orion.gpsregister.entity.entWaypoints;
import informatica.orion.gpsregister.repository.rpsWaypoints;

public class vmWaypoints extends AndroidViewModel {

    private rpsWaypoints mRepository;

    private LiveData<List<entWaypoints>> mAllWaypoints;

    public vmWaypoints(Application application) {
        super(application);
        mRepository = new rpsWaypoints(application);
        mAllWaypoints= mRepository.getmAllWaypoints();
    }

    LiveData<List<entWaypoints>> getAllWaypoints() { return mAllWaypoints; }
    public void insert(entWaypoints waypoints) { mRepository.insert(waypoints); }
}
