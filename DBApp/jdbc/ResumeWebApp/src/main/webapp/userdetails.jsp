<%@ page import="entity.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="assets/css/users.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <%--    <link rel="stylesheet" href="css/bootstrap.css"/>--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

<%
    User u=(User) request.getAttribute("user");

%>
<div class="container">
<div class="row">
    <form action="userdetails" method="POST">
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="id" value="<%=u.getId()%>"/>
        <label class="col-sm-4 col-form-label"> name:</label>
        <input type="text" name="name" value="<%=u.getName()%>"/>
        <br/>
        <label class="col-sm-4 col-form-label">surname: </label>
        <input type="text" name="surname" value="<%=u.getSurname()%>"/>
        <br/>
        <label class="col-sm-4 col-form-label">nationality id: </label>
        <input type="text" name="nationalityId" value="<%=u.getNationality().getId()%>"/>
        <br/>
        <label class="col-sm-4 col-form-label">address : </label>
        <input type="text" name="address" value="<%=u.getAddress()%>"/>
        <br/>
        <label class="col-sm-4 col-form-label">email : </label>
        <input type="text" name="email" value="<%=u.getEmail()%>"/>
        <br/>
        <label class="col-sm-4 col-form-label">phone : </label>
        <input type="text" name="phone" value="<%=u.getPhone()%>"/>
        <br/>
        <label class="col-sm-4 col-form-label" >profile description : </label>
        <input type="text" name="profileDescription" value="<%=u.getProfileDescription()%>"/>
        <input type="submit" name="save" class="btn-primary" value="Save"/>
    </form>
<%--    <form action="userdetails" method="POST">--%>
<%--        <input type="hidden" name="action" value="add"/>--%>
<%--        <input type="hidden" name="id" value="<%=u.getId()%>"/>--%>
<%--        <label class="col-sm-4 col-form-label"> name:</label>--%>
<%--        <input type="text" name="name" value="<%=u.getName()%>"/>--%>
<%--        <br/>--%>
<%--        <label class="col-sm-4 col-form-label">surname: </label>--%>
<%--        <input type="text" name="surname" value="<%=u.getSurname()%>"/>--%>
<%--        <br/>--%>
<%--        <label class="col-sm-4 col-form-label">phone : </label>--%>
<%--        <input type="text" name="phone" value="<%=u.getPhone()%>"/>--%>
<%--        <br/>--%>
<%--        <label class="col-sm-4 col-form-label">email : </label>--%>
<%--        <input type="text" name="email" value="<%=u.getEmail()%>"/>--%>
<%--        <br/>--%>
<%--        <label  class="col-sm-4 col-form-label">password : </label>--%>
<%--        <input type="text" name="password" value="<%=u.getPassword()%>">--%>
<%--&lt;%&ndash;        <label class="col-sm-4 col-form-label">nationality id: </label>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <input type="text" name="nationalityId" value="<%=u.getNationality().getId()%>"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <br/>&ndash;%&gt;--%>
<%--        <label class="col-sm-4 col-form-label" >profile description : </label>--%>
<%--        <input type="text" name="profileDescription" value="<%=u.getProfileDescription()%>"/>--%>
<%--        <label class="col-sm-4 col-form-label">address : </label>--%>
<%--        <input type="text" name="address" value="<%=u.getAddress()%>"/>--%>
<%--        <br/>--%>
<%--        <input type="submit" name="save" class="btn-primary" value="Save"/>--%>
<%--    </form>--%>
</div>
</div>
<br/>
</body>
</html>