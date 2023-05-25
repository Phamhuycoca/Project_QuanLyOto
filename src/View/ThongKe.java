/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.ConnectDB;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.MessageFormat;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phamk
 */
public class ThongKe extends javax.swing.JPanel {
    ConnectDB db=new ConnectDB();
    Connection conn=db.getConnection();
    PreparedStatement pst =null;
    ResultSet rs =null;
    DefaultTableModel model=null;
    /**
     * Creates new form ThongKe
     */
    public ThongKe() {
        initComponents();
        loadtable2();
        txtTongSoXeDaBan.setText(TongXe());
        txtTongDoanhThu.setText(Tong());
        txtTongSoNhanVien.setText(Tongnv());
    }
    String tsnv=null;
    public String Tongnv(){
         String Get="select COUNT(MaNV) as_tong FROM NhanVien ";
        try{
        pst=conn.prepareStatement(Get);
        rs=pst.executeQuery();
        if(rs.next()){
            tsnv=rs.getString("as_tong");
        }
        }catch(Exception ex){
            
        }
        return tsnv;
    }
  String tt=null;
    public String TongXe(){
        String Get="select COUNT(*) from HoaDon ";
        try{
        pst=conn.prepareStatement(Get);
        rs=pst.executeQuery();
        if(rs.next()){
            tt=rs.getString("");
        }
        }catch(Exception ex){
            
        }
        return tt;
    }
      String tong=null;
    public String Tong(){
        String Get="Select SUM(ThanhTien) AS tong_gia_tien FROM CTHD ";
        try{
        pst=conn.prepareStatement(Get);
        rs=pst.executeQuery();
        if(rs.next()){
            tong=rs.getString("tong_gia_tien");
        }
        }catch(Exception ex){
            
        }
        return tong;
    }
     public void loadtable2(){
         try{
        String get="Select MaHD,TenKH,DiaChi,Sdt FROM HoaDon";
        pst=conn.prepareStatement(get);
        rs=pst.executeQuery();
        ResultSetMetaData stData=rs.getMetaData();
        int kq=stData.getColumnCount();
        DefaultTableModel RecordTable= (DefaultTableModel)TableHoaDon.getModel();
        RecordTable.setRowCount(0);
        while(rs.next()){
            Vector columnData=new Vector();
            for(int i=0;i<=kq;i++){
                columnData.add(rs.getString(1));
                columnData.add(rs.getString(2));
                columnData.add(rs.getString(3));
                columnData.add(rs.getString(4));

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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtTongDoanhThu = new javax.swing.JTextField();
        txtTongSoNhanVien = new javax.swing.JTextField();
        txtTongSoXeDaBan = new javax.swing.JTextField();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TableHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TableHoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Tổng doanh thu");

        jLabel2.setText("Số nhân viên");

        jLabel3.setText("Số xe đã bán ");

        jButton1.setText("In danh sách thống kê");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTongDoanhThu)
                            .addComponent(txtTongSoNhanVien)
                            .addComponent(txtTongSoXeDaBan, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jButton1)))
                .addGap(0, 40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTongSoNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTongSoXeDaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(126, 126, 126))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
          int selectedRow = TableHoaDon.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                MessageFormat header = new MessageFormat("Danh sách hóa đơn đã bán");
                MessageFormat footer = new MessageFormat("- {0} -");
                TableHoaDon.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            } catch (PrinterException ex) {
                System.err.format("Cannot print %s%n", ex.getMessage());
            }
    }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableHoaDon;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtTongDoanhThu;
    private javax.swing.JTextField txtTongSoNhanVien;
    private javax.swing.JTextField txtTongSoXeDaBan;
    // End of variables declaration//GEN-END:variables
}
