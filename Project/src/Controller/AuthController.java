package Controller;

import Model.User.*;

public class AuthController {

    private UserDAO userDAO;

    public AuthController(){

        userDAO =
                new UserDAO();

    }

    public boolean signup(

        String username,

        String nama,

        String email,

        String password

    ){

        User user =
                new User(

                        0,

                        username,

                        nama,

                        email,

                        password

                );

        return userDAO
                .createUser(user);

    }

    public User login(

            String username,
            String password

    ){

        return userDAO
                .login(
                        username,
                        password
                );

    }

}