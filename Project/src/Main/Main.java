package Main;

import View.LoginView;

public class Main {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                new LoginView().setVisible(true);
            }
        });
    }
}
