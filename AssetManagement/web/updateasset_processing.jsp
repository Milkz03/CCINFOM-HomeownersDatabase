<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="assetmanagement.*, java.math.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Asset Processing</title>
    </head>
    <body>
        <form action="index.html">
            <jsp:useBean id="UpdateB" class="assetmanagement.asset_updates" scope="session" />
            <%  String v_asset_id           = request.getParameter("asset_id");
                String v_asset_name         = request.getParameter("asset_name");
                String v_asset_description  = request.getParameter("asset_description");
                String v_acquisition_date   = request.getParameter("acquisition_date");
                String v_forrent            = request.getParameter("forrent");
                String v_asset_value        = request.getParameter("asset_value");
                String v_type_asset         = request.getParameter("type_asset");
                String v_status             = request.getParameter("status");
                String v_loc_lattitude       = request.getParameter("loc_lattitude");
                String v_loc_longiture      = request.getParameter("loc_longiture");
                String v_hoa_name           = request.getParameter("hoa_name");
                
                UpdateB.asset_id            = Integer.valueOf(v_asset_id);
                UpdateB.asset_name          = v_asset_name;
                UpdateB.asset_description   = v_asset_description;
                UpdateB.acquisition_date    = v_acquisition_date;
                UpdateB.forrent             = Boolean.valueOf(v_forrent);
                UpdateB.asset_value         = new BigDecimal(v_asset_value);
                UpdateB.type_asset          = AssetTypes.valueOf(v_type_asset);
                UpdateB.status              = AssetStatuses.valueOf(v_status);
                UpdateB.loc_lattitude        = new BigDecimal(v_loc_lattitude);
                UpdateB.loc_longiture       = new BigDecimal(v_loc_longiture);
                UpdateB.hoa_name            = v_hoa_name;
                
                int status = UpdateB.upd_asset();
                
                if (status == 1) { %>
                <h1>Update Successful</h1>
            <%  } else {  %>
                <h1>Update Unsuccessful</h1>
            <%  }  %>
            <input type="submit" value="Back to Main Menu">
        </form>
    </body>
</html>
