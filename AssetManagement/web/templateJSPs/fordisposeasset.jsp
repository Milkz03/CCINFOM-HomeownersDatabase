<%-- 
    Document   : fordisposeasset
    Created on : Apr 15, 2023, 4:18:26 PM
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
        <form action="fordisposeasset_processing.jsp">
            <jsp:useBean id="forDisposeA" class="assetmanagement.asset_forDispose" scope="session"/>
            Asset:<select id="asset_id" name="asset_id">
                <%
                    forDisposeA.assets_forDisp();
                    for (int i = 0; i < forDisposeA.asset_idlist.size(); i++) {
                %>
                    <option value="<%=forDisposeA.asset_idlist.get(i)%>"><%=forDisposeA.asset_idlist.get(i) + " - " + forDisposeA.asset_namelist.get(i)%></option>
                <%  }
                %>
            </select><br>
            <input type="submit" value="Mark for disposal">
    </body>
</html>
