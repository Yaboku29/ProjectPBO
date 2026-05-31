package View;

import Model.User.User;
import Model.Wallet.Wallet;
import Model.Wallet.WalletDAO;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class WalletView extends JFrame {
    private User user;
    private JTable table;
    private DefaultTableModel model;
    
    public WalletView(User user){
        this.user = user;
        
        setTitle("Wallet Management");
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Wallet Management", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        
        //DefaultTableModel model =
                //new DefaultTableModel();
        
        //model.addColumn("ID");
        //model.addColumn("Nama Wallet");
        //model.addColumn("Saldo");
        
        model = new DefaultTableModel(
                new String[]{
                    "ID",
                    "Nama Wallet",
                    "Saldo"
                },0
        );
        
        //WalletDAO walletDAO =
                //new WalletDAO();
        
        //ArrayList<Wallet> wallets =
                //walletDAO.getByUserId(
                        //user.getId()
                //);
        
        //for (Wallet wallet : wallets) {
            
            //model.addRow(
                    //new Object[]{
                        //wallet.getId(),
                        //wallet.getNama(),
                        //wallet.getSaldo()
                    //}
            //);
        //}
        
        //JTable table =
                //new JTable(model);
        
        //JButton btnTambah = new JButton("Tambah Wallet");
        //panel.add(title, BorderLayout.NORTH);
        //panel.add(new JScrollPane(table), BorderLayout.CENTER);
        //panel.add(btnTambah, BorderLayout.SOUTH);
        
        table = new JTable(model);
        
        JButton btnTambah = new JButton("Tambah Wallet");
        JButton btnEdit = new JButton("Edit Wallet");
        JButton btnHapus = new JButton("Hapus Wallet");
        
        JPanel buttonPanel = new JPanel();
        
        buttonPanel.add(btnTambah);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnHapus);
        
        panel.add(title, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        
        loadWallet();
        
        btnTambah.addActionListener(e -> {
            String nama = JOptionPane.showInputDialog(
                    this,
                    "Masukkan Nama Wallet"
            );
            
            if(
                    nama == null
                    ||
                    nama.trim().isEmpty()
                    ){
                return;
            }
            
            Wallet wallet = new Wallet(0, user.getId(), nama, 0);
            
            WalletDAO dao = new WalletDAO();
            
            boolean berhasil = dao.createWallet(wallet);
            
            if(berhasil){
                JOptionPane.showMessageDialog(
                        this,
                        "Wallet berhasil ditambahkan");
                
                loadWallet();
                
            } else{
                JOptionPane.showMessageDialog(
                        this,
                        "Gagal menambahkan wallet");
                
            }
        });
        
        btnEdit.addActionListener(e -> {
            
            int row = table.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(
                        this,
                        "Pilih wallet terlebih dahulu"
                );
                return;
            }
            int id =
                    Integer.parseInt(
                            table.getValueAt(row,0).toString()
                    );

            String namaLama =
                    table.getValueAt(row,1).toString();

            String namaBaru =
                    JOptionPane.showInputDialog(
                            this,
                            "Nama Wallet Baru",
                            namaLama
                    );
            
            if(
                    namaBaru == null
                    ||
                    namaBaru.trim().isEmpty()
                    ){
                return;
            }
            
            Wallet wallet =
                    new Wallet(
                            id, user.getId(), namaBaru, Double.parseDouble(
                                    table.getValueAt(row,2).toString()
                            )
                    );
            
            WalletDAO dao =
                    new WalletDAO();
            
            boolean berhasil =
                    dao.updateWallet(wallet);
            
            if(berhasil){
                JOptionPane.showMessageDialog(
                        this,
                        "Wallet berhasil diupdate"
                );

                loadWallet();
            
            }else{
                
                JOptionPane.showMessageDialog(
                        this,
                        "Gagal update wallet"
                
                );
            }
        
        });
        
        btnHapus.addActionListener(e -> {
            int row = table.getSelectedRow();
            
            if(row == -1){
                
                JOptionPane.showMessageDialog(
                        this,
                        "Pilih wallet terlebih dahulu"
                );
                return;
            }
            
            int id =
                    Integer.parseInt(
                            table.getValueAt(row,0).toString()
                    );
            
            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Hapus wallet ini?",
                            "Konfirmasi",
                            JOptionPane.YES_NO_OPTION
                    );
            
            if(confirm == JOptionPane.YES_OPTION){
                
                WalletDAO dao =
                        new WalletDAO();
                
                boolean berhasil =
                        dao.deleteWallet(id);
                
                if(berhasil){
                    JOptionPane.showMessageDialog(
                            this,
                            "Wallet berhasil dihapus"
                    );

                    loadWallet();
                
                }else{
                    
                    JOptionPane.showMessageDialog(
                            this,
                            "Gagal menghapus wallet"
                    );

                }
            }
        
        });
       
    }
    
    private void loadWallet(){
        model.setRowCount(0);
        
        WalletDAO dao = new WalletDAO();
        
        ArrayList<Wallet> wallets = dao.getByUserId(
                user.getId()
        );
        
        for(Wallet wallet : wallets){
            model.addRow(
                    new Object[]{
                        wallet.getId(),
                        wallet.getNama(),
                        wallet.getSaldo()
                    }
            );
        }
    }
}
