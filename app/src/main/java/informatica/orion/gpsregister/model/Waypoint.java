package informatica.orion.gpsregister.model;

import java.util.Date;

public interface Waypoint {
    int getId();
    int getUsuarioId();
    String getNis();
    String getOt();
    Date getFecha();
    String getCoordLAT();
    String getCoordLONG();
    String getCoordX();
    String getCoordY();
    String getCedula();
    String getObservacion();

}
