<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="assetmanagement.*, java.math.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Asset Processing</title>
    </head>
    <body>
        <form action="index.html">
            <jsp:useBean id="RegisterB" class="assetmanagement.asset_register" scope="session" />
            <%  String v_asset_name         = request.getParameter("asset_name");
                String v_asset_description  = request.getParameter("asset_description");
                String v_acquisition_date   = request.getParameter("acquisition_date");
                String v_forrent            = request.getParameter("forrent");
                String v_asset_value        = request.getParameter("asset_value");
                String v_type_asset         = request.getParameter("type_asset");
                String v_status             = request.getParameter("status");
                String v_loc_lattitude      = request.getParameter("loc_lattitude");
                String v_loc_longiture      = request.getParameter("loc_longiture");
                String v_hoa_name           = request.getParameter("hoa_name");
                
                RegisterB.asset_name          = v_asset_name;
                RegisterB.asset_description   = v_asset_description;
                RegisterB.acquisition_date    = v_acquisition_date;
                RegisterB.forrent             = Boolean.valueOf(v_forrent);
                RegisterB.asset_value         = new BigDecimal(v_asset_value);
                RegisterB.type_asset          = AssetTypes.valueOf(v_type_asset);
                RegisterB.status              = AssetStatuses.valueOf(v_status);
                RegisterB.loc_lattitude       = new BigDecimal(v_loc_lattitude);
                RegisterB.loc_longiture       = new BigDecimal(v_loc_longiture);
                RegisterB.hoa_name            = v_hoa_name;
                
                int status = RegisterB.reg_asset();
                
                if (status == 1) { %>
                <h1>Registering Asset Successful!</h1>
            <%  } else { %>
                <h1>Registering Asset Failed!</h1>
            <%  }  %>
            <input type="submit" value="Return to Menu">
        </form>
    </body>
</html>
