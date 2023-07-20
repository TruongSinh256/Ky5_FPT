<%-- 
    Document   : comboDetails
    Created on : May 20, 2023, 5:45:51 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ComboDetail</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="../stylesheet/comboDetails.css">
        <link
            href="https://fonts.googleapis.com/css2?family=Alegreya&family=MuseoModerno:wght@200;300;400;500;600;800;900&family=Open+Sans:ital,wght@0,300;1,300&display=swap"
            rel="stylesheet">
        <link rel=”stylesheet” href=”https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css”> <script
        src=”https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js”></script> <script
        src=”https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js”></script> <script
        src=”https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js”></script> </head> <!-- Load an icon
        library -->

    <body>
        <jsp:include page="../components/Menu.jsp"></jsp:include>


        <section class="container sproduct my-2 pt-2">
            <div class="row mt-3 d-flex justify-content-center">
                <div class="col-lg-4 col-md-12 col-12">
                    <c:forEach items="${requestScope.listimg}" var="i">
                        <c:if test="${i.proID==requestScope.pro.proID}" >
                    <img class="img-fluid w-100 pb-1" id="MainImg"
                         src="../${i.proImg}" alt="">
                    </c:if>
                    </c:forEach>
                    <div class="small-img-group ">
                        <c:forEach items="${requestScope.listimg}" var="i">
                            <a href="comboDetail?comboID=${requestScope.comboId}&proID=${i.proID}&colorID=0&sizeID=0"> <div class="small-img-col ">
                                    <img src="../${i.proImg}" width="75%" class="small-img" alt="">
                                </div>
                            </a>
                            </c:forEach>
                    </div>
                </div>
                <div class="col-lg-4 col-md-12 col-12">
                    <c:set var= "p" value="${requestScope.pro}" />
                    <h3 class="py-4">${p.proName}</h3>

                    <h4 style="color:red"><b>${p.proPrice} VND</b></h4>
                    <hr />
                    <h6>(*Vui lòng chọn từ trên xuống dưới!)</h6>
                    &emsp;
                    <div class="row product-color flex align-items-center ">
                        <!-- form color -->


                        <h5 class="col-4">Color</h5>
                        <c:forEach items="${requestScope.list_color}" var="o"> 
                            <a href = "comboDetail?comboID=${requestScope.comboId}&proID=${requestScope.pro.proID}&colorID=${o.proColorID}&sizeID=0" 
                               class="col-3 px-0 mx-2 text-center" name="colorID" type="button" style="border-bottom: 1px solid;border-radius: 10px">
                                <button style="${o.proColorID==requestScope.coID?"color:white;background-color: black;border-radius: 10px;":"color:black;background-color: white;border-radius: 10px;"}" class="px-0 py-0">${o.proColorName}</button>
                            </a>                         
                        </c:forEach>

                    </div>
                    &nbsp;

                    <div class="row">
                        <h5 class="col-4 ">Size</h5>
                        <c:forEach items="${requestScope.list_stock}" var="o">
                            <a href="comboDetail?comboID=${requestScope.comboId}&proID=${requestScope.pro.proID}&colorID=${o.proColorID}&sizeID=${o.proSizeID}" type="button" id="size" class="col-1 px-0 py-0 mx-1 text-center" name="size"" value="${o.proSizeID}"> 
                                <button style="${o.proSizeID==requestScope.siID?"color:whitesmoke;background-color: black;":"color:black;background-color: whitesmoke;"}" class="px-0 py-0">${o.sizeName}</button>
                            </a>
                        </c:forEach>
                    </div>
                    &emsp;
                    <div class="row product-quantity flex align-items-center ">
                        <h5 class="col-4">Quantity</h5>
                        <input type="number" id="quantity" class="col-2 px-0 mx-0 text-center" name="quantity" value="1"
                               min="1" max="${requestScope.stock_quantity.quantity}">
                        <h6 class="col-6 text-center mt-1" style="display: ${requestScope.stock_quantity.quantity==0?"none":""}"> Còn ${requestScope.stock_quantity.quantity} sản phẩm</h6>

                    </div>
                    &emsp;
                    <button type="submit" class="buy-btn px-3 "><span class="fa fa-cart-plus"></span> Add To Cart</button>
                    <br> <br> 
                    <span style="padding-top: 20px">${requestScope.pro.proDetail}</span>
                </div>
            </div>

            <section id="featured" class="my-5 pb-5">
                <div class="container text-center mt-5 py-5">
                    <h3></h3>
                    <hr class="mx-auto">
                    <p></p>
                </div>
                <div class="row mx-auto container-fluid">
                    <c:forEach items="${requestScope.list_4_img}" var="i">

                        <div class="product text-center col-lg-3 col-md-4 col-12">
                            <a href="productDetail?proID=${i.proID}&colorID=0&sizeID=0"><img class="img-fluid mb-3 w-75"
                                                                                             src="../${i.proImg}" alt=""></a> 
                                <c:forEach items="${requestScope.list_4}" var="o">
                                <a href="productDetail?proID=${i.proID}&colorID=0&sizeID=0"> <h5>${o.proID==i.proID?o.proName:""}</a>
                                <h6 style="color: red;"><b>${o.proID==i.proID?o.proPrice:""} ${o.proID==i.proID?"VND":""}</b></h6>
                            </c:forEach>
                        </div>

                    </c:forEach>
                </div>
            </section>
        </section>

        


        <jsp:include page="../components/Footer.jsp"></jsp:include>
        <script>
            var MainImg = document.getElementById('MainImg');
            var smallImg = document.getElementsByClassName('small-img');

            smallImg[0].onclick = function () {
                MainImg.src = smallImg[0].src;
            }
            smallImg[1].onclick = function () {
                MainImg.src = smallImg[1].src;
            }
            smallImg[2].onclick = function () {
                MainImg.src = smallImg[2].src;
            }
            smallImg[3].onclick = function () {
                MainImg.src = smallImg[3].src;
            }


        </script>
        <script>
            function submitForm() {
                document.getElementById('myform').submit();
            }
        </script>
    </body>
