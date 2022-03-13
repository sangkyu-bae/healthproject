<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="product_containers">
	<div class="product_wrap">
		<div class="product_head">ShOP</div>
		<div class="product_img_box">
			<div class="filter_wrap float_box">
				<div class="filter_head">Filter by</div>
				<div class="filter_list_wrap">
					<ul class="filter_list">
						<li class="filter_item" data-category="0"><a
							class="filter_link click_item">All</a></li>
						<li class="filter_item" data-category="3"><a
							class="filter_link">Rain Coats</a></li>
						<li class="filter_item" data-category="2"><a
							class="filter_link">Sleeping Bags</a></li>
						<li class="filter_item" data-category="1"><a
							class="filter_link">Tent Shop</a></li>
					</ul>
				</div>
			</div>
			<div class="product_img_wrap float_box">
				<ul class="product_img_ul">
					<!-- 
                <li class="product_img_item">
                  <a href="#">
                    <img src="http://localhost:8080/healthproject/resources/img/product_1.png" alt="">
                  </a>
                  <div class="product_view">
                    <a href="#">
                        Qucik View
                    </a>
                  </div>
                  <div class="product_text">
                    I'm a product
                  </div>
                  <div class="product_price">
                    72,000원
                  </div>
                </li>
                 -->
				</ul>
			</div>
			<div class="select_box">
				<select id="select-lang" onchange="sortProduct();" name="">
					<option selected value="0">Sort By</option>
					<option value="price-low">Price(low to high)</option>
					<option value="price-high">price(high to low)</option>
					<option value="name-low">Name A-Z</option>
					<option value="name-high">Name Z-A</option>
				</select>
			</div>
		</div>
	</div>
</div>
