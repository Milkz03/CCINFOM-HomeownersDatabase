package assetmanagement;

import java.sql.*;
import java.util.ArrayList;

public class asset_delete {
    
    public int              asset_id;
    public String           asset_name;
    public AssetTypes       type_asset;
    
    public ArrayList<Integer>           asset_idlist    = new ArrayList<>();
    public ArrayList<String>            asset_namelist  = new ArrayList<>();
    
    public asset_delete(){}
    
    public int del_asset() throws ClassNotFoundException {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM assets WHERE asset_id=?");
            pstmt.setInt(1, asset_id);
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
    
    public int typedel_asset() throws ClassNotFoundException {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT type_asset FROM assets WHERE asset_id=?");
            pstmt.setInt(1, asset_id);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                type_asset = AssetTypes.valueOf(rst.getString("type_asset"));
            }
            
            pstmt.close();
            conn.close();
            
            System.out.println("Successful");
            return 1;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public int deletable_assets() {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM assets WHERE asset_id NOT IN (SELECT asset_id FROM asset_transactions);");
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
    
    
    public int assets_to_remove() throws ClassNotFoundException{
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM assets WHERE enclosing_asset=?");
            pstmt.setInt(1, asset_id);
            ResultSet rst = pstmt.executeQuery();
            
            asset_idlist.clear();
            
            while (rst.next()) {
                asset_id        = rst.getInt("asset_id");
                asset_idlist.add(asset_id);
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
    
    public int asset_removal() throws ClassNotFoundException{
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE assets SET enclosing_asset=NULL WHERE asset_id=?");
            pstmt.setInt(1, asset_id);
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
    
    public static void main (String args[]) throws ClassNotFoundException {
        asset_delete test = new asset_delete();
//        test.asset_id = 5006;
//        test.assets_to_remove();
//        
//        for(int i = 0; i < test.asset_idlist.size(); i++){
//            System.out.println(test.asset_idlist.get(i));
//        }
        
//        test.asset_id = 5011;
//        test.asset_removal();

//        test.deletable_assets();
//        for(int i = 0; i < test.asset_idlist.size(); i++){
//            System.out.println(test.asset_idlist.get(i));
//            System.out.println(test.asset_namelist.get(i));
//        }

//        test.asset_id = 5013;
//        test.del_asset();
//        System.out.println(test.type_asset);
    }
}
