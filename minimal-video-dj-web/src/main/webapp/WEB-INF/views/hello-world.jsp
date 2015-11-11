<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC -HelloWorld</title>
</head>
<body>
	<center>
		<h2>Hello World</h2>
		<h2>
			${message} ${name} <br>
			${fileList}
			
			<jsp:useBean id="fileList" type="java.util.ArrayList" scope="request" />
    <table border="1">
        <tr>
            <td>File name:</td>
        </tr>
        <c:forEach items="${fileList}" var="file">
                <td><a href="start?file=${file}">${file}</a></td> 
            </tr>
        </c:forEach>
    </table> 
    
    
		</h2>
	</center>
</body>
</html>
