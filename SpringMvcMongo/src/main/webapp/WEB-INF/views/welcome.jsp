<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Welcome</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	</head>
	<body>
		<div class="container" style="margin-top:5%;">
			<h2 id="article_header" class="text-warning" align="center">Libreria personale</h2>
	    	<div>&nbsp;</div>
	    	
	    	<!-- Div to add a new user to the mongo database -->
	    	<div id="add_new_user">
	    			<c:url var="addUrl" value="/book/add" /><a id="add" href="${addUrl}" class="btn btn-success"> + Libro</a>
	    	</div>
	    	<div>&nbsp;</div>
			
	    	<!-- Table to display the user list from the mongo database -->
	    	<table id="books_table" class="table">
	        	<thead>
	            	<tr align="center">
	            		<th>Id</th>
	            		<th>Titolo</th>
	            		<th>Autore</th>
	            		<th>Editore</th>
	            		<th>ISBN</th>
	            		<th colspan="5"></th>
	            	</tr>
	        	</thead>
	        	<tbody>
	            	<c:forEach items="${books}" var="book">
	                	<tr align="center">
	                    	<td><c:out value="${book.id}" /></td>
	                    	<td><c:out value="${book.title}" /></td>
	                    	<td><c:out value="${book.author}" /></td>
	                    	<td><c:out value="${book.editor}" /></td>
	                    	<td><c:out value="${book.isbn}" /></td>
	                    	<td>
	                        	<c:url var="editUrl" value="/book/edit?id=${book.id}" /><a id="update" href="${editUrl}" class="btn btn-warning">Modifica</a>
	                    	</td>
	                    	<td>
	                        	<c:url var="deleteUrl" value="/book/delete?id=${book.id}" /><a id="delete" href="${deleteUrl}" class="btn btn-danger">Elimina</a>
	                    	</td>
	                	</tr>
	            	</c:forEach>
	        	</tbody>
	    	</table>
		</div>	    
	</body>
</html>