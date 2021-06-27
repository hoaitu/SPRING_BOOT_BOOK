(function($) {
  "use strict";
  /*----------------------------
 wow js active
------------------------------ */
  new WOW().init();

  /*----------------------------
 owl active
------------------------------ */

  $(".new-trend-list").owlCarousel({
    autoPlay: false,
    slideSpeed: 2000,
    pagination: false,
    navigation: true,
    items: 3 /* [This code for animation ] */,
    /* transitionStyle : "fade", */ navigationText: [
      "<i class='fa fa-angle-left'></i>",
      "<i class='fa fa-angle-right'></i>"
    ],
    itemsDesktop: [1199, 3],
    itemsDesktopSmall: [991, 3],
    itemsTablet: [767, 2],
    itemsMobile: [479, 1]
  });
  /*----------------------------
 Featured Product List owl active
------------------------------ */

  $(".featured-product-list").owlCarousel({
    autoPlay: false,
    slideSpeed: 2000,
    pagination: false,
    navigation: true,
    items: 4 /* [This code for animation ] */,
    /* transitionStyle : "fade", */ navigationText: [
      "<i class='fa fa-angle-left'></i>",
      "<i class='fa fa-angle-right'></i>"
    ],
    itemsDesktop: [1199, 3],
    itemsDesktopSmall: [991, 3],
    itemsTablet: [767, 2],
    itemsMobile: [479, 1]
  });
  /*----------------------------
 Featured Product List owl active
------------------------------ */

  $(".related-product").owlCarousel({
    autoPlay: false,
    slideSpeed: 2000,
    pagination: false,
    navigation: true,
    items: 4 /* [This code for animation ] */,
    /* transitionStyle : "fade", */ navigationText: [
      "<i class='fa fa-angle-left'></i>",
      "<i class='fa fa-angle-right'></i>"
    ],
    itemsDesktop: [1199, 3],
    itemsDesktopSmall: [991, 3],
    itemsTablet: [767, 2],
    itemsMobile: [479, 1]
  });

  /*----------------------------
Testimonial list owl active
------------------------------ */

  $(".testimonial-list").owlCarousel({
    autoPlay: false,
    slideSpeed: 2000,
    pagination: false,
    navigation: true,
    items: 1 /* [This code for animation ] */,
    /* transitionStyle : "fade", */ navigationText: [
      "<i class='flaticon-arrows'></i>",
      "<i class='flaticon-arrows-1'></i>"
    ],
    itemsDesktop: [1199, 1],
    itemsDesktopSmall: [991, 1],
    itemsTablet: [767, 1],
    itemsMobile: [479, 1]
  });

  /*----------------------------
Blog list owl active
------------------------------ */

  $(".blog-list").owlCarousel({
    autoPlay: false,
    slideSpeed: 2000,
    pagination: false,
    navigation: true,
    items: 4 /* [This code for animation ] */,
    /* transitionStyle : "fade", */ navigationText: [
      "<i class='fa fa-angle-left'></i>",
      "<i class='fa fa-angle-right'></i>"
    ],
    itemsDesktop: [1199, 4],
    itemsDesktopSmall: [991, 3],
    itemsTablet: [767, 2],
    itemsMobile: [479, 1]
  });
  /*----------------------------
Blog list owl active
------------------------------ */

  $(".team-list").owlCarousel({
    autoPlay: false,
    slideSpeed: 2000,
    pagination: false,
    navigation: true,
    items: 4 /* [This code for animation ] */,
    /* transitionStyle : "fade", */ navigationText: [
      "<i class='fa fa-angle-left'></i>",
      "<i class='fa fa-angle-right'></i>"
    ],
    itemsDesktop: [1199, 4],
    itemsDesktopSmall: [991, 3],
    itemsTablet: [767, 2],
    itemsMobile: [479, 1]
  });

  /*----------------------------
 Banner List owl active
------------------------------ */

  $(".banner-listjkdl").owlCarousel({
    autoPlay: true,
    slideSpeed: 2000,
    pagination: false,
    navigation: false,
    items: 3 /* [This code for animation ] */,
    /* transitionStyle : "fade", */ itemsDesktop: [1199, 3],
    itemsDesktopSmall: [991, 3],
    itemsTablet: [767, 2],
    itemsMobile: [479, 1]
  });
  /*---------------------
 2. CounterUp
--------------------- */

  $(".counter").counterUp({
    delay: 50,
    time: 3000
  });

  /*---------------------
 countdown
--------------------- */
  $("[data-countdown]").each(function() {
    var $this = $(this),
      finalDate = $(this).data("countdown");
    $this.countdown(finalDate, function(event) {
      $this.html(
        event.strftime(
          '<span class="cdown days"><span class="time-count">%-D</span> <p>Days</p></span> <span class="cdown hour"><span class="time-count">%-H</span> <p>Hour</p></span> <span class="cdown minutes"><span class="time-count">%M</span> <p>Min</p></span> <span class="cdown second"> <span><span class="time-count">%S</span> <p>Sec</p></span>'
        )
      );
    });
  });
  /*--------------------------
	scrollUp
---------------------------- */

  $.scrollUp({
    scrollText: '<i class="fa fa-angle-up"></i>',
    easingType: "linear",
    scrollSpeed: 900,
    animation: "fade"
  });
  /*----------------------------
 price-slider active
------------------------------ */

  $("#slider-range").slider({
    range: true,
    min: 20,
    max: 800,
    values: [60, 250],
    slide: function(event, ui) {
      $("#amount").val(ui.values[0] + "K - " + ui.values[1] + "K");
    }
  });
  $("#amount").val(
    $("#slider-range").slider("values", 0) +
      "K - " +
      $("#slider-range").slider("values", 1) +
      "K"
  );
  /*----------------------------------
 Create an account toggle function 
 ----------------------------------*/
  $("#ship-box").on("click", function() {
    $("#ship-box-info").slideToggle(1000);
  });

  /*--------------------------------
	Create an account toggle function
---------------------------------*/
  $("#cbox").on("click", function() {
    $("#cbox_info").slideToggle(900);
  });

  /*---------------------
	venobox
--------------------- */
  $(".venobox").venobox();

  /*----------------------------
	Input Plus Minus Button
------------------------------ */

  $(".cart-plus-minus").append(
    '<div class="dec qtybutton">-</div><div class="inc qtybutton">+</div>'
  );
  $(".qtybutton").on("click", function() {
    var $button = $(this);
    var oldValue = $button
      .parent()
      .find("input")
      .val();
    var giamGia = $("#tamTinh1").text().substring(0,2);
    var maSach = $button
        .parent()
        .find("p")
        .text();
    if ($button.text() == "+") {
      if(oldValue < 10){
        var newVal = parseFloat(oldValue) + 1;
      }
      else {
        newVal = 10;
      }

    } else {
      // Don't allow decrementing below zero
      if (oldValue > 1) {
        var newVal = parseFloat(oldValue) - 1;
      } else {
        newVal = 1;
      }
    }
    $button
      .parent()
      .find("input")
      .val(newVal);
    change(newVal,maSach);
    tongSoLuong();
    tienKhuyenMai(giamGia);
  });
  function change(soLuong, maSach) {
    var xhttp;
    if (window.XMLHttpRequest) {
      xhttp = new XMLHttpRequest();
    } else {
      xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhttp.onreadystatechange = function () {
      if (xhttp.readyState == 4) {
        var data = xhttp.responseText;
        document.getElementById(maSach).innerHTML = soLuong;
        var maSachHeder = maSach +"1";
        var maSachHeder1 = maSach +"11";
        document.getElementById(maSachHeder).innerHTML = soLuong;
        document.getElementById(maSachHeder1).innerHTML = soLuong;
        document.getElementById("total").innerHTML= data;
        document.getElementById("total1").innerHTML= data;
        document.getElementById("tamTinh").innerHTML = data;
        document.getElementById("tongTien").innerHTML = data;
      }
    }
    xhttp.open("POST", "http://localhost:8080/BookProject/ThayDoiSoLuongController?maSach=" + maSach + "&soLuong=" + soLuong, true);
    xhttp.send();
  }
  function tongSoLuong() {
    var xhttp;
    if (window.XMLHttpRequest) {
      xhttp = new XMLHttpRequest();
    } else {
      xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhttp.onreadystatechange = function () {
      if (xhttp.readyState == 4) {
        var data = xhttp.responseText;
        document.getElementById("tongSoLuong").innerHTML = data;
      }
    }
    xhttp.open("POST", "http://localhost:8080/BookProject/ThayDoiTongSoLuongController", true);
    xhttp.send();
  }
  function tienKhuyenMai(giamGia) {
    var xhttp;
    if (window.XMLHttpRequest) {
      xhttp = new XMLHttpRequest();
    } else {
      xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhttp.onreadystatechange = function () {
      if (xhttp.readyState == 4) {
        var data = xhttp.responseText;
        document.getElementById("tongTien1").innerHTML = data;
      }
    }
    xhttp.open("POST", "http://localhost:8080/BookProject/ThayDoiTongTienGiamGiaController?khuyenMai=" + giamGia, true);
    xhttp.send();
  }
  /*---------------------
 Faq page Accrodian
--------------------- */
  $(".collapse")
    .on("shown.bs.collapse", function() {
      $(this)
        .parent()
        .find(".fa-plus-square")
        .removeClass("fa-plus-square")
        .addClass("fa-minus-square");
    })
    .on("hidden.bs.collapse", function() {
      $(this)
        .parent()
        .find(".fa-minus-square")
        .removeClass("fa-minus-square")
        .addClass("fa-plus-square");
    });
  /*----------------------------
	jQuery MeanMenu
------------------------------ */
  jQuery("nav#dropdown").meanmenu();
})(jQuery);
