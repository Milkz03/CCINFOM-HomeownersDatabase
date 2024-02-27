package assetmanagement;

import java.sql.*;
import java.util.ArrayList;
import java.math.*;
import java.util.Arrays;

public class asset_register {
    
    public int              asset_id;
    public String           asset_name;
    public String           asset_description;
    public String           acquisition_date;
    public boolean          forrent;
    public BigDecimal       asset_value;
    public AssetTypes       type_asset;
    public AssetStatuses    status;
    public BigDecimal       loc_lattitude;
    public BigDecimal       loc_longiture;
    public String           hoa_name;
    public String           subdv_name;
    
    public ArrayList<String>            asset_typelist  = new ArrayList<>(Arrays.asList("Property", "Equipment", "F&F", "Others"));
    public ArrayList<AssetTypes>        asset_typeabrvlist  = new ArrayList<>(Arrays.asList(AssetTypes.P, AssetTypes.E, AssetTypes.F, AssetTypes.O));
    public ArrayList<String>            asset_statuslist  = new ArrayList<>(Arrays.asList("Working", "Deteriorated", "For Repair"));
    public ArrayList<AssetStatuses>     asset_statusabrvlist  = new ArrayList<>(Arrays.asList(AssetStatuses.W, AssetStatuses.D, AssetStatuses.P));
    public ArrayList<String>            hoa_namelist  = new ArrayList<>();
    public ArrayList<String>            subdv_namelist  = new ArrayList<>();
    
    public asset_register(){}
    
    public int reg_asset() throws ClassNotFoundException {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");

            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(asset_id) + 1 AS newID FROM assets");
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                asset_id = rst.getInt("newID");
            }
            
            pstmt = conn.prepareStatement("INSERT INTO assets (asset_id, asset_name, asset_description, acquisition_date, forrent, asset_value, type_asset, status, loc_lattitude, loc_longiture, hoa_name) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, asset_id);
            pstmt.setString(2, asset_name);
            pstmt.setString(3, asset_description);
            pstmt.setDate(4, Date.valueOf(acquisition_date));
            pstmt.setBoolean(5, forrent);
            pstmt.setBigDecimal(6, asset_value);
            pstmt.setString(7, type_asset.name());
            pstmt.setString(8, status.name());
            pstmt.setBigDecimal(9, loc_lattitude);
            pstmt.setBigDecimal(10, loc_longiture);
            pstmt.setString(11, hoa_name);
            pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            
            System.out.println("Successful");
            return 1;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public int available_HOAs() {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT hoa_name, subdivision_name FROM hoa");
            ResultSet rst = pstmt.executeQuery();
            
            hoa_namelist.clear();
            subdv_namelist.clear();
            
            while (rst.next()) {
                hoa_name    = rst.getString("hoa_name");
                subdv_name  = rst.getString("subdivision_name");
                hoa_namelist.add(hoa_name);
                subdv_namelist.add(subdv_name);
            }
            pstmt.close();
            conn.close();
            
            System.out.println("Successful");
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    
    public static void main (String args[]) throws ClassNotFoundException {
        asset_register test = new asset_register();
//        test.asset_name = "Test_run3";
//        test.asset_description = "This is a test3";
//        test.acquisition_date = "2023-05-17";
//        test.forrent = true;
//        test.asset_value = new BigDecimal("592432");
//        test.type_asset = AssetTypes.P;
//        test.status = AssetStatuses.W;
//        test.loc_lattitude = new BigDecimal(103.5502);
//        test.loc_longiture = new BigDecimal(100.2435);
//        test.hoa_name = "SMH";
//        test.enclosing_asset = 5008;
        test.reg_asset();
    }
    
}
