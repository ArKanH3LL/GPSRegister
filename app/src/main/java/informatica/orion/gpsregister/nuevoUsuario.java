package informatica.orion.gpsregister;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import informatica.orion.gpsregister.entity.entUsuarios;

public class nuevoUsuario extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditNombreView;
    private EditText mEditCedulaView;
    private EditText mEditUnidadView;
    private EditText mEditEmpresaView;

    entUsuarios nuevouser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_usuario);
        mEditNombreView = findViewById(R.id.edt_nombre);
        mEditCedulaView = findViewById(R.id.edt_cedula);
        mEditUnidadView = findViewById(R.id.edt_unidad);
        mEditEmpresaView = findViewById(R.id.edt_empresa);

        final Button button = findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditNombreView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String nombre = mEditNombreView.getText().toString();
                    String cedula = mEditCedulaView.getText().toString();
                    String unidad = mEditUnidadView.getText().toString();
                    String empresa = mEditEmpresaView.getText().toString();
                    nuevouser = new entUsuarios();
                    nuevouser.setNombre(nombre);
                    nuevouser.setCedula(cedula);
                    nuevouser.setUnidad(unidad);
                    nuevouser.setEmpresa(empresa);

                    replyIntent.putExtra(EXTRA_REPLY, new String[]{nombre, cedula, unidad, empresa});
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
