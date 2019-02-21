<%@ page import="app.bean.Product" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="ecommerce-widget">

    <div class="row">
        <div class="container-fluid">
            <form id="formupdate">
                <input type="submit" id="updateSanPham" class="btn-danger" value="cập nhật"></input>

                <%--<input type="text" hidden name="id" value="${product.id}"><br>--%>
                <input type="text" hidden name="id" value="${product.id}"><br>

                name <input type="text" name="name" value="${product.name}"><br>
                price <input type="text" name="price" value="${product.price}"><br>
                <img id="mainImage" src="/assets/images/products/${product.mainPhoto}"><br>
                <input hidden name="mainPhoto" value="${product.mainPhoto}" >
                <input id="mainPhoto" type="file" >
                supplier <select name="supplier.id">
                <option value="1">HC Bike</option>
            </select>
                motor <input type="text" name="motor" value="${productDetail.motor}"/><br>
                operate <input type="text" name="operate" value="${productDetail.operate}"/><br>
                speed <input type="text" name="speed" value="${productDetail.speed}"/><br>
                safeWeight <input type="text" name="safeWeight" value="${productDetail.safeWeight}"/><br>
                weight <input type="text" name="weight" value="${productDetail.weight}"/><br>
                maximumDistance <input type="text" name="maximumDistance" value="${productDetail.maximumDistance}"/><br>
                chargingTime <input type="text" name="chargingTime" value="${productDetail.chargingTime}"/><br>
                typeOfBattery <input type="text" name="typeOfBattery" value="${productDetail.typeOfBattery}"/><br>
                tires <input type="text" name="tires" value="${productDetail.tires}"/><br>
                accessoriesIncluded <input type="text" name="accessoriesIncluded"
                                           value="${productDetail.accessoriesIncluded}"/><br>
                longs <input type="text" name="longs" value="${productDetail.longs}"/><br>
                wide <input type="text" name="wide" value="${productDetail.wide}"/><br>
                hight <input type="text" name="hight" value="${productDetail.hight}"/><br>
                warrantyBattery <input type="text" name="warrantyBattery" value="${productDetail.warrantyBattery}"/><br>
                warrantyMotor <input type="text" name="warrantyMotor" value="${productDetail.warrantyMotor}"/><br>
            </form>

                <c:forEach items="${product.productColors}" var="productsColors">
                    <div>
                        <span>${productsColors.color.value}</span>
                        <input type="number" value="${productsColors.amount}">
                        <button class="btn-info">delete</button>
                    </div>
                </c:forEach>
                <table>
                    <tbody>
                    <tr>
                        <c:forEach items="${product.images}" var="images">
                            <td>
                                <img src="/assets/images/products/${images.link}"><br>
                                <button class="btn-danger changeImage" data-id="${images.id}">thay đổi</button>
                                <input hidden class="fileImages" type="file" data-id="${images.id}" value="${images.link}"/>
                            </td>
                        </c:forEach>
                    </tr>
                    </tbody>
                </table>
        </div>
    </div>

</div>