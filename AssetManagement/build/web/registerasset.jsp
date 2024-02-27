<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Asset</title>
    </head>
    <body>
        <form action="registerasset_processing.jsp">
            <jsp:useBean id="RegisterA" class="assetmanagement.asset_register" scope="session"/>
            Asset Name:<input type="text" id="asset_name" name="asset_name" required><br>
            Asset Description:<input type="text" id="asset_description" name="asset_description" required><br>
            Acquisition Date:<input type="date" id="acquisition_date" name="acquisition_date" required><br>
            <input type="checkbox" id="forrent" name="forrent">For rent<br>
            Asset Value:<input type="number" id="asset_value" name="asset_value" step=".01" required><br>
            Asset Type:<select id="type_asset" name="type_asset" required>
                <%  for (int i = 0; i < RegisterA.asset_typelist.size(); i++) { %>
                    <option value="<%=RegisterA.asset_typeabrvlist.get(i)%>"><%=RegisterA.asset_typelist.get(i)%></option>
                <%  }  %>
            </select><br>
             Asset Status:<select id="status" name="status" required>
                <%  for (int i = 0; i < RegisterA.asset_statuslist.size(); i++) { %> 
                    <option value="<%=RegisterA.asset_statusabrvlist.get(i)%>"><%=RegisterA.asset_statuslist.get(i)%></option>
                <%  }  %>
            </select><br>
            Latitude:<input type="number" id="loc_lattitude" name="loc_lattitude" step=".0001" required><br>
            Longitude:<input type="number" id="loc_longiture" name="loc_longiture" step=".0001" required><br>
            HOA Name:<select id="hoa_name" name="hoa_name" required>
                <%  RegisterA.available_HOAs();
                    for (int i = 0; i < RegisterA.hoa_namelist.size(); i++) { %>
                    <option value="<%=RegisterA.hoa_namelist.get(i)%>"><%=RegisterA.subdv_namelist.get(i)%></option>
                <%  }  %>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
