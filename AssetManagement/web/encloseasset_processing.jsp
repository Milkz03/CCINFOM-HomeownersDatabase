<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enclose Asset Processing</title>
    </head>
    <body>
        <form action="index.html">
            <jsp:useBean id="EncloseB" class="assetmanagement.asset_enclosure" scope="session" />
            <%  String v_asset_id           = request.getParameter("asset_id");
                String v_enclosing_asset    = request.getParameter("enclosing_asset");
                
                EncloseB.asset_id           = Integer.valueOf(v_asset_id);
                EncloseB.enclosing_asset    = Integer.valueOf(v_enclosing_asset);
                
                int status = EncloseB.enc_asset();
                
                if (status == 1) { %>
                <h1>Enclose Successful</h1>
            <%  } else {  %>
                <h1>Enclose Unsuccessful</h1>
            <%  }  %>
            <input type="submit" value="Back to Main Menu">
        </form>
    </body>
</html>
