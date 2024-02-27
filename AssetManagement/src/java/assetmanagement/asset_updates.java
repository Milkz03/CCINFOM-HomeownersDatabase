package assetmanagement;

import java.sql.*;
import java.util.ArrayList;
import java.math.*;
import java.util.Arrays;

public class asset_updates {
    
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
    
    public ArrayList<Integer>           asset_idlist    = new ArrayList<>();
    public ArrayList<String>            asset_namelist  = new ArrayList<>();
    public ArrayList<String>            asset_typelist  = new ArrayList<>(Arrays.asList("Property", "Equipment", "F&F", "Others"));
    public ArrayList<AssetTypes>        asset_typeabrvlist  = new ArrayList<>(Arrays.asList(AssetTypes.P, AssetTypes.E, AssetTypes.F, AssetTypes.O));
    public ArrayList<String>            asset_statuslist  = new ArrayList<>(Arrays.asList("Working", "Deteriorated", "For Repair", "For Disposal"));
    public ArrayList<AssetStatuses>     asset_statusabrvlist  = new ArrayList<>(Arrays.asList(AssetStatuses.W, AssetStatuses.D, AssetStatuses.P, AssetStatuses.S));
    public ArrayList<String>            hoa_namelist  = new ArrayList<>();
    public ArrayList<String>            subdv_namelist  = new ArrayList<>();
    
    public asset_updates(){}
    
    public int upd_asset() throws ClassNotFoundException{
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE assets SET asset_name=?, asset_description=?, acquisition_date=?, forrent=?, asset_value=?, type_asset=?, status=?, loc_lattitude=?, loc_longiture=?, hoa_name=? WHERE asset_id=?");
            pstmt.setInt(11, asset_id);
            pstmt.setString(1, asset_name);
            pstmt.setString(2, asset_description);
            pstmt.setDate(3, Date.valueOf(acquisition_date));
            pstmt.setBoolean(4, forrent);
            pstmt.setBigDecimal(5, asset_value);
            pstmt.setString(6, type_asset.name());
            pstmt.setString(7, status.name());
            pstmt.setBigDecimal(8, loc_lattitude);
            pstmt.setBigDecimal(9, loc_longiture);
            pstmt.setString(10, hoa_name);
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
    
    public int updatable_assets() {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT asset_id, asset_name FROM assets WHERE status='W' OR status='D' OR status='P' OR status='S'");
            ResultSet rst = pstmt.executeQuery();
            
            asset_idlist.clear();
            asset_namelist.clear();
            
            while (rst.next()) {
                asset_id        = rst.getInt("asset_id");
                asset_name      = rst.getString("asset_name");
                asset_idlist.add(asset_id);
                asset_namelist.add(asset_name);
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
        asset_updates test = new asset_updates();
//        test.asset_id = 5011;
//        test.asset_name = "This is a New Test";
//        test.asset_description = "Updating the Description of this Asset";
//        test.acquisition_date = "2003-07-20";
//        test.forrent = false;
//        test.asset_value = new BigDecimal("999999.99999");
//        test.type_asset = AssetTypes.E;
//        test.status = AssetStatuses.D;
//        test.loc_lattitude = new BigDecimal("120.290399");
//        test.loc_longiture = new BigDecimal("121.492999");
//        test.hoa_name = "SJH";
//        test.enclosing_asset = 5004;
//        test.upd_asset();

//        test.updatable_assets();
//        for (int i = 0; i < test.asset_idlist.size(); i++) {
//            System.out.println(test.asset_idlist.get(i));
//            System.out.println(test.asset_namelist.get(i));
//        }

//        test.available_HOAs();
//        for (int i = 0; i < test.hoa_namelist.size(); i++){
//            System.out.println(test.hoa_namelist.get(i));
//            System.out.println(test.subdv_namelist.get(i));
//        }
    }
}
