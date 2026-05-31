package Controller;

import Model.User.User;
import Model.User.UserDAO;

public class UserController {

    private final UserDAO userDAO;

    public UserController() {
        userDAO = new UserDAO();
    }

    public boolean register(User user) {
        return userDAO.register(user);
    }

    public User login(String username, String password) {
        return userDAO.login(username, password);
    }
}