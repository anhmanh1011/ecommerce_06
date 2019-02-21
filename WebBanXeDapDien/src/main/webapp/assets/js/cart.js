jQuery(document).ready(function () {

    jQuery(document).on('change', '.soLuong', function () {
        var quantity = jQuery(this).val();
        var parent = jQuery(this).parent().parent().parent();
        var id = parent.attr('data-id');
        var color = parent.attr('data-color');
        var price = parent.attr('data-price');

        jQuery.ajax({
            url: '/cart/update',
            type: 'post',
            data: {
                productColor: color,
                productId: id,
                quantity: quantity
            },
            success: function (data) {
                if (data == true) {
                    console.log(quantity + ' ' + id + ' ' + color);
                    var total = parent.find('span[data-title=total]');
                    total.text((price * quantity).toFixed(0).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + ' VND');
                } else {
                    console.log("thay doi khong thanh cong");
                }
            },
            dataType: "json"

        })
    });

    jQuery(document).on('click', '.xoaGioHang', function () {

        var parent = jQuery(this).parent().parent();
        var id = parent.attr('data-id');
        var color = parent.attr('data-color');

        jQuery.ajax({
            url: '/cart/delete',
            type: 'post',
            data: {
                productId: id,
                productColor: color,
            },
            success: function (data) {
                if (data == true) {
                    parent.remove();
                } else {
                    console.log("xoa khong thanh cong")
                }
            },
            dataType: "json"

        })

    });

    jQuery("#form_cart").submit(function (e) {
        e.preventDefault();
    });

    jQuery('#btn-submit-cart').click(function (e) {
        e.preventDefault();
        var color = jQuery("#color option:selected").val();
        var productId = jQuery("input[name=product_id]").val();
        var quantity = jQuery("input[name=quantity]").val();
        var urlImage = jQuery("#mainImage").attr("data-images");
        var productPrice = jQuery("#amount").attr("data-value");

        var productName = jQuery("#productName").text();

        console.log(color + ' ' + productId + ' ' + quantity + ' ' + urlImage + ' ' + productPrice + ' ' + productName);

        jQuery.ajax({

            url: '/cart/create',
            type: 'post',
            data: {
                productColor: color,
                productId: productId,
                quantity: quantity,
                urlImage: urlImage,
                productPrice: productPrice,
                productName: productName
            },
            success: function (data) {
                jQuery('.mini_cart_item').remove();
                if (data != null) {
                    jQuery('#countProduct').text(data.length);
                    for (var i = 0; i < data.length; i++) {
                        var value = parseInt(data[i].productPrice);
                        var price = value.toFixed(0).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + ' VND';
                        jQuery('.cart_list').append('<li ' + 'data-id="' + data[i].productId + '" class="mini_cart_item" ' + 'data-color="' + data[i].productColor + '"' + '><a id="abc" title="Remove this item" style="cursor: pointer" class="remove" >×</a><a href="single-product.html"><img class="attachment-shop_thumbnail size-shop_thumbnail wp-post-image"src="/assets/images/products/' + data[i].urlImage + '" alt="">' + data[i].productName + '&nbsp;</a><span class="quantity">' + data[i].quantity + ' ' + data[i].productColor + '× <span class="amount">' + price + '</span></span></li>');
                    }
                }


            },
            dataType: "json"
        })
    });

    jQuery(document).on('click', '.remove', function () {
        var elment = jQuery(this).parent();
        var productId = elment.attr('data-id');
        var productColor = elment.attr('data-color');

        jQuery.ajax({
            url: '/cart/delete',
            type: 'post',
            data: {
                productColor: productColor,
                productId: productId,
            },
            success: function (data) {
                if (data == true) {
                    var count = jQuery('#countProduct').text();
                    count -= 1;
                    jQuery('#countProduct').text(count);
                    elment.hide();
                } else {
                    console.log("xoa khong thanh cong")
                }
            },
            dataType: "json"

        })

    });

    jQuery('#submitCheckout').click(function (e) {
        e.preventDefault();
        var adress = jQuery('#billing_address').val();
        var name = jQuery('#billing_name').val();
        var phone = jQuery('#billing_phone').val();
        var comments = jQuery('#order_comments').val();

        jQuery.ajax({
            url: '/orders/create',
            type: 'post',
            data: {
                customerName: name,
                customerAdress: adress,
                customerPhoneNumber: phone,
                customerNote: comments
            },
            success: function (data) {
                if (data == true) {
                    alert("Đã thêm hóa đơn thành công")
                    window.location = "/";
                } else {

                    alert("Thêm hóa đơn thất bại ")
                }
            },
            dataType: "json"
        })

    });

    function loadCart() {
        jQuery.ajax({
            url: '/cart',
            type: 'get',
            success: function (data) {
                jQuery('.mini_cart_item').remove();
                if (data != null) {
                    jQuery('#countProduct').text(data.length);
                    for (var i = 0; i < data.length; i++) {
                        var value = parseInt(data[i].productPrice);
                        var price = value.toFixed(0).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") + ' VND';
                        jQuery('.cart_list').append('<li ' + 'data-id="' + data[i].productId + '" class="mini_cart_item" ' + 'data-color="' + data[i].productColor + '"' + '><a id="abc" title="Remove this item" style="cursor: pointer" class="remove" >×</a><a href="single-product.html"><img class="attachment-shop_thumbnail size-shop_thumbnail wp-post-image"src="/assets/images/products/' + data[i].urlImage + '" alt="">' + data[i].productName + '&nbsp;</a><span class="quantity">' + data[i].quantity + ' ' + data[i].productColor + '× <span class="amount">' + price + '</span></span></li>');
                    }
                } else {
                    console.log("cart trong")
                }
            },
            dataType: "json"
        })

    }

    loadCart();

    function loadSuppilers() {
        jQuery.ajax({
            url: '/supplier',
            type: 'get',
            success: function (data) {
                if (data != null) {
                    for (var i = 0; i < data.length; i++) {
                        jQuery('#product_cat').append('<option class="level-0" value="' + data[i].id + '">' + data[i].name + '</option>');
                    }
                } else {
                    console.log("khong co supplier")
                }


            },
            dataType: "json"
        })

    };
    loadSuppilers();

});