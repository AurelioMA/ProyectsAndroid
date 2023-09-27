package com.l20291012.myfirstapp.usuarios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.l20291012.myfirstapp.R;
import com.l20291012.myfirstapp.usuarios.model.Usuario;
import com.l20291012.myfirstapp.usuarios.repository.UsersRepository;

import java.util.HashMap;

public class UserDashboardActivity extends AppCompatActivity {

    private UsersRepository ur;
    private Usuario userInfo;

    private TextView vUser;
    private TextView vNombre;
    private TextView vEdad;
    private TextView vGenero;
    private TextView vEmail;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        vUser = findViewById(R.id.vUser);
        vNombre = findViewById(R.id.vNombre);
        vEdad = findViewById(R.id.vEdad);
        vGenero = findViewById(R.id.vGenero);
        vEmail = findViewById(R.id.vEmail);

        ur = UsersRepository.getInstance();

        String user, pass;
        user = getIntent().getStringExtra("usuario");
        pass = getIntent().getStringExtra("pass");

        HashMap<String, HashMap<String,Usuario>> usuariosRegistrados = ur.getRegisteredUsers();
        if (usuariosRegistrados.containsKey(user)) {
            HashMap<String, Usuario> usuariosPorUsuario = usuariosRegistrados.get(user);
            userInfo = usuariosPorUsuario.get(pass);

            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            ShapeableImageView imgUser = findViewById((R.id.imgUser));
            if (userInfo.getGenero() == 'H') {
                imgUser.setImageResource(R.drawable.hombre);
            } else {
                imgUser.setImageResource(R.drawable.mujer);

            }
            vUser.setText(userInfo.getUsuario());
            vNombre.setText(userInfo.getNombre());
            vEdad.setText(String.valueOf(userInfo.getEdad()) + " Años");
            vEmail.setText(userInfo.getEmail());
            vGenero.setText(String.valueOf(userInfo.getGenero()));


            //userInfo = ur.getRegisteredUsers().get(user).get(pass);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.idDelete) {
            //mostrar alert dialog de estas seguro, si o no
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Estás seguro?")
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Acción a realizar si el usuario hace clic en "Sí"
                            dialog.dismiss(); // Cierra el diálogo
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Acción a realizar si el usuario hace clic en "No"
                            dialog.dismiss(); // Cierra el diálogo
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (item.getItemId() == R.id.idInfo){
            //informacion del usuario
            Toast.makeText(this,"Presionaste el boton mas informacion", Toast.LENGTH_SHORT).show();

        } else if (item.getItemId() == R.id.idSetting){
            //toast accediendo a la informacion
            Toast.makeText(this,"Accediendo a la informacion", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }
}
