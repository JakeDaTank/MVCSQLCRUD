<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="style.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SongInfo</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty song}">
			<h3>Song added</h3>

			<table>
				<tr>
					<th>Artist</th>
					<th>Song Title</th>
				</tr>
				<tr>
					<td><c:out value="${song.artistTitle}" /></td>
					<td><c:out value="${song.title}" /></td>

				</tr>
			</table>
		</c:when>
		<c:otherwise>
			<p>No new song was added</p>
		</c:otherwise>
	</c:choose>
	<br> <h3>Current List of songs</h3>
	<br>
	<table>
		<tr>
			<th>Artist</th>
			<th>Song Title</th>
		</tr>
		<c:forEach items="${album}" var="song">
			<tr>
				<td><c:out value="${song.artistTitle}" /></td>
				<br>
				<td><c:out value="${song.title}" /></td>

			</tr>
		</c:forEach>
	</table>

</body>
</html>