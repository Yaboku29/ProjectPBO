package View;

import Controller.CategoryController;
import Model.Category.Category;
import Model.Category.CategoryDAO;
import Model.TransactionType;
import Model.User.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CategoryView extends JFrame {
    
    private User user;

    public CategoryView(User user) {

        this.user = user;

        setTitle("Category");
        setSize(700, 450);
        setLocationRelativeTo(null);

        JPanel panel =
                new JPanel(new BorderLayout());

        JLabel title =
                new JLabel(
                        "Category Management",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        DefaultTableModel model =
                new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Jenis");

        CategoryDAO categoryDAO =
                new CategoryDAO();

        ArrayList<Category> categories =
                categoryDAO.getAllCategory();

        for (Category category : categories) {

            model.addRow(
                    new Object[]{
                            category.getId(),
                            category.getNama(),
                            category.getJenis()
                    }
            );

        }

        JTable table =
                new JTable(model);

        JButton btnTambah =
                new JButton("Tambah Category");

        btnTambah.addActionListener(e -> {

            try {

                String nama =
                        JOptionPane.showInputDialog(
                                this,
                                "Nama Category"
                        );

                if (nama == null || nama.isBlank()) {
                    return;
                }

                TransactionType jenis =
                        (TransactionType)
                                JOptionPane.showInputDialog(
                                        this,
                                        "Pilih Jenis",
                                        "Jenis Category",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        TransactionType.values(),
                                        TransactionType.PEMASUKAN
                                );

                if (jenis == null) {
                    return;
                }

                Category category =
                        new Category(
                                0,
                                nama,
                                jenis
                        );

                boolean berhasil =
                        categoryDAO.createCategory(
                                category
                        );

                if (berhasil) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Category berhasil ditambahkan"
                    );

                    dispose();

                    new CategoryView(user)
                            .setVisible(true);

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Gagal menambahkan category"
                    );

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Input tidak valid"
                );

            }

        });

        JButton btnEdit =
                new JButton("Edit Category");

        btnEdit.addActionListener(e -> {

            int row =
                    table.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pilih category terlebih dahulu"
                );

                return;
            }

            try {

                int id =
                        Integer.parseInt(
                                table.getValueAt(row, 0)
                                        .toString()
                        );

                String namaLama =
                        table.getValueAt(row, 1)
                                .toString();

                String namaBaru =
                        JOptionPane.showInputDialog(
                                this,
                                "Nama Baru",
                                namaLama
                        );

                if (namaBaru == null ||
                        namaBaru.isBlank()) {
                    return;
                }

                TransactionType jenisLama =
                        TransactionType.valueOf(
                                table.getValueAt(row, 2)
                                        .toString()
                        );

                TransactionType jenisBaru =
                        (TransactionType)
                                JOptionPane.showInputDialog(
                                        this,
                                        "Pilih Jenis",
                                        "Edit Category",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        TransactionType.values(),
                                        jenisLama
                                );

                if (jenisBaru == null) {
                    return;
                }

                Category category =
                        new Category(
                                id,
                                namaBaru,
                                jenisBaru
                        );

                if (
                        categoryDAO.updateCategory(
                                category
                        )
                ) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Category berhasil diupdate"
                    );

                    dispose();

                    new CategoryView(user)
                            .setVisible(true);

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Gagal update category"
                );

            }

        });

        JButton btnHapus =
                new JButton("Hapus Category");

        btnHapus.addActionListener(e -> {

            int row =
                    table.getSelectedRow();

            if (row == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pilih category terlebih dahulu"
                );

                return;
            }

            int id =
                    Integer.parseInt(
                            table.getValueAt(row, 0)
                                    .toString()
                    );

            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Hapus category ini?",
                            "Konfirmasi",
                            JOptionPane.YES_NO_OPTION
                    );

            if (confirm ==
                    JOptionPane.YES_OPTION) {

                if (
                        categoryDAO.deleteCategory(id)
                ) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Category berhasil dihapus"
                    );

                    dispose();

                    new CategoryView(user)
                            .setVisible(true);

                }

            }

        });
        JButton btnKembali = new JButton("Kembali");
        
        
        btnKembali.addActionListener(e -> {

        new DashboardView(user)
                .setVisible(true);

        dispose();

        });
        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(btnTambah);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnHapus);
        buttonPanel.add(btnKembali);
        
        panel.add(
                title,
                BorderLayout.NORTH
        );

        panel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        panel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(panel);
    }
}