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
    <title>Thêm sản phẩm mới</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>
<div class="container" style="margin-top: 20px">
    <form method="post" style="padding-top: 50px">
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="productId">Product ID</label>
                <input type="text" name="productId" readonly class="form-control" id="productId" value="${product.getId()}">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="new_name">New Name</label>
                <input type="text" name="new_name" class="form-control" id="new_name" value="${product.getProductName()}">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="new_price">New Price</label>
                <input type="text" name="new_price" class="form-control" id="new_price" value="${product.getProductPrice()}">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="new_quantity">New Quantity</label>
                <input type="text" name="new_quantity" class="form-control" id="new_quantity" value="${product.getProductQuantity()}">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="new_color">New Color</label>
                <input type="text" name="new_color" class="form-control" id="new_color" value="${product.getProductColor()}">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="new_category_id">New Category ID</label>
                <input type="text" name="new_category_id" class="form-control" id="new_category_id" value="${product.getCategoryId()}">
            </div>
        </div>
        <div class="row">
            <button type="submit" class="btn btn-success">UPDATE</button>
            <a href="/productController" btn btn-success">Back to homepage</a>
        </div>
    </form>
</div>
</body>
</html>
