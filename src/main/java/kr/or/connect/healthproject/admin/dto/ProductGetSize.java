package kr.or.connect.healthproject.admin.dto;

import java.util.List;

public class ProductGetSize {
	private Long id;
	private String description;
	private String content;
	private Long price;
	private double discountRate;
	private String saveFileName;
	private List<ProductSize>productSize;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public List<ProductSize> getProductSize() {
		return productSize;
	}
	public void setProductSize(List<ProductSize> productSize) {
		this.productSize = productSize;
	}
	
	
}
