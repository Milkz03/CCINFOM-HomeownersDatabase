<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Record a Rental</title>
    </head>
    <body>
        <form action="recordrental_processing.jsp">
            <jsp:useBean id="RecordA" class="assetmanagement.rental_record" scope="session"/>
            Asset:<select id="asset_id" name="asset_id" required>
                <%  RecordA.rentable_assets();
                    for (int i = 0; i < RecordA.asset_idlist.size(); i++) { %>
                    <option value="<%=RecordA.asset_idlist.get(i)%>"><%=RecordA.asset_idlist.get(i) + " - " + RecordA.asset_namelist.get(i)%></option>
                <%  }  %>
            </select><br>
            Transaction Date:<input type="date" id="transaction_date" name="transaction_date" required><br>
            Reservation Date:<input type="date" id="reservation_date" name="reservation_date" required><br>
            Resident Renting:<select id="resident_id" name="resident_id" required>
                <%  RecordA.renting_residents();
                    for (int i = 0; i < RecordA.resident_idlist.size(); i++) { %>
                    <option value="<%=RecordA.resident_idlist.get(i)%>"><%=RecordA.resident_namelist.get(i)%></option>
                <%  }  %>
            </select><br>
            Officer in Charge:<select id="trans_hoid" name="trans_hoid" required>
                <%  RecordA.authorizing_officers();
                    for (int i = 0; i < RecordA.officer_idlist.size(); i++) { %>
                    <option value="<%=RecordA.officer_idlist.get(i)%>"><%=RecordA.trans_positionlist.get(i) + " - " + RecordA.officer_namelist.get(i)%></option>
                <%  }  %>
            </select><br>
            <input type="submit" value="Submit Update">
        </form>
    </body>
</html>
