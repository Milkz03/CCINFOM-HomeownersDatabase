package templateAssets;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mukbookpro
 */
import java.util.*;
import java.sql.*;

public class assets {
    
    //fields of assets
    public int      asset_id;
    public String   asset_name;
    public int      asset_status;
    
    //list of assets
    public ArrayList<Integer>   asset_idlist        = new ArrayList<>();
    public ArrayList<String>    asset_namelist      = new ArrayList<>();
    public ArrayList<Integer>   asset_statuslist    = new ArrayList<>();
    
    public assets(){}
    
    public int assets_fortransfer () {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assetsdb?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT asset_id, asset_name FROM assets WHERE status=9001");
            ResultSet rst = pstmt.executeQuery();
            
            asset_idlist.clear();
            asset_namelist.clear();
            asset_statuslist.clear();
            
            while (rst.next()) {
                asset_id        = rst.getInt("asset_id");
                asset_name      = rst.getString("asset_name");
                asset_status    = 9001;
                asset_idlist.add(asset_id);
                asset_namelist.add(asset_name);
                asset_statuslist.add(asset_status);
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
    
    public int register_asset() throws ClassNotFoundException {
        
        try {
            //This is where we will put codes that will interact with databases
            //1. connect to our database
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assetsdb?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            //2. Prepare our SQL Statement
            //      2.1 To get the next AssetID
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(asset_id) + 1 AS newID FROM assets");
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()) {
                asset_id = rst.getInt("newID");
            }
            
            //      2.2 Save the new Asset
            pstmt = conn.prepareStatement("INSERT INTO assets (asset_id, asset_name, status) VALUE (?, ?, ?)");
            pstmt.setInt(1, asset_id);
            pstmt.setString(2, asset_name);
            asset_status = 9001;
            pstmt.setInt(3, asset_status);
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
        
        assets A = new assets();
//        A.asset_name = "Test Run";
//        System.out.println(A.register_asset());
        
        
        A.assets_fortransfer();
        for (int i = 0; i < A.asset_idlist.size(); i++) {
            System.out.println(A.asset_idlist.get(i));
            System.out.println(A.asset_namelist.get(i));
            System.out.println(A.asset_statuslist.get(i));
        }
        
    }
}
