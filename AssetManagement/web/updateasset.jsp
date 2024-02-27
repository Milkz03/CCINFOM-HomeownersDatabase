<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update an Asset</title>
    </head>
    <body>
        <form action="updateasset_processing.jsp">
            <jsp:useBean id="UpdateA" class="assetmanagement.asset_updates" scope="session"/>
            Asset:<select id="asset_id" name="asset_id" required>
                <%  UpdateA.updatable_assets();
                    for (int i = 0; i < UpdateA.asset_idlist.size(); i++) { %>
                    <option value="<%=UpdateA.asset_idlist.get(i)%>"><%=UpdateA.asset_idlist.get(i) + " - " + UpdateA.asset_namelist.get(i)%></option>
                <%  }  %>
            </select><br>
            Asset Name:<input type="text" id="asset_name" name="asset_name" required><br>
            Asset Description:<input type="text" id="asset_description" name="asset_description" required><br>
            Acquisition Date:<input type="date" id="acquisition_date" name="acquisition_date" required><br>
            <input type="checkbox" id="forrent" name="forrent">For rent<br>
            Asset Value:<input type="number" id="asset_value" name="asset_value" step=".01" required><br>
            Asset Type:<select id="type_asset" name="type_asset" required>
                <%  for (int i = 0; i < UpdateA.asset_typelist.size(); i++) { %>
                    <option value="<%=UpdateA.asset_typeabrvlist.get(i)%>"><%=UpdateA.asset_typelist.get(i)%></option>
                <%  }  %>
            </select><br>
            Asset Status:<select id="status" name="status" required>
                <%  for (int i = 0; i < UpdateA.asset_statuslist.size(); i++) { %> 
                    <option value="<%=UpdateA.asset_statusabrvlist.get(i)%>"><%=UpdateA.asset_statuslist.get(i)%></option>
                <%  }  %>
            </select><br>
            Latitude:<input type="number" id="loc_lattitude" name="loc_lattitude" step=".0001" required><br>
            Longitude:<input type="number" id="loc_longiture" name="loc_longiture" step=".0001" required><br>
            HOA Name:<select id="hoa_name" name="hoa_name" required>
                <%  UpdateA.available_HOAs();
                    for (int i = 0; i < UpdateA.hoa_namelist.size(); i++) { %>
                    <option value="<%=UpdateA.hoa_namelist.get(i)%>"><%=UpdateA.subdv_namelist.get(i)%></option>
                <%  }  %>
            </select>
            <input type="submit" value="Submit Update">
        </form>
    </body>
</html>
