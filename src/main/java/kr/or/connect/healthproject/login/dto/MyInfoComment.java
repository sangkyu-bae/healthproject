package kr.or.connect.healthproject.login.dto;

public class MyInfoComment {
	private Long reservationUserComment;
	private String description;
	private String comment;
	private double score;
	public Long getReservationUserComment() {
		return reservationUserComment;
	}
	public void setReservationUserComment(Long reservationUserComment) {
		this.reservationUserComment = reservationUserComment;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	
}
