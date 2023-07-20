<%-- 
    Document   : Admin
    Created on : May 20, 2023, 3:44:21 PM
    Author     : dinhd513
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/admin.css">

        <title>Admin</title>
    </head>
    <body> 
        <!-- SIDEBAR -->    
        <jsp:include page="../components/MenuBar_Manage.jsp">
            <jsp:param name="active1" value="active"/>
            <jsp:param name="hrefAdmin" value="../view/AdminDashboard?table=product"/>
            <jsp:param name="hrefShopInfo" value="../view/ShopInfomation"/>
             <jsp:param name="hrefOrder" value="../view/managerorder?table=all&filter=new&page=1"/>
        </jsp:include>
        <!-- SIDEBAR -->



        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu' ></i>
                <a href="#" class="nav-link">Categories</a>

                <input type="checkbox" id="switch-mode" hidden>


            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>
                <div class="head-title">
                    <div class="left">
                        <h1>Admin Dashboard</h1>
                    </div>
                </div>

                <ul class="box-info">
                    <li>
                        <i class='bx bxs-calendar-check' ></i>
                        <span class="text">
                            <h3>${requestScope.totalProduct}</h3>
                            <p>Product</p>
                        </span>
                    </li>
                    <li>
                        <i class='bx bxs-group' ></i>
                        <span class="text">
                            <h3>${requestScope.totalOrder}</h3>
                            <p>Order</p>
                        </span>
                    </li>
                    <li>
                        <i class='bx bxs-dollar-circle' ></i>
                        <span class="text">
                            <h3>${requestScope.totalRevenue} VND</h3>
                            <p>Revenue</p>
                        </span>
                    </li>
                </ul>


                <div class="table-data">

                    <div class="order">  
                        <div class="head">
                            <h3><c:if test="${requestScope.listProduct != null}">New Product</c:if>
                                <c:if test="${requestScope.listStock != null}">New Stock</c:if>
                                </h3>
                                <a style="color: black; <c:if test="${requestScope.listProduct != null}">border-bottom: 2px solid black</c:if>" href="../view/AdminDashboard?table=product">New Product</a>
                            <a style="color: black; <c:if test="${requestScope.listStock != null}">border-bottom: 2px solid black</c:if>" href="../view/AdminDashboard?table=stock">New Stock</a>
                            </div> 

                            <table style="display: <c:if test="${requestScope.listStock != null}">none</c:if>">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Status</th>

                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.listProduct}" var="o">
                                    <tr>
                                        <td>${o.proID}</td>
                                        <td style="display: flex;">
                                            <c:forEach items="${requestScope.list_img}" var="i">
                                                <c:if test="${o.proID==i.proID}">  
                                                    <img src="../${i.proImg}">
                                                </c:if>  
                                            </c:forEach> 

                                            <p>${o.proName}</p>

                                        </td>



                                        <td>${o.proPrice}</td>
                                        <c:if test="${o.proStatus == true}">
                                            <td><span class="status completed">On sale</span></td>
                                        </c:if>
                                        <c:if test="${o.proStatus != true}">
                                            <td><span class="status pending">not sold</span></td>
                                        </c:if>     


                                    </tr>
                                </c:forEach>

                            </tbody>

                        </table>



                            <table style="display: <c:if test="${requestScope.listProduct != null}">none</c:if>">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Name</th>
                                        <th>Color</th>
                                        <th>Size</th>
                                        <th>Quantity</th>
                                        <th>Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.listStock}" var="o">
                                    <tr>
                                        <td>${o.stockID}</td>
                                        <td style="display: flex;">
                                            <c:forEach items="${requestScope.list_img}" var="i">
                                                <c:if test="${o.proID==i.proID}">  
                                                    <img src="../${i.proImg}">

                                                </c:if>  
                                            </c:forEach> 

                                            <c:forEach items="${requestScope.list_Pro}" var="e">
                                                <c:if test="${o.proID==e.proID}">  
                                                    <p>${e.proName}</p>

                                                </c:if>  
                                            </c:forEach> 


                                        </td>
                                        <td>
                                            <c:forEach items="${requestScope.list_Color}" var="j">
                                                <c:if test="${o.proColorID == j.proColorID}">  
                                                    ${j.proColorName}
                                                </c:if>  
                                            </c:forEach> 
                                        </td>
                                        <td>
                                            <c:forEach items="${requestScope.list_Size}" var="k">
                                                <c:if test="${o.proSizeID == k.sizeID}">  
                                                    ${k.sizeName}
                                                </c:if>  
                                            </c:forEach>        
                                        </td>
                                        <td>${o.quantity}</td>
                                        <c:if test="${o.stockStatus == true}">
                                            <td><span class="status completed">In stock</span></td>
                                        </c:if>
                                        <c:if test="${o.stockStatus != true}">
                                            <td><span class="status pending">Out of stock</span></td>
                                        </c:if>     

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                    </div>




                    <div class="order">
                        <div class="head">
                            <h3>New Order</h3> 
                        </div>
                        <ul class="todo-list">
     
                                <table>
                                    <tr>
                                        <th>ID</th>
                                        <th>Date</th>
                                        <th>Gmail</th>
                                        <th>Total_Money</th>         
                                        <th>Status</th> 
                                    </tr>
                                <c:forEach items="${requestScope.listOrder}" var="o">

                                        <tr>
                                            <td style="font-weight: bold;">${o.orID} |</td>                            
                                            <td>${o.dob}</td> 
                                            <td>${o.gmail}</td>
                                            <td>${o.totalMoney} VND </td>
                                            
                                                <td><span class="status completed">Pending</span></td>
                                          
                                        </tr>

                                    </c:forEach>
                                </table>   

                            </ul>
                        </div>
                    </div>
                </main>
                <!-- MAIN -->
            </section>
            <!-- CONTENT -->


            <script src="../js/MaketingPage.js"></script>
        </body>
    </html>

