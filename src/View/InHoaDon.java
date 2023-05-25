/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.ConnectDB;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.MessageFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phamk
 */
public class InHoaDon extends javax.swing.JFrame {
    ConnectDB db=new ConnectDB();
    Connection conn=db.getConnection();
    PreparedStatement pst =null;
    ResultSet rs =null;
    DefaultTableModel model=null;
    /**
     * Creates new form InHoaDon
     */
    private String mahd=null;
    public InHoaDon(String id) {
        initComponents();
           setTitle("In hóa đơn");
        setLocationRelativeTo(null);
        this.mahd=id;
        JOptionPane.showMessageDialog(this, mahd);
          try{
        String get="SELECT        dbo.HoaDon.MaHD, dbo.HoaDon.TenKH, dbo.HoaDon.DiaChi, dbo.HoaDon.Sdt, dbo.Xe.TenXe, dbo.HangSanXuat.TenHSX, dbo.Xe.Gia, dbo.CTHD.ThanhTien\n" +
"FROM            dbo.HoaDon INNER JOIN\n" +
"                         dbo.CTHD ON dbo.HoaDon.MaHD = dbo.CTHD.MaHD INNER JOIN\n" +
"                         dbo.Xe ON dbo.CTHD.MaXe = dbo.Xe.MaXe INNER JOIN\n" +
"                         dbo.HangSanXuat ON dbo.Xe.MaHSX = dbo.HangSanXuat.MaHSX where HoaDon.MaHD=?";
        pst=conn.prepareStatement(get);
        pst.setString(1,mahd);
        rs=pst.executeQuery();
        ResultSetMetaData stData=rs.getMetaData();
        int kq=stData.getColumnCount();
        DefaultTableModel RecordTable= (DefaultTableModel)Table.getModel();
        RecordTable.setRowCount(0);
        while(rs.next()){
            Vector columnData=new Vector();
            for(int i=0;i<=kq;i++){
                columnData.add(rs.getString(1));
                columnData.add(rs.getString(2));
                columnData.add(rs.getString(3));
                columnData.add(rs.getString(4));
                columnData.add(rs.getString(5));
                columnData.add(rs.getString(6));
                columnData.add(rs.getString(7));
                columnData.add(rs.getString(8));
            }
            RecordTable.addRow(columnData);
         }
         }catch(Exception ex){
             System.out.println(ex.toString());
         }
    }
    public InHoaDon(){
        
    }
public void Load(){
      try{
        String get="SELECT        dbo.HoaDon.MaHD, dbo.HoaDon.TenKH, dbo.HoaDon.DiaChi, dbo.HoaDon.Sdt, dbo.Xe.TenXe, dbo.HangSanXuat.TenHSX, dbo.Xe.Gia, dbo.CTHD.ThanhTien\n" +
"FROM            dbo.HoaDon INNER JOIN\n" +
"                         dbo.CTHD ON dbo.HoaDon.MaHD = dbo.CTHD.MaHD INNER JOIN\n" +
"                         dbo.Xe ON dbo.CTHD.MaXe = dbo.Xe.MaXe INNER JOIN\n" +
"                         dbo.HangSanXuat ON dbo.Xe.MaHSX = dbo.HangSanXuat.MaHSX where HoaDon.MaHD=?";
        pst=conn.prepareStatement(get);
        pst.setString(1,mahd);
        rs=pst.executeQuery();
        ResultSetMetaData stData=rs.getMetaData();
        int kq=stData.getColumnCount();
        DefaultTableModel RecordTable= (DefaultTableModel)Table.getModel();
        RecordTable.setRowCount(0);
        while(rs.next()){
            Vector columnData=new Vector();
            for(int i=0;i<=kq;i++){
                columnData.add(rs.getString(1));
                columnData.add(rs.getString(2));
                columnData.add(rs.getString(3));
                columnData.add(rs.getString(4));
                columnData.add(rs.getString(5));
                columnData.add(rs.getString(6));
                columnData.add(rs.getString(7));
                columnData.add(rs.getString(8));
            }
            RecordTable.addRow(columnData);
         }
         }catch(Exception ex){
             System.out.println(ex.toString());
         }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        btnInHoaDon = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Khách hàng", "Địa chỉ", "Số điện thoại", "Xe", "Hãng sản xuất", "Giá", "Đơn giá"
            }
        ));
        jScrollPane1.setViewportView(Table);

        btnInHoaDon.setText("In hóa đơn");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(387, 387, 387)
                .addComponent(btnInHoaDon)
                .addContainerGap(396, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btnInHoaDon)
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        // TODO add your handling code here:
        int selectedRow = Table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                MessageFormat header = new MessageFormat("Danh sách hóa đơn");
                MessageFormat footer = new MessageFormat("- {0} -");
                Table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            } catch (PrinterException ex) {
                System.err.format("Cannot print %s%n", ex.getMessage());
            }
    }
        this.dispose();
    }//GEN-LAST:event_btnInHoaDonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InHoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
