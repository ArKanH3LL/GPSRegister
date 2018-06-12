package informatica.orion.gpsregister;

import android.app.Application;


import informatica.orion.gpsregister.database.GPSRegisterDataBase;

public class GPSRegisterApp extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate(){
        super.onCreate();

        mAppExecutors = new AppExecutors();
    }

    public GPSRegisterDataBase getDatabase(){
        return GPSRegisterDataBase.getInstance(this, mAppExecutors);
    }

    public DataRepository getRepository(){
        return DataRepository.getInstance(getDatabase());
    }

}
