<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Return a Rented Asset</title>
    </head>
    <body>
        <form action="returnrental_processing.jsp">
            <jsp:useBean id="ReturnA" class="assetmanagement.rental_return" scope="session"/>
            Asset:<select id="asset_id" name="asset_id" required>
                <%  ReturnA.returnable_assets();
                    for (int i = 0; i < ReturnA.asset_idlist.size(); i++) { %>
                    <option value="<%=ReturnA.asset_idlist.get(i)%>"><%=ReturnA.asset_idlist.get(i) + " - " + ReturnA.asset_namelist.get(i)%></option>
                <%  }  %>
            </select><br>
        <input type="submit" value="Submit Update">
    </body>
</html>
