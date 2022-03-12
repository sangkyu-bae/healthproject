package kr.or.connect.healthproject.login.dto;

import java.util.Date;

public class MyInfoProductQuestion {
	private Long productQuestionId;
	private String description;
	private String text;
	private String name;
	private Long questionAnswer;
	private Date createDate;
	public Long getProductQuestionId() {
		return productQuestionId;
	}
	public void setProductQuestionId(Long productQuestionId) {
		this.productQuestionId = productQuestionId;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getQuestionAnswer() {
		return questionAnswer;
	}
	public void setQuestionAnswer(Long questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
