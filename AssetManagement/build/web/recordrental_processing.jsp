<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Record Rental Processing</title>
    </head>
    <body>
        <form action="index.html">
        <jsp:useBean id="RecordB" class="assetmanagement.rental_record" scope="session" />
            <%  String v_asset_id           = request.getParameter("asset_id");
                String v_transaction_date   = request.getParameter("transaction_date");
                String v_reservation_date   = request.getParameter("reservation_date");
                String v_resident_id        = request.getParameter("resident_id");
                String v_trans_hoid         = request.getParameter("trans_hoid");
                
                RecordB.asset_id            = Integer.valueOf(v_asset_id);
                RecordB.transaction_date    = v_transaction_date;
                RecordB.reservation_date    = v_reservation_date;
                RecordB.resident_id         = Integer.valueOf(v_resident_id);
                RecordB.trans_hoid          = Integer.valueOf(v_trans_hoid);
                
                RecordB.typerec_rental();
                RecordB.officer_details();
                
                int status = RecordB.rec_rentals();
                
                if(RecordB.type_asset.name() == "P"){
                    RecordB.assets_to_rent();
                    
                    for (int i = 0; i < RecordB.asset_idlist.size(); i++){
                        RecordB.asset_id = RecordB.asset_idlist.get(i);
                        RecordB.rec_rentals();
                    }
                }
                
                if (status == 1) { %>
                <h1>Record Successful</h1>
            <%  } else {  %>
                <h1>Record Unsuccessful</h1>
            <%  }  %>
            <input type="submit" value="Back to Main Menu">
        </form>
    </body>
</html>
