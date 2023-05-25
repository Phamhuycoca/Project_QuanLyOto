/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.ConnectDB;
import java.awt.Component;
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
public class Home extends javax.swing.JFrame {
    ConnectDB db=new ConnectDB();
    Connection conn=db.getConnection();
    PreparedStatement pst =null;
    ResultSet rs =null;
    DefaultTableModel model=null;
        byte[] photo=null;
String fileName=null;
private ImageIcon format=null;
    /**
     * Creates new form Home
     */
    private String manv=null;
    public Home(String id) {
        initComponents();
        this.manv=id;
        setTitle("Trang chủ");
        setLocationRelativeTo(null);
        JOptionPane.showMessageDialog(this, manv);
        
        txtNhanVien.setText(GetName());
        txtNhanVien.setEnabled(false);
        txtMaHD.setEnabled(false);
        LoadTableXe();
        LoadTableHoaDon();
        LoadTableCTHD();
    }
    public Home(){
        
    }
    //Load table Xe
    public void LoadTableXe(){
         try{
        String get="SELECT        dbo.Xe.MaXe, dbo.Xe.TenXe, dbo.Xe.HinhAnh, dbo.HangSanXuat.TenHSX, dbo.Xe.Gia\n" +
"FROM            dbo.Xe INNER JOIN\n" +
"                         dbo.HangSanXuat ON dbo.Xe.MaHSX = dbo.HangSanXuat.MaHSX";
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
    //Load table Hóa đơn
    public void LoadTableHoaDon(){
         try{
        String get="SELECT        dbo.HoaDon.MaHD, dbo.HoaDon.TenKH, dbo.NhanVien.TenNV, dbo.HoaDon.DiaChi, dbo.HoaDon.Sdt, dbo.HoaDon.ThanhTien\n" +
"FROM            dbo.HoaDon INNER JOIN\n" +
"                         dbo.NhanVien ON dbo.HoaDon.MaNV = dbo.NhanVien.MaNV";
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
                columnData.add(rs.getString(5));
                columnData.add(rs.getString(6));

            }
            RecordTable.addRow(columnData);
         }
         }catch(Exception ex){
             System.out.println(ex.toString());
         }
    }
    //Load table CTHD
     public void LoadTableCTHD(){
         try{
        String get="SELECT        dbo.CTHD.MaCTHD, dbo.CTHD.MaHD, dbo.Xe.TenXe, dbo.CTHD.ThanhTien\n" +
"FROM            dbo.Xe INNER JOIN\n" +
"                         dbo.CTHD ON dbo.Xe.MaXe = dbo.CTHD.MaXe";
        pst=conn.prepareStatement(get);
        rs=pst.executeQuery();
        ResultSetMetaData stData=rs.getMetaData();
        int kq=stData.getColumnCount();
        DefaultTableModel RecordTable= (DefaultTableModel)TableCTHD.getModel();
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
    //Get tên nhân viên
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
        TableCTHD = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnCapNhat = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtKhachHang = new javax.swing.JTextField();
        txtNhanVien = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        txtTenXe = new javax.swing.JTextField();
        txtTong = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableHoaDon = new javax.swing.JTable();
        btnTaoHoaDon = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();
        btnKiemTra = new javax.swing.JButton();
        btnThemHoaDon = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableXe = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtHinhAnh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TableCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã chi tiết hóa đơn", "Mã hóa đơn", "Xe", "Đơn giá"
            }
        ));
        jScrollPane1.setViewportView(TableCTHD);

        jLabel1.setText("Chi tiết hóa đơn");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnCapNhat.setText("Cập nhật");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCapNhat)
                .addGap(104, 104, 104))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCapNhat)
                .addGap(30, 30, 30))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Khách hàng");

        jLabel4.setText("Nhân viên");

        jLabel5.setText("Địa chỉ");

        jLabel6.setText("Số điện thoại");

        jLabel7.setText("Tên xe");

        jLabel8.setText("Tổng");

        TableHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Khách hàng", "Nhân viên", "Địa chỉ", "Số điện thoại", "Tổng tiền"
            }
        ));
        TableHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TableHoaDon);

        btnTaoHoaDon.setText("Tạo Hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnInHoaDon.setText("In hóa đơn");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        btnKiemTra.setText("Kiểm tra");
        btnKiemTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKiemTraActionPerformed(evt);
            }
        });

        btnThemHoaDon.setText("Thêm vào hóa đơn");
        btnThemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHoaDonActionPerformed(evt);
            }
        });

        jLabel9.setText("Mã hóa đơn");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKiemTra)
                    .addComponent(btnThemHoaDon))
                .addGap(78, 78, 78)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTaoHoaDon)
                    .addComponent(btnInHoaDon))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaHD))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(txtTenXe, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(txtKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon)
                    .addComponent(btnThemHoaDon))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInHoaDon)
                    .addComponent(btnKiemTra))
                .addGap(65, 65, 65)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                "Mã", "Xe", "Hình ảnh", "Hãng sản xuất", "Giá"
            }
        ));
        TableXe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableXeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableXe);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Danh sách xe đang bán");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtHinhAnh.setText("Hình ảnh");
        txtHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(87, 87, 87)
                .addComponent(txtHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txtHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
String maxe=null;
String giaxe=null;
    private void TableXeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableXeMouseClicked
        // TODO add your handling code here:
         model=(DefaultTableModel)TableXe.getModel();
        int c=TableXe.getSelectedRow();
        byte[] image=(byte[]) model.getValueAt(c, 2);
        ImageIcon ii = new ImageIcon(image);
        Image images = ii.getImage().getScaledInstance(txtHinhAnh.getWidth(), txtHinhAnh.getHeight(), Image.SCALE_SMOOTH);
        format=new ImageIcon(images);
        txtHinhAnh.setIcon(format);
        txtTenXe.setText(model.getValueAt(c, 1).toString());
        //txtTong.setText(model.getValueAt(c, 4).toString());
        maxe=model.getValueAt(c, 0).toString();
        giaxe=model.getValueAt(c, 4).toString();
        JOptionPane.showMessageDialog(this, maxe);
    }//GEN-LAST:event_TableXeMouseClicked

    private void btnKiemTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKiemTraActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, maxe);
        JOptionPane.showMessageDialog(this, giaxe);
    }//GEN-LAST:event_btnKiemTraActionPerformed

    private void btnThemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHoaDonActionPerformed
        // TODO add your handling code here:
        try{
                String get="INSERT INTO CTHD VALUES(?,?,?)";
                pst=conn.prepareStatement(get);
                pst.setString(1,txtMaHD.getText());
                pst.setString(2,maxe);
                pst.setString(3,giaxe);
                if(pst.executeUpdate()==1){
                    JOptionPane.showMessageDialog(this, "Lưu thông tin thành công");
                    LoadTableCTHD();
                }
   
        }catch(Exception ex){
            
        }
    }//GEN-LAST:event_btnThemHoaDonActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
         try{
                String get="INSERT INTO HoaDon VALUES(?,?,?,?,?)";
                pst=conn.prepareStatement(get);
                pst.setString(1,txtKhachHang.getText());
                pst.setString(2,manv);
                pst.setString(3,txtDiaChi.getText());
                pst.setString(4,txtSdt.getText());
                pst.setString(5,txtTong.getText());
                if(pst.executeUpdate()==1){
                    JOptionPane.showMessageDialog(this, "Lưu thông tin thành công");
                    LoadTableHoaDon();
                }
   
        }catch(Exception ex){
            
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void TableHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableHoaDonMouseClicked
        // TODO add your handling code here:
         model=(DefaultTableModel)TableHoaDon.getModel();
        int c=TableHoaDon.getSelectedRow();
        txtMaHD.setText(model.getValueAt(c, 0).toString());
        String id=model.getValueAt(c, 0).toString();
        String khachhang=model.getValueAt(c, 0).toString();
        String nhanvien = model.getValueAt(c, 0).toString();
        String DiaChi=model.getValueAt(c, 0).toString();
        String sdt=model.getValueAt(c, 0).toString();
        String TongTien=model.getValueAt(c, 0).toString();
        int check_log_out = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn in hóa đơn ?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (check_log_out == JOptionPane.YES_OPTION) {
            String printData = "\t\t\tSố hóa đơn: "+id+"\n\n Khách hàng: " + khachhang + "\n\n "+
        "Nhân viên phuc vụ: "+ nhanvien +"\n\n "+ " địa chỉ nhận xe "+ DiaChi+"\n\n "+// Thay đổi tại đây
        "Số điện thoại khách hàng : "+ sdt+"\n\n "+
        "Tổng hóa đơn :" + TongTien+ "\n\n "+
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
    }//GEN-LAST:event_TableHoaDonMouseClicked

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        // TODO add your handling code here:
         model=(DefaultTableModel)TableHoaDon.getModel();
        int c=TableHoaDon.getSelectedRow();
        txtMaHD.setText(model.getValueAt(c, 0).toString());
        String id=model.getValueAt(c, 0).toString();
          Component rootPane=null;
          int check_log_out = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn in danh sách hóa đơn?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (check_log_out == JOptionPane.YES_OPTION) {
                    new InHoaDon(id).setVisible(true);
        }
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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableCTHD;
    private javax.swing.JTable TableHoaDon;
    private javax.swing.JTable TableXe;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnKiemTra;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThemHoaDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JLabel txtHinhAnh;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtNhanVien;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTenXe;
    private javax.swing.JTextField txtTong;
    // End of variables declaration//GEN-END:variables
}
