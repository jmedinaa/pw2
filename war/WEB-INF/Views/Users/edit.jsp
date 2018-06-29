<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page import="model.entity.*" %>


<% List<Role> array = (List<Role>)request.getAttribute("roles"); %>
<% User user = (User) request.getAttribute("user"); %>
<% 

    SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
    String birthString = formato.format(user.getBirth());

 %>
<!DOCTYPE HTML>
<html>
<head>
	<title>lab08-20143379</title>
	<meta charset="utf-8">
	<meta name ="viewport" content="width-divice-width, initial-scale=1, shrink-to-fit-on">
	<meta http-equiv="x-ua-compatible" content="ie-edge">
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
	 <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    .row.content {
    	height: 450px}
   
    .sidenav {
      padding-top: 10px;
      background-color: #f1f1f1;
      height: 800px;
    }
   
    </style>
</head>

<body>
	<nav class="navbar navbar-light navbar-expand-md fixed-top" style="background-color: #e3f2fd;">
		<div class="container-fluid">
	  		<a class="navbar-brand" href="/index.html">
			    <img src="https://compucol.co/wp-content/uploads/compucolSGE-icon-large.png" width="30" height="30" class="d-inline-block align-top" alt="logo Sistema Academico">
			    Sistema Academico
		    </a>
			<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="	false" aria-label="Toggle navigation">
		   		  <span class="navbar-toggler-icon"></span>
		    </button>
			<div class="collapse navbar-collapse " id="navbarTogglerDemo01"> 
			    <div class="navbar-nav mr-auto  text-center">
				    <a class="nav-item nav-link" href="/roles">Roles </a>
				    <a class="nav-item nav-link" href="/users">Users</a>
				    <a class="nav-item nav-link" href="/resources">Resources</a>
				    <a class="nav-item nav-link" href="/access">Access</a>
				    <a class="nav-item nav-link" href="/citations">Citations</a>
			    </div>
				<div class="d-flex flex-row justify-content-center">
				    <a href="/users/login" class="btn btn-outline-primary mr-2">Login </a>
				    <a href="/users/logout" class="btn btn-outline-primary">Logout</a>
				</div>
			</div>	
		</div>		
	</nav>
	<br>
	<br>
	<div class="container-fluid text-center">    
	  <div class="row content">
	    <div class="col-sm-2 sidenav">
	       <div class="navbar-nav mr-auto  text-left">
	       	        <a class="nav-item nav-link text-muted" >Actions</a>
				    <a class="nav-item nav-link" href="/users">List Users</a>
			</div>
	    </div>
	    <div class="col-sm-10 text-left"> 
	    <br>
	      <h3 class="text-primary">User Edit</h3>
		  <form method="post"  action="/users/edit">
		   <div class="form-group">
			    <label>Id:</label>
			    <input type="text" class="form-control" readonly name="id"  value="<%=user.getId()%>">
		   </div>
		   <div class="form-group">
			   <label>Roles:</label>
			    <select class="form-control"  name="idRole" required>
			    <%if(array.size() > 0) {
					for(Role rol : array){ 
					if(rol.getId()==user.getIdRole()){%>
				      <option value="<%=rol.getId()%><%=rol.getName()%>" selected><%=rol.getName() %></option>
				      <%}
				   else{%>
					   <option value="<%=rol.getId()%><%=rol.getName()%>" ><%=rol.getName() %></option>
				<%  }}}%>
				</select>
		  </div>
		   <div class="form-group">
			    <label>Email:</label>
			    <input type="text" class="form-control" name="email"  placeholder="Email" value="<%=user.getEmail() %>" required >
		   </div>
		   <div class="form-group">
			    <label>Birth:</label>
			    <input type="date" class="form-control" name="birth" placeholder="Birth" value="<%=birthString %>"max="2050-05-24" required>
		   </div>
		   <div class="form-group">
			    <label>Gender:</label>
			    <%if(user.isGender()==true) {%>
			     <select class="form-control" name="gender">
				      <option value="true" selected>Male</option>
				      <option value="false">Female</option>
				</select>
				<%} 
				else{%>
				  <select class="form-control" name="gender">
				      <option value="true" >Male</option>
				      <option value="false"selected>Female</option>
				</select>
				<%} %>
		   </div>
		   <div class="form-group">
			    <label>Status:</label>
			    <%if(user.isStatus()==true) {%>
			     <select class="form-control" name="status">
				      <option value="true" selected>True</option>
				      <option value="false">False</option>
				</select>
				<%} 
				else{%>
				  <select class="form-control" name="status">
				      <option value="true" >True</option>
				      <option value="false"selected>False</option>
				</select>
				<%} %>
		   </div>
		   <button type="submit" class="btn btn-primary">Submit</button>
		  </form>
	    </div>
	  </div>
	</div>

	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!-- Popper JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</body>
</html>