/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.User;

/**
 *
 * @author Admin
 */
public class User {
    private int id;
    private String username;
    private String nama;
    private String email;
    private String password;
    
    public User(){}
    public User(int id, String username, String nama,String email,String password){
        this.id=id;
        this.username=username;
        this.nama=nama;
        this.email=email;
        this.password=password;
    }
    
    public int getID(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getNama(){
        return nama;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
}
