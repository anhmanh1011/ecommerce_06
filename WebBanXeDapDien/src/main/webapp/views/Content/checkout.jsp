<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="content" class="site-content" tabindex="-1">
    <div class="container">

        <nav class="woocommerce-breadcrumb"><a href="home.html">Home</a><span class="delimiter"><i
                class="fa fa-angle-right"></i></span>Checkout
        </nav>

        <div id="primary" class="content-area">
            <main id="main" class="site-main">
                <article class="page type-page status-publish hentry">
                    <header class="entry-header"><h1 itemprop="name" class="entry-title">Checkout</h1></header>
                    <!-- .entry-header -->

                    <form enctype="multipart/form-data"  class="checkout woocommerce-checkout" >
                        <div id="customer_details" class="col2-set">
                            <div class="col-1">
                                <div class="woocommerce-billing-fields">

                                    <h3>Billing Details</h3>

                                    <p id="billing_first_name_field"
                                       class="form-row form-row form-row-first validate-required"><label class=""
                                                                                                         for="billing_name">
                                        Name <abbr title="required" class="required">*</abbr></label><input type="text"
                                                                                                            value=""
                                                                                                            placeholder=""
                                                                                                            id="billing_name"
                                                                                                            name="billing_first_name"
                                                                                                            class="input-text ">
                                    </p>
                                    <p id="billing_phone_field" class="form-row form-row form-row-last validate-required validate-phone"><label class="" for="billing_phone">Phone <abbr title="required" class="required">*</abbr></label><input type="tel" value="${account.phoneNumber}" placeholder="" id="billing_phone" name="billing_phone"  class="input-text "></p><div class="clear"></div>
                                    <p id="billing_address_1_field" class="form-row form-row form-row-wide address-field validate-required"><label class="" for="billing_address">Address <abbr title="required" class="required">*</abbr></label><input type="text" value="" placeholder="Street address" id="billing_address" name="billing_address_1" class="input-text "></p>

                                    <div class="clear"></div>
                                </div>
                            </div>

                            <div class="col-2">
                                <h3>Shipping Details</h3>
                                <div class="woocommerce-shipping-fields">

                                    <p id="order_comments_field" class="form-row form-row notes"><label class=""
                                                                                                        for="order_comments">Order
                                        Notes</label><textarea cols="5" rows="2"
                                                               placeholder="Notes about your order, e.g. special notes for delivery."
                                                               id="order_comments" class="input-text "
                                                               name="order_comments"></textarea></p>
                                </div>
                            </div>
                        </div>

                        <h3 id="order_review_heading">Your order</h3>

                        <div class="woocommerce-checkout-review-order" id="order_review">
                            <table class="shop_table woocommerce-checkout-review-order-table">
                                <thead>
                                <tr>
                                    <th class="product-name">Product</th>
                                    <th class="product-total">Total</th>
                                </tr>
                                </thead>
                                <tbody id="showcart">
                                <c:set var="total" value="${0}"/>
                                <c:forEach items="${carts}" var="cart">
                                    <c:set var="total" value="${total + cart.productPrice * cart.quantity}"/>
                                    <tr class="cart_item">
                                        <td class="product-name">
                                                ${cart.productName}
                                            <strong class="product-quantity">× ${cart.quantity}</strong>
                                        </td>
                                        <td class="product-total">
                                            <span class="amount"><fmt:formatNumber
                                                    value="${cart.productPrice * cart.quantity}" type="currency"
                                                    pattern="###,###,### VND"/></span>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>

                                <tr class="cart-subtotal">
                                    <th>Subtotal</th>
                                    <td><span class="amount"><fmt:formatNumber value="${total}" type="currency"
                                                                               pattern="###,###,### VND"/></span></td>
                                </tr>

                                <tr class="order-total">
                                    <th>Total</th>
                                    <td><strong><span class="amount"><fmt:formatNumber value="${total + 300000}"
                                                                                       type="currency"
                                                                                       pattern="###,###,### VND"/></span></strong>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
                            <div class="woocommerce-checkout-payment" id="payment">
                                <div class="form-row place-order">
                                    <input id="submitCheckout" type="button" style="background-color: yellow;" data-value="Place order" value="Place order"
                                           class="button alt">
                                </div>
                            </div>

                        </div>
                    </form>
                </article>
            </main><!-- #main -->
        </div><!-- #primary -->
    </div><!-- .container -->
</div>
<!-- #content -->