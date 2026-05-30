package View;

import java.util.Scanner;

import Controller.AuthController;

import Helper.Session;

import Model.User.User;

public class LoginMenu {

    private Scanner input;

    private AuthController auth;

    public LoginMenu(){

        input =
                new Scanner(
                        System.in
                );

        auth =
                new AuthController();

    }

    public void signup(){

        System.out.println(
                "\n=== SIGN UP ==="
        );

        System.out.print(
                "Username: "
        );

        String username =
                input.nextLine();

        System.out.print(
                "Nama: "
        );

        String nama =
                input.nextLine();

        System.out.print(
                "Email: "
        );

        String email =
                input.nextLine();

        System.out.print(
                "Password: "
        );

        String password =
                input.nextLine();

        boolean berhasil =
                auth.signup(

                        username,

                        nama,

                        email,

                        password

                );

        if(berhasil){

            System.out.println(
                    "Signup berhasil"
            );

        }

        else{

            System.out.println(
                    "Signup gagal"
            );

        }

    }


}