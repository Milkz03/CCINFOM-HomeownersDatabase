package assetmanagement;

import java.sql.*;
import java.util.ArrayList;

public class asset_enclosure {
    
    public int      asset_id;
    public String   asset_name;
    public Integer  enclosing_asset;
    
    public ArrayList<String>    nonproperty_list    = new ArrayList<>();
    public ArrayList<Integer>   nonproperty_idlist  = new ArrayList<>();
    public ArrayList<String>    property_list       = new ArrayList<>();
    public ArrayList<Integer>   property_idlist     = new ArrayList<>();
    
    public asset_enclosure(){}
    
    public int enc_asset() throws ClassNotFoundException{
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            if (enclosing_asset == -1){
                enclosing_asset = null;
            }
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE assets SET enclosing_asset=? WHERE asset_id=?");
            pstmt.setObject(1, enclosing_asset);
            pstmt.setInt(2, asset_id);
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
    
    public int available_nonproperties() {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT asset_id, asset_name FROM assets WHERE type_asset!='P'");
            ResultSet rst = pstmt.executeQuery();
            
            nonproperty_list.clear();
            nonproperty_idlist.clear();
            
            while (rst.next()) {
                asset_name  =rst.getString("asset_name");
                asset_id =rst.getInt("asset_id");
                nonproperty_list.add(asset_name);
                nonproperty_idlist.add(asset_id);
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
    
    public int available_properties() {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT asset_id, asset_name FROM assets WHERE type_asset='P'");
            ResultSet rst = pstmt.executeQuery();
            
            property_list.clear();
            property_idlist.clear();
            
            while (rst.next()) {
                asset_name  =rst.getString("asset_name");
                asset_id =rst.getInt("asset_id");
                property_list.add(asset_name);
                property_idlist.add(asset_id);
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
        asset_enclosure testing = new asset_enclosure();
        
//        testing.available_nonproperties();
//            for (int i = 0; i < testing.nonproperty_list.size(); i++) {
//                System.out.println(testing.nonproperty_list.get(i));
//                System.out.println(testing.nonproperty_idlist.get(i));
//            }
//        testing.available_properties();
//            for (int i = 0; i < testing.property_list.size(); i++) {
//                System.out.println(testing.property_list.get(i));
//                System.out.println(testing.property_idlist.get(i));
//            }
        
    }
}
