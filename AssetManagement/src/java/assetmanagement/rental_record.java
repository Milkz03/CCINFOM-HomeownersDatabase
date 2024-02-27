package assetmanagement;

import java.sql.*;
import java.util.ArrayList;

public class rental_record {
    
    public int              asset_id;
    public String           asset_name;
    public String           transaction_date;
    public int              trans_hoid;
    public String           trans_position;
    public String           trans_electiondate;
    public boolean          isdeleted;
    public TransactionTypes transaction_type;
    public String           reservation_date;
    public int              resident_id;
    public RentalStatuses   status;
    public String           resident_name;
    public String           officer_name;
    public AssetTypes       type_asset;
    
    public ArrayList<Integer>           asset_idlist    = new ArrayList<>();
    public ArrayList<String>            asset_namelist  = new ArrayList<>();
    public ArrayList<Integer>           resident_idlist    = new ArrayList<>();
    public ArrayList<String>            resident_namelist  = new ArrayList<>();
    public ArrayList<Integer>           officer_idlist    = new ArrayList<>();
    public ArrayList<String>            officer_namelist  = new ArrayList<>();
    public ArrayList<String>            trans_positionlist  = new ArrayList<>();
    
    public rental_record(){}
    
    public int rec_rentals() throws ClassNotFoundException {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO asset_transactions (asset_id, transaction_date, trans_hoid, trans_position, trans_electiondate, isdeleted, transaction_type) VALUE (?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, asset_id);
            pstmt.setDate(2, Date.valueOf(transaction_date));
            pstmt.setInt(3, trans_hoid);
            pstmt.setString(4, trans_position);
            pstmt.setDate(5, Date.valueOf(trans_electiondate));
            isdeleted = false;
            pstmt.setBoolean(6, isdeleted);
            transaction_type = TransactionTypes.R;
            pstmt.setString(7, transaction_type.name());
            pstmt.executeUpdate();
            
            pstmt = conn.prepareStatement("INSERT INTO asset_rentals (asset_id, rental_date, reservation_date, resident_id, status) VALUE (?, ?, ?, ?, ?)");
            pstmt.setInt(1, asset_id);
            pstmt.setDate(2, Date.valueOf(transaction_date));
            pstmt.setDate(3, Date.valueOf(reservation_date));
            pstmt.setInt(4, resident_id);
            status = RentalStatuses.R;
            pstmt.setString(5, status.name());
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
    
    public int rentable_assets() {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT asset_id, asset_name FROM assets WHERE forrent = true");
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
    
    public int renting_residents() {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT r.resident_id, CONCAT(p.firstname, ' ', p.lastname) AS fullname FROM residents r JOIN people p ON r.resident_id = p.peopleid ORDER BY fullname");
            ResultSet rst = pstmt.executeQuery();
            
            resident_idlist.clear();
            resident_namelist.clear();
            
            while (rst.next()) {
                resident_id        = rst.getInt("resident_id");
                resident_name      = rst.getString("fullname");
                resident_idlist.add(resident_id);
                resident_namelist.add(resident_name);
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
    
    public int authorizing_officers() {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT o.ho_id, CONCAT(p.firstname, ' ', p.lastname) AS fullname, o.position FROM officer o JOIN homeowner h ON o.ho_id = h.ho_id JOIN people p ON h.ho_id = p.peopleid WHERE o.election_date IN (SELECT MAX(election_date) FROM officer)");
            ResultSet rst = pstmt.executeQuery();
            
            officer_idlist.clear();
            officer_namelist.clear();
            trans_positionlist.clear();
            
            while (rst.next()) {
                trans_hoid        = rst.getInt("o.ho_id");
                officer_name      = rst.getString("fullname");
                trans_position  = rst.getString("o.position");
                officer_idlist.add(trans_hoid);
                officer_namelist.add(officer_name);
                trans_positionlist.add(trans_position);
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
    
    public int officer_details() {
        try {
            Connection conn;
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HOADB?useTimezone=true&serverTimezone=UTC&user=root&password=mikkelg_03");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT position, election_date FROM officer WHERE election_date IN (SELECT MAX(election_date) FROM officer) AND ho_id=?");
            pstmt.setInt(1, trans_hoid);
            ResultSet rst = pstmt.executeQuery();
            
            while (rst.next()) {
                trans_position      = rst.getString("position");
                trans_electiondate  = rst.getString("election_date");
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
    
    public int typerec_rental() throws ClassNotFoundException {
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
    
    public int assets_to_rent() throws ClassNotFoundException{
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
    
    public static void main (String args[]) throws ClassNotFoundException {
        rental_record test = new rental_record();
        
//        test.renting_residents();
//        for (int i = 0; i < test.resident_idlist.size(); i++) {
//            System.out.println(test.resident_idlist.get(i));
//            System.out.println(test.resident_namelist.get(i));
//        }
//        
//        test.authorizing_officers();
//        for (int i = 0; i < test.officer_idlist.size(); i++) {
//            System.out.println(test.officer_idlist.get(i));
//            System.out.println(test.officer_namelist.get(i));
//            System.out.println(test.trans_positionlist.get(i));
//        }
    }
}
