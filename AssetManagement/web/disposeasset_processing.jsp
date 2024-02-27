<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dispose Asset</title>
    </head>
    <body>
        <form action="index.html">
            <jsp:useBean id="DisposeB" class="assetmanagement.asset_dispose" scope="session" />
            <%  String v_asset_id   = request.getParameter("asset_id");
                DisposeB.asset_id   = Integer.valueOf(v_asset_id);
                
                int status = DisposeB.disp_asset();
                
                if (status == 1) { %>
                <h1>Dispose Successful</h1>
            <%  } else {  %>
                <h1>Dispose Unsuccessful</h1>
            <%  }  %>
            <input type="submit" value="Back to Main Menu">
        </form>
    </body>
</html>
