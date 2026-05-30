package Model.User;

public class User {

    private int id;

    private String username;

    private String nama;

    private String email;

    private String password;

    public User(){}

    public User(

            int id,

            String username,

            String nama,

            String email,

            String password

    ){

        this.id=id;

        this.username=username;

        this.nama=nama;

        this.email=email;

        this.password=password;

    }

    // =========================
    // GETTER
    // =========================

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // =========================
    // SETTER
    // =========================

    public void setId(
            int id
    ){
        this.id=id;
    }

    public void setUsername(
            String username
    ){
        this.username=username;
    }

    public void setNama(
            String nama
    ){
        this.nama=nama;
    }

    public void setEmail(
            String email
    ){
        this.email=email;
    }

    public void setPassword(
            String password
    ){
        this.password=password;
    }

}