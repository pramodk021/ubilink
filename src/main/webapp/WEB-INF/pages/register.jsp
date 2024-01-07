<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register here to access</title>
<link type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" />
<script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
<script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
</head>
<body>
 <div class="container">
  <div class="row">
   <div class="col-sm-9">
    <h2>Registration Page</h2>
    <div class="row">
     <div class="alert center-text" role="alert" id="alert"></div>
    </div>
    <form class="form-horizontal" role="form" method="post" name="userForm" id="userForm">
     <div class="form-group">
      <label for="firstName" class="col-lg-3 control-label">First Name</label>
      <div class="col-lg-9"><input type="text" class="form-control" name="firstName"
        id="firstName" placeholder="firstName" value="">
      </div>
     </div>
     <div class="form-group">
      <label for="lastName" class="col-lg-3 control-label">Last Name</label>
      <div class="col-lg-9">
       <input type="text" class="form-control" name="lastName" id="lastName" placeholder="lastName" value="">
      </div>
     </div>
     <div class="form-group">
      <label for="description" class="col-lg-3 control-label">Mobile</label>
      <div class="col-lg-9">
       <textarea class="form-control" name="mobile" id="mobile" placeholder="Mobile" cols="10" rows="5"></textarea>
      </div>
     </div>
    </form>
   </div>
  </div>
 </div>
</body>
 <script>
  $(document).ready($('.form-control').change(function() {
   $.ajax({
    type : "post",
    url : "/registration/register.htm",
    cache : false,
    data : $('#userForm').serialize(),
    success : function(response) {
     var obj = JSON.parse(response);
     $("#alert").text(JSON.stringify(obj));
     $("#alert").addClass("alert-success");
    },
    error : function() {
     alert('Error while request..');
    }
   });
  }));
 </script>
</html>
