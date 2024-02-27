/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package templateAssets;

/**
 *
 * @author mukbookpro
 */

import java.util.*;
import java.sql.*;

public class asset_transfers {
    
    public int      asset_id;
    public String   transfer_date;
    public String   from_location;
    public String   to_location;
    public int      status;
    
    public ArrayList<Integer>   asset_idlist        = new ArrayList<> ();
    public ArrayList<String>    transfer_datelist   = new ArrayList<> ();
    public ArrayList<String>    from_locationlist   = new ArrayList<> ();
    public ArrayList<String>    to_locationlist     = new ArrayList<> ();
    public ArrayList<Integer>   statuslist          = new ArrayList<> ();
    
    public asset_transfers() {}
    
    public int register_transfer() {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/assetsdb?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO asset_transfers (asset_id, transfer_date, from_location, to_location, status) VALUES (?,NOW(),?,?,8001)");
            pstmt.setInt(1,asset_id);
            pstmt.setString(2, from_location);
            pstmt.setString(3, to_location);
            pstmt.executeUpdate();
            
            // Update the status of the asset
            pstmt = conn.prepareStatement("UPDATE assets SET status=9003 WHERE asset_id=?");
            pstmt.setInt(1,asset_id);
            pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            return 1;
            
            } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public static void main (String args[]) {
        
        asset_transfers B = new asset_transfers();
        B.asset_id      = 1004;
        B.from_location = "Clubhouse";
        B.to_location   = "Presidents house";
        B.register_transfer();
    }
}
