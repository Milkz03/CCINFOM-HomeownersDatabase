<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Asset</title>
    </head>
    <body>
        <form action="deleteasset_processing.jsp">
            <jsp:useBean id="DeleteA" class="assetmanagement.asset_delete" scope="session"/>
            Asset:<select id="asset_id" name="asset_id" required>
                <%  DeleteA.deletable_assets();
                    for (int i = 0; i < DeleteA.asset_idlist.size(); i++) { %>
                    <option value="<%=DeleteA.asset_idlist.get(i)%>"><%=DeleteA.asset_idlist.get(i) + " - " + DeleteA.asset_namelist.get(i)%></option>
                <%  }  %>
            </select><br>
            <input type="submit" value="Delete Asset">
        </form>
    </body>
</html>
