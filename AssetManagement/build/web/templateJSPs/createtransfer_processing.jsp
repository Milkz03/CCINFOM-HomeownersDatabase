<%-- 
    Document   : createtransfer_processing
    Created on : Mar 23, 2023, 7:05:57 AM
    Author     : mukbookpro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, assetmanagement.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create transfer Processing</title>
    </head>
    <body>
        <form action="index.html">
            <jsp:useBean id="B" class="templateAssets.asset_transfers" scope="session" />
            <%  //Receive the value from createtransfer.jsp
                String v_asset_id       = request.getParameter("asset_id");
                String v_from_location  = request.getParameter("from_location");
                String v_to_location    = request.getParameter("to_location");
                
                B.asset_id      = Integer.valueOf(v_asset_id);
                B.from_location = v_from_location;
                B.to_location   = v_to_location;
                int status = B.register_transfer();
                
                if (status == 1) { 
            %>
                <h1>Creating a Transfer Successful</h1>
            <%  } else {
            %>
                <h1>Creating a Transfer Unsuccessful</h1>
            <%  }
            %>
            <input type="submit" value="Back to Main Menu">
        </form>
    </body>
</html>
