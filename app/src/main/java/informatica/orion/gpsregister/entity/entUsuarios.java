package informatica.orion.gpsregister.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(indices = {@Index("cedula")})
public class entUsuarios {
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

    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.
}
