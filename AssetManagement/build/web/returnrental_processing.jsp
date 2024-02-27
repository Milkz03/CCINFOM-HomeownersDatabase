<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Return a Rented Asset Processing</title>
    </head>
    <body>
        <form action="index.html">
        <jsp:useBean id="ReturnB" class="assetmanagement.rental_return" scope="session" />
            <%  String v_asset_id           = request.getParameter("asset_id");
                ReturnB.asset_id            = Integer.valueOf(v_asset_id);
                
                int status = ReturnB.ret_rentals();
                
                if (status == 1) { %>
                <h1>Return Successful</h1>
            <%  } else {  %>
                <h1>Return Unsuccessful</h1>
            <%  }  %>
            <input type="submit" value="Back to Main Menu">
        </form>
    </body>
</html>
