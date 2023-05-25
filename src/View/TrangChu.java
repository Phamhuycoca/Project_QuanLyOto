/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.ConnectDB;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phamk
 */
public class TrangChu extends javax.swing.JFrame {
    ConnectDB db=new ConnectDB();
    Connection conn=db.getConnection();
    PreparedStatement pst =null;
    ResultSet rs =null;
    DefaultTableModel modelXE=null;
    DefaultTableModel modelHD=null;

        byte[] photo=null;
String fileName=null;
private ImageIcon format=null;
    /**
     * Creates new form TrangChu
     */
private String manv=null;
    public TrangChu(String id) {
        initComponents();
        this.manv=id;
         txtNhanVien.setText(GetName());
          setTitle("Trang chủ");
          LoadTableXe();
        setLocationRelativeTo(null);
        txtNhanVien.setEnabled(false);
    }
    public TrangChu(){
        
    }
     //Load Danh sách dữ liệu của xe
    public void LoadTableXe(){
         try{
        String get="SELECT dbo.Xe.MaXe, dbo.Xe.TenXe, dbo.Xe.HinhAnh, dbo.HangSanXuat.TenHSX, dbo.Xe.Gia\n" +
        "FROM dbo.Xe INNER JOIN dbo.HangSanXuat ON dbo.Xe.MaHSX = dbo.HangSanXuat.MaHSX";
        pst=conn.prepareStatement(get);
        rs=pst.executeQuery();
        ResultSetMetaData stData=rs.getMetaData();
        int kq=stData.getColumnCount();
        DefaultTableModel RecordTable= (DefaultTableModel)TableXe.getModel();
        RecordTable.setRowCount(0);
        while(rs.next()){
            Vector columnData=new Vector();
            for(int i=0;i<=kq;i++){
                columnData.add(rs.getString(1));
                columnData.add(rs.getString(2));
                byte[] image=rs.getBytes(3);
                columnData.add(image);
                columnData.add(rs.getString(4));
                columnData.add(rs.getString(5));
            }
            RecordTable.addRow(columnData);
         }
         }catch(Exception ex){
             System.out.println(ex.toString());
         }
    }
    
    //CheckForm
    private boolean CheckForm(){
        if(txtKhachHang.getText().isEmpty()||txtDiaChi.getText().isEmpty()||txtSdt.getText().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
     //Lấy tên nhân viên
     String hoten=null;
        public String GetName(){
         String Get="select TenNV from NhanVien where MaNV=?";
        try{
        pst=conn.prepareStatement(Get);
        pst.setString(1, manv);
        rs=pst.executeQuery();
        if(rs.next()){
            hoten=rs.getString("TenNV");
        }
        }catch(Exception ex){
            
        }
        return hoten;
    }
        //Load danh sách hóa đơn theo tên khách hàng
         public void LoadTableHoaDon(){
         try{
        String get="SELECT        MaHD, TenKH, DiaChi, Sdt, ThanhTien\n" +
"FROM            dbo.HoaDon WHERE TenKH=?";
        pst=conn.prepareStatement(get);
        pst.setString(1, txtKhachHang.getText());
        rs=pst.executeQuery();
        ResultSetMetaData stData=rs.getMetaData();
        int kq=stData.getColumnCount();
        DefaultTableModel RecordTable= (DefaultTableModel)TableHD.getModel();
        RecordTable.setRowCount(0);
        while(rs.next()){
            Vector columnData=new Vector();
            for(int i=0;i<=kq;i++){
                columnData.add(rs.getString(1));
                columnData.add(rs.getString(2));
                columnData.add(rs.getString(3));
                columnData.add(rs.getString(4));
                columnData.add(rs.getString(5));

            }
            RecordTable.addRow(columnData);
         }
         }catch(Exception ex){
             System.out.println(ex.toString());
         }
    }
         //Load table CTHD
     public void LoadTableCTHD(){
          modelHD=(DefaultTableModel)TableHD.getModel();
           int chd=TableHD.getSelectedRow();
           String mahd=modelHD.getValueAt(chd, 0).toString();
         try{
        String get="SELECT        MaCTHD, MaHD, MaXe, ThanhTien\n" +
"FROM            dbo.CTHD WHERE MaHD=?";
        pst=conn.prepareStatement(get);
        pst.setString(1, mahd);
        rs=pst.executeQuery();
        ResultSetMetaData stData=rs.getMetaData();
        int kq=stData.getColumnCount();
        DefaultTableModel RecordTable= (DefaultTableModel)TableCTDH.getModel();
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
      //Lấy tổng tền hóa đơn
        String tong=null;
        public String GetTongTien(){
            modelHD=(DefaultTableModel)TableHD.getModel();
           int chd=TableHD.getSelectedRow();
           String mahd=modelHD.getValueAt(chd, 0).toString();
            String Get="Select SUM(ThanhTien) AS tong_gia_tien FROM CTHD WHERE MaHD =?";
        try{
        pst=conn.prepareStatement(Get);
        pst.setString(1, mahd);
        rs=pst.executeQuery();
        if(rs.next()){
            tong=rs.getString("tong_gia_tien");
        }
        }catch(Exception ex){
            
        }
        return tong;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtKhachHang = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        txtNhanVien = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableHD = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableCTDH = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableXe = new javax.swing.JTable();
        txtHinhAnh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Tên khách hàng");

        jLabel3.setText("Địa chỉ");

        jLabel4.setText("Số điện thoại");

        jLabel5.setText("Nhân viên");

        jLabel6.setText("Tổng tiền");

        jButton1.setText("Tạo hóa đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Thêm vào chi tiết");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cập nhật");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Lưu hóa đơn");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("In hóa đơn ");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtKhachHang)
                    .addComponent(txtDiaChi)
                    .addComponent(txtSdt)
                    .addComponent(txtNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(50, 50, 50)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton5)
                            .addComponent(jButton3))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(jButton2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(33, 33, 33))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TableHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Khách hàng", "Địa chỉ", "Số diện thoại"
            }
        ));
        TableHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableHDMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableHD);

        TableCTDH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã chi tiết hóa đơn", "Mã hóa đơn", "Mã xe", "Giá xe"
            }
        ));
        jScrollPane3.setViewportView(TableCTDH);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TableXe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã xe", "Tên xe", "Hình ảnh", "Hãng sản xuất", "Giá"
            }
        ));
        TableXe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableXeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableXe);

        txtHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHinhAnh.setText("Hình ảnh");
        txtHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(txtHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(txtHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TableXeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableXeMouseClicked
        // TODO add your handling code here:
        modelXE=(DefaultTableModel)TableXe.getModel();
        int c=TableXe.getSelectedRow();
        byte[] image=(byte[]) modelXE.getValueAt(c, 2);
        ImageIcon ii = new ImageIcon(image);
        Image images = ii.getImage().getScaledInstance(txtHinhAnh.getWidth(), txtHinhAnh.getHeight(), Image.SCALE_SMOOTH);
        format=new ImageIcon(images);
        txtHinhAnh.setIcon(format);
        //txtTenXe.setText(model.getValueAt(c, 1).toString());
        //txtTong.setText(model.getValueAt(c, 4).toString());
        //maxe=model.getValueAt(c, 0).toString();
        //giaxe=model.getValueAt(c, 4).toString();
    }//GEN-LAST:event_TableXeMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(CheckForm()){
            try{
                String set="INSERT INTO HoaDon(TenKH,MaNV,DiaChi,Sdt) VALUES(?,?,?,?)";
                pst=conn.prepareStatement(set);
                pst.setString(1,txtKhachHang.getText());
                pst.setString(2,manv);
                pst.setString(3,txtDiaChi.getText());
                pst.setString(4,txtSdt.getText());
                if(pst.executeUpdate()==1){
                    JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
                    LoadTableHoaDon();
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e);
                System.out.println(e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(this, "Nhập đầy đủ thông tin đơn hàng");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
       try{
            modelXE=(DefaultTableModel)TableXe.getModel();
        int c=TableXe.getSelectedRow();
        String maxe=modelXE.getValueAt(c, 0).toString();
        String giaxe=modelXE.getValueAt(c, 4).toString();
           System.out.println(maxe+" \t"+giaxe);
        JOptionPane.showMessageDialog(this, maxe);
       try{
           modelHD=(DefaultTableModel)TableHD.getModel();
           int chd=TableHD.getSelectedRow();
           String mahd=modelHD.getValueAt(chd, 0).toString();
           System.out.println(mahd);
            String set="INSERT INTO CTHD VALUES(?,?,?)";
            pst=conn.prepareStatement(set);
            pst.setString(1,mahd);
            pst.setString(2, maxe);
            pst.setString(3, giaxe);
            if(pst.executeUpdate()==1){
                    JOptionPane.showMessageDialog(this, "Thêm thành công chi tiết hóa đơn");
                    LoadTableCTHD();
                }
       }catch(Exception ex){
           JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để thêm");
       }
       
       }catch(Exception e){
          JOptionPane.showMessageDialog(this, "Vui lòng chọn xe để tạo hóa đơn");
       }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        System.out.println("Tổng tiền"+GetTongTien());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void TableHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableHDMouseClicked
        // TODO add your handling code here:
          modelHD=(DefaultTableModel)TableHD.getModel();
           int chd=TableHD.getSelectedRow();
           String mahd=modelHD.getValueAt(chd, 0).toString();
           System.out.println("Mã hóa đơn"+mahd);
         try{
        String get="SELECT        MaCTHD, MaHD, MaXe, ThanhTien\n" +
"FROM            dbo.CTHD WHERE MaHD=?";
        pst=conn.prepareStatement(get);
        pst.setString(1, mahd);
        rs=pst.executeQuery();
        ResultSetMetaData stData=rs.getMetaData();
        int kq=stData.getColumnCount();
        DefaultTableModel RecordTable= (DefaultTableModel)TableCTDH.getModel();
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
                System.out.println("Tổng tiền"+GetTongTien());
                txtTongTien.setText(GetTongTien());

    }//GEN-LAST:event_TableHDMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if(txtTongTien.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn lại");
        }else{
              modelHD=(DefaultTableModel)TableHD.getModel();
           int chd=TableHD.getSelectedRow();
           String mahd=modelHD.getValueAt(chd, 0).toString();
           System.out.println("Mã hóa đơn"+mahd);
           try{
               String set="UPDATE HoaDon SET ThanhTien=? WHERE MaHD=?";
               pst=conn.prepareStatement(set);
                pst.setString(2,mahd);
            pst.setString(1, txtTongTien.getText());
            if(pst.executeUpdate()==1){
                    JOptionPane.showMessageDialog(this, "Lưu hóa đơn thành công");
                }

           }catch(Exception ex){
               
           }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         modelHD=(DefaultTableModel)TableHD.getModel();
        int c=TableHD.getSelectedRow();
        String mahd=modelHD.getValueAt(c, 0).toString();
        int check_log_out = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn in hóa đơn ?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (check_log_out == JOptionPane.YES_OPTION) {
            String printData = "\t\t\tSố hóa đơn: "+mahd+"\n\n Khách hàng: " + txtKhachHang.getText() + "\n\n "+
        "Nhân viên phuc vụ: "+ txtNhanVien.getText() +"\n\n "+ " địa chỉ nhận xe "+ txtDiaChi.getText()+"\n\n "+// Thay đổi tại đây
        "Số điện thoại khách hàng : "+ txtSdt.getText()+"\n\n "+
        "Tổng hóa đơn :" + txtTongTien.getText()+ "\n\n "+
        "Giám đốc: Trần Quý Nam";
               PrinterJob job = PrinterJob.getPrinterJob();
job.setPrintable(new Printable() {
   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
      if (pageIndex > 0) {
         return NO_SUCH_PAGE;
      }
      Graphics2D g2d = (Graphics2D) graphics;
      g2d.setFont(new Font("Serif", Font.PLAIN, 12)); // Set font and size
      String[] lines = printData.split("\n"); // Split text into lines
      int y = 100; // Starting y position
      for (String line : lines) {
         g2d.drawString(line, 100, y); // Draw each line at x=100, y=100 and increment y position for each line
         y += g2d.getFontMetrics().getHeight(); // Increment y position by the height of the font
      }
      return PAGE_EXISTS;
   }
});
         boolean doPrint = job.printDialog();
         if (doPrint) {
            try {
               job.print();
            } catch (PrinterException ex) {
               System.err.println(ex);
            }
         }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableCTDH;
    private javax.swing.JTable TableHD;
    private javax.swing.JTable TableXe;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JLabel txtHinhAnh;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtNhanVien;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
