<%--
  Created by IntelliJ IDEA.
  User: minhle
  Date: 3/21/21
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm sản phẩm</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>
<div class="container" style="margin-top: 20px">
    <form method="post" style="padding-top: 50px">
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="inputName">Product Name</label>
                <input type="text" name="name" class="form-control" id="inputName" placeholder="Name">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="inputPrice">Product Price</label>
                <input type="text" name="price" class="form-control" id="inputPrice" placeholder="Price">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="inputQuantity">Product Quantity</label>
                <input type="text" name="quantity" class="form-control" id="inputQuantity" placeholder="Quantity">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="inputColor">Product Color</label>
                <input type="text" name="color" class="form-control" id="inputColor" placeholder="Color">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="inputCategoryId">Category ID</label>
                <input type="text" name="categoryid" class="form-control" id="inputCategoryId" placeholder="CategoryId (1 for Phone, 2 for Laptop)">
            </div>
        </div>
        <button type="submit" class="btn btn-success">ADD</button>
    </form>
</div>
</body>
</html>
