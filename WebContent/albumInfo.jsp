<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AlbumInfo</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty album}">
			<ul>
				<c:forEach items="${album.listOfSongs}" var="song">
				<li>song.title</li>
				</c:forEach>
				<li>${album.bandname}</li>
				<li>${album.bandName}</li>
				<li>${album.albumName}</li>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No state found</p>
		</c:otherwise>
	</c:choose>
	<form action="GetButton.do" method="GET">
		<input type="submit" class="button" name="prev" value="prev">

		<input type="submit" class="button" name="next" value="next">
	</form>
</body>
</html>