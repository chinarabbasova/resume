<%@ page import="dao.inter.UserDaoInter" %>
<%@ page import="main.Context" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>All Users</title>
    <link rel="stylesheet" href="assets/css/users.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <%--    <link rel="stylesheet" href="css/bootstrap.css"/>--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <%--    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">--%>
    <script type="text/javascript" src="assets/js/users.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

</head>
<body>

<%
    User user =(User) session.getAttribute("loggedInUser");
%>

<%="   Welcome " + user.getName()%>
<%
    UserDaoInter userDao = Context.instanceUserDao();
    String idStr = request.getParameter("id");
    Integer id = null;
    if (idStr != null && !(idStr.trim().isEmpty())) {
        id = Integer.parseInt(idStr);}
    String name = request.getParameter("name");
    String surname = request.getParameter("surname");
    String nationalityIdStr = request.getParameter("nId");
    Integer nationalityId = null;
    if (nationalityIdStr != null && !(nationalityIdStr.trim().isEmpty())) {
        nationalityId = Integer.parseInt(nationalityIdStr);
    }
    String address = request.getParameter("address");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String profileDescription = request.getParameter("profileDescription");
    List<User> list = userDao.getAllUser(name, surname, nationalityId, address, email, phone, profileDescription);
%>

<div style="width: 5px">
    <form action="logout" method="POST" >
        <button type="submit" class="btn btn-info" name="logout" >Logout</button>
    </form>
</div>
<div style="width: 5px">
    <form action="addUser" method="GET">
        <input type="hidden" name="id" value="0"/>
        <input type="hidden" name="action" value="add"/>
        <input class="btn btn-secondary" type="submit" name="action" value="add"/>
    </form>
</div>
<div class="container">
    <div class="row">
        <div class="col-5">
            <form action="users" method="GET">
                <div class="form-group">
                    <label for="name"> name:</label>
                    <input onkeyup="writeWhatIamTyping()"
                           placeholder="Enter name" class="form-control" type="text" name="name" value=""/>
                </div>
                <div class="form-group">
                    <label for="surname">surname: </label>
                    <input placeholder="Enter surname" class="form-control" type="text" name="surname" value=""/>
                </div>
                <input class=" btn btn-primary" type="submit" name="search" value="Search" id="btnSearch"/>
            </form>
        </div>
    </div>
    <hr/>
    <div>

        <table class="table">
            <thead>
            <th> Name</th>
            <th> Surname</th>
            <th> Nationality</th>
            <th> Address</th>
            <th> Email</th>
            <th> Phone</th>
            <th> Profile Description</th>
            <th> Operations</th>
            <th></th>

            </thead>
            <tbody>
            <%
                for (User u : list) {
            %>
            <tr>
                <td><%=u.getName()%>
                </td>
                <td><%=u.getSurname()%>
                </td>
                <td><%=u.getNationality().getName() == null ? "N/A" : u.getNationality().getName()%>
                </td>
                <td><%=u.getAddress()%>
                </td>
                <td><%=u.getEmail()%>
                </td>
                <td><%=u.getPhone()%>
                </td>
                <td><%=u.getProfileDescription()%>
                </td>
                <td style="width: 5px">

                    <input type="hidden" name="id" value="<%=u.getId()%>"/>
                    <input type="hidden" name="action" value="delete"/>
                    <input class="btn btn-danger" type="submit" name="action" value="delete"
                           data-toggle="modal" data-target="#exampleModal"
                    onclick="setIdForDelete(<%=u.getId()%>)"/>
                </td>
                <td style="width: 5px">
                    <form action="userdetails" method="GET">
                        <input type="hidden" name="id" value="<%=u.getId()%>"/>
                        <input type="hidden" name="action" value="update"/>
                        <input class="btn btn-secondary" type="submit" name="action" value="update"/>
                    </form>
                </td>
            </tr>
            <%} %>
            </tbody>
        </table>
    </div>

</div>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure?
            </div>
            <div class="modal-footer">
                <form action="userdetails" method="POST">
                    <input type="hidden" name="id" value="" id="idforDelete"/>
                    <input type="hidden" name="action" value="delete"/>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-danger" value="Delete">
                </form>
            </div>
        </div>
    </div>
</div>
<br/>
<%--<a href="hello-servlet">Hello Servlet</a>--%>
</body>
</html>