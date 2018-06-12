package informatica.orion.gpsregister.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import informatica.orion.gpsregister.model.Usuario;

@Entity(tableName ="usuarios")
public class UsuarioEntity implements Usuario {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;
    private String cedula;
    private String unidad;
    private String empresa;

    @Override
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Override
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}


    @Override
    public String getCedula() {return cedula;}
    public void setCedula(String cedula) {this.cedula = cedula;}


    @Override
    public String getUnidad() {return unidad;}
    public void setUnidad(String unidad) {this.unidad = unidad;}

    @Override
    public String getEmpresa() {return empresa;}
    public void setEmpresa(String empresa) {this.empresa = empresa;}

    public UsuarioEntity(){
    }

    public UsuarioEntity(int id, String nombre, String cedula, String unidad, String empresa){
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.unidad = unidad;
        this.empresa = empresa;
    }

    public UsuarioEntity(Usuario usuario){
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.cedula = usuario.getCedula();
        this.unidad = usuario.getUnidad();
        this.empresa = usuario.getEmpresa();
    }
}
