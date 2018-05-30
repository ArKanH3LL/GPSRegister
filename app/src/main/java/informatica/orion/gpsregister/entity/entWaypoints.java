package informatica.orion.gpsregister.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "entWaypoints", foreignKeys =@ForeignKey(entity = entUsuarios.class,parentColumns = "uid",childColumns = "cedula"))
public class entWaypoints {

    @PrimaryKey(autoGenerate = true)
    public int idwp;

    @ColumnInfo(name = "nis")
    public String nis;
    public void setNis(String nis) {this.nis = nis;}
    public String getNis() {return nis;}

    @ColumnInfo(name = "ot")
    public String ot;
    public String getOt() {return ot;}
    public void setOt(String ot) {this.ot = ot;}

    @ColumnInfo(name = "fecha")
    public String fecha;
    public String getFecha() {return fecha;}
    public void setFecha(String fecha) {this.fecha = fecha;}

    @ColumnInfo(name = "coordlat")
    public String coordlat;
    public String getCoordlat() {return coordlat;}
    public void setCoordlat(String coordlat) {this.coordlat = coordlat;}

    @ColumnInfo(name = "coordlong")
    public String coordlong;
    public String getCoordlong() {return coordlong;}
    public void setCoordlong(String coordlong) {this.coordlong = coordlong;}

    @ColumnInfo(name = "coordx")
    public String coordx;
    public String getCoordx() {return coordx;}
    public void setCoordx(String coordx) {this.coordx = coordx;}

    @ColumnInfo(name = "coordy")
    public String coordy;
    public String getCoordy() {return coordy;}
    public void setCoordy(String coordy) {this.coordy = coordy;}

    @ColumnInfo(name="cedula")
    public String cedula;
}