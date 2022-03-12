package kr.or.connect.healthproject.login.dto;

import java.util.Date;

public class ReservationInfo {
	private Long id;
	private Long productId;
	private Long userId;
	private Date reservationDate;
	private int cancleFlag;
	private Date createDate;
	private Date modifyDate;
	private Long productSizeId;
	
//	private Long displayInfoId;
//	private String reservationName;
//	private String reservationTel;
//	private String reservationEmail;
//	public String getReservationName() {
//		return reservationName;
//	}
//	public void setReservationName(String reservationName) {
//		this.reservationName = reservationName;
//	}
//	public String getReservationTel() {
//		return reservationTel;
//	}
//	public void setReservationTel(String reservationTel) {
//		this.reservationTel = reservationTel;
//	}
//	public String getReservationEmail() {
//		return reservationEmail;
//	}
//	public void setReservationEmail(String reservationEmail) {
//		this.reservationEmail = reservationEmail;
//	}
//	public Long getDisplayInfoId() {
//		return displayInfoId;
//	}
//	public void setDisplayInfoId(Long displayInfoId) {
//		this.displayInfoId = displayInfoId;
//	}
	public Long getProductSizeId() {
		return productSizeId;
	}
	public void setProductSizeId(Long productSizeId) {
		this.productSizeId = productSizeId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public int getCancleFlag() {
		return cancleFlag;
	}
	public void setCancleFlag(int cancleFlag) {
		this.cancleFlag = cancleFlag;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
}
