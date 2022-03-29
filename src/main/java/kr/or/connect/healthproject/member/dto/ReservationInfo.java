package kr.or.connect.healthproject.member.dto;

import java.sql.Date;

public class ReservationInfo {
	private Long id;
	private Long productId;
	private Long userId;
	private Date reservationDate;
	private int cancleFlag;
	private Date createDate;
	private Date modifyDate;
	private Long productSizeid;
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
	public Long getProductSizeid() {
		return productSizeid;
	}
	public void setProductSizeid(Long productSizeid) {
		this.productSizeid = productSizeid;
	}
	
	
}
