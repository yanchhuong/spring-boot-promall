<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Remember me with Spring MVC Framework</title>
</head>
<body>
<c:form method="post" commandName="account" action="login">
     <fieldset>
     	<legend>Login</legend>
     	${error}
        <table cellpadding="2" cellspacing="2">
           <tr>  
        		<td>Username: </td>
        		<td><c:input path ="username" /></td>
          </tr>
          <tr>  
        		<td>Password: </td>
        		<td><c:password path ="password" /></td>
          </tr>	
          <tr>  
        		<td>Remember me? </td>
        		<td><input type="checkbox" name="remember" value="1"/></td>
          </tr>	
          <tr>  
        		<td>&nbsp;</td>
        		<td><input type="submit" value="Login"/></td>
          </tr>	
        
        </table>
     </fieldset>
</c:form>
</body>
</html>