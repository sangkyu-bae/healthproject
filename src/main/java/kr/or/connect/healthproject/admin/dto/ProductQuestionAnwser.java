package kr.or.connect.healthproject.admin.dto;

import java.util.Date;

public class ProductQuestionAnwser {
	private Long id;
	private String text;
	private Long productQuestionId;
	private Long userId;
	private Date createDate;
	private Date modifyDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Long getProductQuestionId() {
		return productQuestionId;
	}
	public void setProductQuestionId(Long productQuestionId) {
		this.productQuestionId = productQuestionId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
