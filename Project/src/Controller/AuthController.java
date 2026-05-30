package Controller;

import Model.User.*;

public class AuthController {

    private UserDAO userDAO;

    public AuthController(){

        userDAO =
                new UserDAO();

    }

    public boolean register(User user){
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