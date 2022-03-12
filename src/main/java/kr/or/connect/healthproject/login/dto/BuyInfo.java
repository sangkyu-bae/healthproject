package kr.or.connect.healthproject.login.dto;

import java.util.Date;

public class BuyInfo {
	private String description;
	private Long cancleFlag;
	private Long reservationInfoId;
	private Long price;
	private double discountRate;
	private Long count;
	private Date createDate;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getCancleFlag() {
		return cancleFlag;
	}
	public void setCancleFlag(Long cancleFlag) {
		this.cancleFlag = cancleFlag;
	}
	public Long getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
}
