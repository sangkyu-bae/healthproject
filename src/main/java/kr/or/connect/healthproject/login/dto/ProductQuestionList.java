package kr.or.connect.healthproject.login.dto;

import java.util.Date;

public class ProductQuestionList {
	private Long id;
	private String name;
	private Long questionAnswer;
	private String text;
	private String email;
	private Date createDate;
	private ProductQuestionAnwserDt productQuestionAnwserDt;
	public ProductQuestionAnwserDt getProductQuestionAnwserDt() {
		return productQuestionAnwserDt;
	}
	public void setProductQuestionAnwserDt(ProductQuestionAnwserDt productQuestionAnwserDt) {
		this.productQuestionAnwserDt = productQuestionAnwserDt;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
