package com.l20291012.myfirstapp.usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

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


            vUser.setText(userInfo.getUsuario());
            vNombre.setText(userInfo.getNombre());
            vEdad.setText(String.valueOf(userInfo.getEdad()) + " Años");
            vEmail.setText(userInfo.getEmail());
            vGenero.setText(String.valueOf(userInfo.getGenero()));


            //userInfo = ur.getRegisteredUsers().get(user).get(pass);
        }
    }

}
