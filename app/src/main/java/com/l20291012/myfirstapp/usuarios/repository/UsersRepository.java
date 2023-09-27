package com.l20291012.myfirstapp.usuarios.repository;

import com.l20291012.myfirstapp.usuarios.model.Usuario;

import java.util.HashMap;

public class UsersRepository {
    private static UsersRepository _instance = null;
    private HashMap< String, HashMap < String, Usuario> > registeredUsers;

    private UsersRepository(){
        registeredUsers = new HashMap<String, HashMap < String, Usuario> >();
        Usuario user1 = new Usuario("Didian", "1234", "Vivian", 27, 'M', "vivian@gmail.com");
        Usuario user2 = new Usuario("Dani", "1234", "Daniela", 22, 'M', "daniela@gmail.com");
        Usuario user3 = new Usuario("Aurelioo", "1234", "Aurelio", 22, 'H', "aurelio@gmail.com");
        Usuario user4 = new Usuario("Clau", "1234", "Claudia", 22, 'M', "clau@gmail.com");


        registeredUsers.put(user1.getUsuario(), new HashMap< String, Usuario>());
        registeredUsers.get(user1.getUsuario()).put(user1.getPass(), user1);

        registeredUsers.put(user2.getUsuario(), new HashMap< String, Usuario>());
        registeredUsers.get(user2.getUsuario()).put(user2.getPass(), user2);

        registeredUsers.put(user3.getUsuario(), new HashMap< String, Usuario>());
        registeredUsers.get(user3.getUsuario()).put(user3.getPass(), user3);
    }

    public static UsersRepository getInstance(){
        if(_instance == null)
            _instance = new UsersRepository();
        return _instance;
    }
    public HashMap<String, HashMap<String, Usuario>> getRegisteredUsers() {
        return registeredUsers;
    }
}
