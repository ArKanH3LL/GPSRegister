package informatica.orion.gpsregister.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import informatica.orion.gpsregister.R;
import informatica.orion.gpsregister.model.Usuario;

public class UsuariosShow extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuario_show);

        // Add product list fragment if this is first creation
        if (savedInstanceState == null) {
            UsuarioListFragment fragment = new UsuarioListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, UsuarioListFragment.TAG).commit();
        }
    }

    /** Shows the product detail fragment */
    public void show(Usuario usuario) {

        UsuarioFragment usuarioFragment = UsuarioFragment.forUsuario(usuario.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("usuario")
                .replace(R.id.fragment_container,
                        usuarioFragment, null).commit();
    }
}
