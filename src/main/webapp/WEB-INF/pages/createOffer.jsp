<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Offer here</title>
</head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
   
    $(document).ready(function() {
      
      $('#createOrderForm').submit(function(event) {
    	  
		  var placeId = $('#txtPlaceId').val();
		  
		  var userId = $('#txtuserId').val();
    	  var categoryId = $('#txtCategoryId').val();
    	  var productName = $('#txtProductName').val();
    	  alert("productName"+productName.toString());
    	  var offerName = $('#txtOfferName').val();
    	  alert("offerName"+offerName.toString());
		  var description = $('#txtdescription').val();
		  alert("description"+description.toString());
		  var validity = $('#txtValidity').val();
		  alert("validity"+validity.toString());
		  var validFrom = $('#txtValidFrom').val();
    	  var json = { "placeId" : placeId, "userId" : userId, "categoryId" : categoryId, "productName" : productName, "offerName": offerName,"description":description,"validity":validity,"validFrom":validFrom};
		  alert(json.toString());
        $.ajax({
        	url: $("#createOrderForm").attr( "action"),
        	data: JSON.stringify(json),
        	type: "POST",
        	
        	beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        	},
        	success: function(smartphone) {
        		var respContent = "";
        		
		  		respContent += "<span class='success'>Offer was created: [";
		  		"]</span>";
        		
        		$("#sOfferFromResponse").html(json);   		
        	}
        });
         
        event.preventDefault();
      });
       
    });   
  </script>
</head>
<body>
<div id="container">
<h1>Create a Offer</h1>
<p>Here you can create new Offer:</p>
<div id="sOfferFromResponse"></div>
</div>
</div>
	<td><input type="hidden" id="txtPlaceId"></td>
	<td><input type="hidden" id="txtuserId"></td>
	<form id="createOrderForm" action="/ubilink/requestOffers/createOffer">
	<table border="1px" cellpadding="0" cellspacing="0">
	<thead>
	<tr>
	<th>Category</th><th>Product</th><th>Offer Name</th><th> Description</th><th>Validity</th><th>ValidFrom</th><th>Actions</th>
	</tr>
	</thead>
	<tbody>
	<tr>
	<td>
	<select id="txtCategoryId">
	<option value="1">Others<option>
	<option value="2">Fashion<option>
	<option value="3">Food<option>
	<option value="4">Electronics<option>
	<option value="5">Apparels<option>
	<option value="5">Jewellery<option>
	<select></td>	
	<td><input  id="txtProductName"/></td>
	<td><input  id="txtOfferName"/></td>	
	<td><input  id="txtdescription"/></td>
	<td><input  id="txtValidity"/></td>	
	<td><input  id="txtValidFrom"/></td>
	<td>
	<a href="Edit">Edit</a><br/>
	<a href="Delete">Delete</a><br/>
	</td>
	</tr>
	</tbody>
	</table>
<input type="submit" value="Create" />

<a href="/ubilink/pages/index.jsp">Home page</a>
</div>
</form>
</body>
</html>