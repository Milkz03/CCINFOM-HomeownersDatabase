<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Asset</title>
    </head>
    <body>
        <form action="index.html">
            <jsp:useBean id="DeleteB" class="assetmanagement.asset_delete" scope="session" />
            <%  String v_asset_id       = request.getParameter("asset_id");
                DeleteB.asset_id        = Integer.valueOf(v_asset_id);
                
                DeleteB.typedel_asset();
                
                if (DeleteB.type_asset.name() == "P") {
                     DeleteB.assets_to_remove();
                    
                    for (int i = 0; i < DeleteB.asset_idlist.size(); i++){
                        DeleteB.asset_id = DeleteB.asset_idlist.get(i);
                        DeleteB.asset_removal();
                    }
                }
                DeleteB.asset_id = Integer.valueOf(v_asset_id);
                int status = DeleteB.del_asset();
                if (status == 1) { %>
                <h1>Delete Successful</h1>
            <%  } else {  %>
                <h1>Delete Unsuccessful</h1>
            <%  }  %>
            <input type="submit" value="Back to Main Menu">
        </form>
    </body>
</html>
