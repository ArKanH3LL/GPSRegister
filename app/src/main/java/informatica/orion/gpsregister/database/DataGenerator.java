package informatica.orion.gpsregister.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import informatica.orion.gpsregister.database.entity.UsuarioEntity;
import informatica.orion.gpsregister.database.entity.WaypointEntity;
import informatica.orion.gpsregister.model.Usuario;

public class DataGenerator {

    private static final String[] FIRST = new String[]{
            "Special edition", "New", "Cheap", "Quality", "Used"};
    private static final String[] SECOND = new String[]{
            "Three-headed Monkey", "Rubber Chicken", "Pint of Grog", "Monocle"};
    private static final String[] DESCRIPTION = new String[]{
            "is finally here", "is recommended by Stan S. Stanman",
            "is the best sold product on Mêlée Island", "is \uD83D\uDCAF", "is ❤️", "is fine"};
    private static final String[] COMMENTS = new String[]{
            "Comment 1", "Comment 2", "Comment 3", "Comment 4", "Comment 5", "Comment 6"};

    public static List<UsuarioEntity> generateUsuarios() {
        List<UsuarioEntity> usuarios = new ArrayList<>(FIRST.length * SECOND.length);
        Random rnd = new Random();
        for (int i = 0; i < FIRST.length; i++) {
            for (int j = 0; j < SECOND.length; j++) {
                UsuarioEntity usuario = new UsuarioEntity();
                usuario.setNombre(FIRST[i] + " " + SECOND[j]);
                usuario.setUnidad(usuario.getNombre() + " " + DESCRIPTION[j]);
                usuario.setEmpresa(usuario.getNombre() + " " + DESCRIPTION[j]);
                usuario.setCedula(String.valueOf(rnd.nextInt(240)));
                usuario.setId(FIRST.length * i + j + 1);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    public static List<WaypointEntity> generateWaypointsForUsuarios(
            final List<UsuarioEntity> usuarios) {
        List<WaypointEntity> waypoints = new ArrayList<>();
        Random rnd = new Random();

        for (Usuario usuario : usuarios) {
            int waypointsNumber = rnd.nextInt(5) + 1;
            for (int i = 0; i < waypointsNumber; i++) {
                WaypointEntity waypoint = new WaypointEntity();
                waypoint.setUsuarioId(usuario.getId());
                waypoint.setCedula(COMMENTS[i] + " for " + usuario.getNombre());
                waypoint.setFecha(new Date(System.currentTimeMillis()
                        - TimeUnit.DAYS.toMillis(waypointsNumber - i) + TimeUnit.HOURS.toMillis(i)));
                waypoints.add(waypoint);
            }
        }

        return waypoints;
    }

}
