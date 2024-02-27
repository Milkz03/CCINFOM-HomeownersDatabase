package assetmanagement;

import java.sql.*;
import java.util.ArrayList;

public class rental_return {
    
    public int              asset_id;
    public String           asset_name;
    public RentalStatuses   status;
    
    public ArrayList<Integer>           asset_idlist    = new ArrayList<>();
    public ArrayList<String>            asset_namelist  = new ArrayList<>();
    
    public rental_return(){}
    
    public int ret_rentals() throws ClassNotFoundException {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE asset_rentals SET status=? WHERE asset_id=? and status='O'");
            pstmt.setInt(2, asset_id);
            status = RentalStatuses.N;
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
    
    public int returnable_assets() {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT ar.asset_id, a.asset_name FROM asset_rentals ar JOIN asset_transactions atr ON ar.asset_id = atr.asset_id AND ar.rental_date = atr.transaction_date JOIN assets a ON atr.asset_id = a.asset_id WHERE ar.status='O'");
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
}
