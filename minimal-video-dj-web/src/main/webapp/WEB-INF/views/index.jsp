<%@page import="java.nio.file.Path"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="ex" uri="/WEB-INF/custom.tld"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="fileList" type="List<Path>" scope="request" />

<html>

<head>
<title>Video portal</title>
<meta name="viewport"
	content="width = device-width, initial-scale = 1.0">
<meta name="apple-mobile-web-app-capable" content="yes">

<!-- Bootstrap -->
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

<!--[if lt IE 9]>
      <script src = "https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src = "https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->

</head>
<body>
<div class="table-responsive">
  <table class="table">
				<c:forEach items="${fileList}" var="file">
				<tr><td align="center">
				<button class="btn btn-lg btn-primary" id="<ex:normalizeFileName fileName="${file.fileName}"/>"
					onclick="startVideo('${file.fileName}',
								'<ex:normalizeFileName fileName="${file.fileName}"/>',
								'<c:url value="/rest/start-video"/>?file=${file.fileName}')">
						<ex:filenameWithoutExtension fileName="${file.fileName}"/>
				</button>
				<span class="label label-info" id="<ex:normalizeFileName fileName="${file.fileName}"/>_message"></span>
				</td>
				</tr>
				</c:forEach>				
</table>
</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="<c:url value="/js/jquery.min.js"/>"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
	
	<!-- My JS file -->
	<script src="<c:url value="/js/minimal-video-dj.js"/>"></script>

</body>

</html>