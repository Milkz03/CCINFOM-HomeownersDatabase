<%-- 
    Document   : createtransfer
    Created on : Mar 23, 2023, 7:00:44 AM
    Author     : mukbookpro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, assetmanagement.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a new Transfer</title>
    </head>
    <body>
        <form action="createtransfer_processing.jsp">
            <jsp:useBean id="A" class="templateAssets.assets" scope="session" />
            Asset:<select id="asset_id" name="asset_id">
                <%
                    A.assets_fortransfer();
                    for (int i = 0; i < A.asset_idlist.size(); i++) {
                %>
                    <option value="<%=A.asset_idlist.get(i)%>"><%=A.asset_namelist.get(i)%></option>
                <%  }
                %>
            </select><br>
            From Location:<input type="text" id="from_location" name="from_location"><br>
            To Location:<input type="text" id="to_location" name="to_location"><br>
            <input type="submit" value="Submit Transfer">
        </form>
    </body>
</html>
