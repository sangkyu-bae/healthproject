package kr.or.connect.healthproject.login.dto;

import java.util.Date;

public class ProductQuestion {
	private Long id;
	private Long productId;
	private Long userId;
	private Long titleCategoryId;
	private String text;
	private int questionAnswer;
	private Date createDate;
	private Date modifyDate;
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
	
	public Long getTitleCategoryId() {
		return titleCategoryId;
	}
	public void setTitleCategoryId(Long titleCategoryId) {
		this.titleCategoryId = titleCategoryId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public int getQuestionAnswer() {
		return questionAnswer;
	}
	public void setQuestionAnswer(int questionAnswer) {
		this.questionAnswer = questionAnswer;
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
