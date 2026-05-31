package Model.Category;
import Model.TransactionType;

public class Category {

    private int id;

    private String nama;

    private TransactionType jenis;

    public Category(
            int id,
            String nama,
            TransactionType jenis
    ) {

        this.id=id;
        this.nama=nama;
        this.jenis=jenis;

    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public TransactionType getJenis() {
        return jenis;
    }
}