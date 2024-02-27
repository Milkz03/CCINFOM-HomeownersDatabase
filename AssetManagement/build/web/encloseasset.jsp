<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enclose an Asset</title>
    </head>
    <body>
        <form action="encloseasset_processing.jsp">
            <jsp:useBean id="EncloseA" class="assetmanagement.asset_enclosure" scope="session"/>
            Asset:<select id="asset_id" name="asset_id" required>
                <%  EncloseA.available_nonproperties();
                    for (int i = 0; i < EncloseA.nonproperty_list.size(); i++) { %>
                    <option value="<%=EncloseA.nonproperty_idlist.get(i)%>"><%=EncloseA.nonproperty_idlist.get(i) + " - " + EncloseA.nonproperty_list.get(i)%></option>
                <%  }  %>
            </select><br>
            Enclosing Asset:<select id="enclosing_asset" name="enclosing_asset" required>
                <%  EncloseA.available_properties();
                    for (int i = 0; i < EncloseA.property_list.size(); i++) { %>
                    <option value="<%=EncloseA.property_idlist.get(i)%>"><%=EncloseA.property_idlist.get(i) + " - " + EncloseA.property_list.get(i)%></option>
                <%  }  %>
                <option value="-1">Unenclose Asset</<option>
            </select><br>
            <input type="submit" value="Enclose Asset">
        </form>
    </body>
</html>
