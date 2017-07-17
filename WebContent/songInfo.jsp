<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SongInfo</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty song}">
			
		</c:when>
		<c:otherwise>
			<p>No song found</p>
		</c:otherwise>
	</c:choose>
	
	
</body>
</html>