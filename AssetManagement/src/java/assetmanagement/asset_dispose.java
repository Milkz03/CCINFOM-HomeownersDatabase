package assetmanagement;

import java.sql.*;
import java.util.ArrayList;

public class asset_dispose {
    
    public int              asset_id;
    public String           asset_name;
    public AssetStatuses    status = AssetStatuses.X;
    
    public ArrayList<Integer>           asset_idlist    = new ArrayList<>();
    public ArrayList<String>            asset_namelist  = new ArrayList<>();
    
    public asset_dispose(){}
    
    public int assets_Disp() {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT asset_id, asset_name FROM assets WHERE status='S'");
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
    
    public int disp_asset() throws ClassNotFoundException{
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE assets SET status=? WHERE asset_id=?");
            pstmt.setInt(2, asset_id);
            pstmt.setString(1, status.name());
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
        asset_dispose test = new asset_dispose();
//        test.asset_id = 5011;
//        test.disp_asset();
    }
}
