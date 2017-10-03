<%-- 
    Document   : login.jsp
    Created on : Sep 19, 2017, 10:11:46 AM
    Author     : 643507
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  
       <%--<c:import url="/includes/header.html"/>--%>
      
       <h1>Login</h1>
        <div>
            <form action="LoginServlet" method="post">
                Username: <input type="text" name="username" value="${username}" ><br>
               Password: <input type="password" name="password" value="${password}" ><br>
                <input type="submit" value="login">
               Remember Me <input type="checkbox" name="check" value="checked"${checked}><br>
            </form>
            
        </div>
${errormessage}
</body><%--<c:import url="/includes/footer.html" />--%>

