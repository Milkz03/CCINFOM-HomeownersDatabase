<%-- 
    Document   : fordisposeasset_processing
    Created on : Apr 15, 2023, 4:40:47 PM
    Author     : mukbookpro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mark for Disposal</title>
    </head>
    <body>
        <form action="index.html">
            <jsp:useBean id="forDisposeB" class="assetmanagement.asset_forDispose" scope="session" />
            <%  //Receive the value from createtransfer.jsp
                String v_asset_id       = request.getParameter("asset_id");
                forDisposeB.asset_id    = Integer.valueOf(v_asset_id);
                
                int status = forDisposeB.forDisp_asset();
                
                if (status == 1) { 
            %>
                <h1>Update Successful</h1>
            <%  } else {
            %>
                <h1>Update Unsuccessful</h1>
            <%  }
            %>
            <input type="submit" value="Back to Main Menu">
        </form>
    </body>
</html>
