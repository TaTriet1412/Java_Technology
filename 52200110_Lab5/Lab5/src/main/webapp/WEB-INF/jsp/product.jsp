<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Product List</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #f8f8f8">

<div class="container-fluid p-5">
    <div class="row mb-5">
        <div class="col-md-6">
            <h3>Product Management</h3>
        </div>
        <div class="col-md-6 text-right">
            Hello <span class="text-danger">Username</span> | <a href="#">Logout</a>
        </div>
    </div>
    <div class="row rounded border p-3">
        <div class="col-md-4">
            <h4 class="text-success">Add new product</h4>
            <form class="mt-3" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="product-name">Product's Name</label>
                    <input class="form-control" name="name" type="text" placeholder="Input product's name" id="product-name" >
                </div>
                <div class="form-group">
                    <label for="price">Product's price</label>
                    <input class="form-control" name="price" type="number" placeholder="Input product's price" id="price" >
                </div>
                <div class="form-group">
                    <button class="btn btn-success mr-2">Add Product</button>
                </div>


                <%@ page contentType="text/html;charset=UTF-8" language="java" %>
                <%
                    String sessionErrMsg = (String) session.getAttribute("sessionErrMsg");
                    if (sessionErrMsg != null) {
                        out.println("<div class=\"alert alert-danger\">");
                        out.println(sessionErrMsg);
                        out.println("</div>");
                    }
                %>
            </form>
        </div>
        <div class="col-md-8">
            <h4 class="text-success">Product List</h4>
            <p>Choose one product to see detail of it</p>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Product's name'</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <c:if test="${not empty sessionScope.productList}">
                <c:forEach var="product" items="${sessionScope.productList}">
                    <tr>
                        <td>${product.id}</td>
                        <td><a href="#">${product.name}</a></td>
                        <td>$ ${product.price}</td>
                        <td>
                            <a href="#">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </c:if>

                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    // Add the following code if you want the name of the file appear on select
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
</script>
</body>
</html>
