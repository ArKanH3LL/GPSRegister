package informatica.orion.gpsregister.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import informatica.orion.gpsregister.model.Waypoint;

@Entity(tableName = "waypoints",
        foreignKeys ={@ForeignKey(entity = UsuarioEntity.class, parentColumns = "id",
                childColumns = "usuarioId",
                onDelete = ForeignKey.CASCADE)}, indices = {@Index(value = "usuarioId")})

public class WaypointEntity implements Waypoint {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int usuarioId;
    private String nis;
    private String ot;
    private Date fecha;
    private String coordLAT;
    private String coordLONG;
    private String coordX;
    private String coordY;
    private String cedula;
    private String observacion;

    @Override
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Override
    public int getUsuarioId() {return usuarioId;}
    public void setUsuarioId(int usuarioId) {this.usuarioId = usuarioId;}

    @Override
    public String getNis() {return nis;}
    public void setNis(String nis) {this.nis = nis;}

    @Override
    public String getOt() {return ot;}
    public void setOt(String ot) {this.ot = ot;}

    @Override
    public Date getFecha() {return fecha;}
    public void setFecha(Date fecha) {this.fecha = fecha;}

    @Override
    public String getCoordLAT() {return coordLAT;}
    public void setCoordLAT(String coordLAT) {this.coordLAT = coordLAT;}

    @Override
    public String getCoordLONG() {return coordLONG;}
    public void setCoordLONG(String coordLONG) {this.coordLONG = coordLONG;}

    @Override
    public String getCoordX() {return coordX;}
    public void setCoordX(String coordX) {this.coordX = coordX;}

    @Override
    public String getCoordY() {return coordY;}
    public void setCoordY(String coordY) {this.coordY = coordY;}

    @Override
    public String getCedula() {return cedula;}
    public void setCedula(String cedula) {this.cedula = cedula;}

    @Override
    public String getObservacion() {return observacion;}
    public void setObservacion(String observacion) {this.observacion = observacion;}

    public WaypointEntity(){

    }

    public WaypointEntity(int id, int usuarioId, String nis, String ot, Date fecha, String coordLAT, String coordLONG, String coordX, String coordY, String cedula, String observacion){
        this.id = id;
        this.usuarioId=usuarioId;
        this.nis = nis;
        this.ot = ot;
        this.fecha = fecha;
        this.coordLAT = coordLAT;
        this.coordLONG = coordLONG;
        this.coordX = coordX;
        this.coordY = coordY;
        this.cedula = cedula;
        this.observacion = observacion;
    }
}