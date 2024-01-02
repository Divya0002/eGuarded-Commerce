<%@page import="java.sql.Connection"%>
<%@page import="action.Db"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <body>
        <center><h1>Attacker Details</h1></center>
        <%
            if(request.getParameter("msg")!=null)
            {%>
        <center><h3>Recovery Successfully</h3></center>
         <%   }
        %>
            <%
                try {
                    Connection con = Db.getConnection();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select * from attacker");
                    while (rs.next()) {
            %>
        <fieldset style="width: 600px;margin-left: 200px;border: 0px transparent;font-size: 20px;">
            <label>Attacker Name</label><input type="text" value="<%=rs.getString("attackname")%>" readonly class="textbox" style="margin-left: 80px;background: transparent;border: 0px transparent;font-size: 18px"/><br /><br />
            <label>Product Name</label><input type="text" value="<%=rs.getString("pname")%>" readonly class="textbox" style="margin-left: 86px;background: transparent;border: 0px transparent;font-size: 18px;height: 30px"/><br /><br />
            <label>Status</label><input type="text" value="<%=rs.getString("status")%>" readonly class="textbox" style="margin-left: 155px;background: transparent;border: 0px transparent;font-size: 18px;height: 30px"/><br /><br />
            <label>Date & Time</label><input type="text" value="<%=rs.getString("rdate")%>" readonly class="textbox" style="margin-left:99px;border: 0px transparent;background: transparent;font-size: 18px;height: 30px;"/><br /><br />
            <label>View the Review </label><label style="margin-left: 60px"><a href="updatereview.jsp?<%=rs.getString("attackname")%>,<%=rs.getString("pcate")%>,<%=rs.getString("pname")%>,<%=rs.getString("uname")%>" style="text-decoration: none">View</a><br /><br />
                <label>View the Product</label><label style="margin-left: 65px"><a href="vrp.jsp?<%=rs.getString("pcate")%>,<%=rs.getString("pname")%>" style="text-decoration: none">View</a><br /><br />
                    <label>Recover the Review</label><label style="margin-left: 43px"><a href="recovery.jsp?<%=rs.getString("pcate")%>,<%=rs.getString("pname")%>,<%=rs.getString("uname")%>" style="text-decoration: none">Recover</a><br /><br />
                        </fieldset>
                        <hr></hr>
                        <%                }
                            } catch (Exception e) {
                                System.out.println("Exception Error in malicious " + e.getMessage());
                            }

                        %>
                        </body>
                        </html>
