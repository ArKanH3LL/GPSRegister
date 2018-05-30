package informatica.orion.gpsregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class nuevoUsuario extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditNombreView;
    private EditText mEditCedulaView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_usuario);
        mEditNombreView = findViewById(R.id.edit_nombre);
        mEditCedulaView = findViewById(R.id.edit_cedula);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditNombreView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String nombre = mEditNombreView.getText().toString();
                    String cedula = mEditCedulaView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, nombre)
                    replyIntent.putExtra(EXTRA_REPLY, cedula);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
