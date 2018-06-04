package informatica.orion.gpsregister.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(indices = {@Index("cedula")})
public class entUsuarios {
    public int getUid() {return uid;}
    public void setUid(int uid) {this.uid = uid;}

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "cedula")
    public String cedula;
    public void setCedula(String cedula) {this.cedula = cedula;}
    public String getCedula() {return cedula;}

    @ColumnInfo(name = "nombre")
    public String nombre;
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getNombre() {return nombre;}

    @ColumnInfo(name = "unidad")
    public String unidad;
    public void setUnidad(String unidad) {this.unidad = unidad;}
    public String getUnidad() {return unidad;}

    @ColumnInfo(name = "empresa")
    public String empresa;
    public void setEmpresa(String empresa) {this.empresa = empresa;}
    public String getEmpresa() {return empresa;}

}
