<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dispose Asset</title>
    </head>
    <body>
        <form action="disposeasset_processing.jsp">
            <jsp:useBean id="DisposeA" class="assetmanagement.asset_dispose" scope="session"/>
            Asset:<select id="asset_id" name="asset_id" required>
                <%  DisposeA.assets_Disp();
                    for (int i = 0; i < DisposeA.asset_idlist.size(); i++) { %>
                    <option value="<%=DisposeA.asset_idlist.get(i)%>"><%=DisposeA.asset_idlist.get(i) + " - " + DisposeA.asset_namelist.get(i)%></option>
                <%  }  %>
                </select><br>
            <input type="submit" value="Dispose Assets">
        </form>
    </body>
</html>
