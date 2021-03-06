package kr.or.connect.healthproject.service;

import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import kr.or.connect.healthproject.dto.FileInfo;
import kr.or.connect.healthproject.login.dto.BuyCount;
import kr.or.connect.healthproject.login.dto.BuyInfo;
import kr.or.connect.healthproject.login.dto.MyCart;
import kr.or.connect.healthproject.login.dto.MyInfoComment;
import kr.or.connect.healthproject.login.dto.MyInfoProductQuestion;
import kr.or.connect.healthproject.login.dto.OrderList;
import kr.or.connect.healthproject.login.dto.ProductQuestion;
import kr.or.connect.healthproject.login.dto.ProductQuestionList;
import kr.or.connect.healthproject.login.dto.ReservationInfo;
import kr.or.connect.healthproject.login.dto.ReservationInfoPrice;
import kr.or.connect.healthproject.login.dto.ReservationUserComment;
import kr.or.connect.healthproject.login.dto.User;
import kr.or.connect.healthproject.login.dto.UserRole;
import kr.or.connect.healthproject.member.dto.MemberReservationInfo;
import kr.or.connect.healthproject.service.security.UserDbService;

public interface MemberService extends UserDbService{
	public Long addUser(User user);
	public User getUse(String loginId);
	
	public Long addReservationUserComment(ReservationUserComment comment, FileInfo fileInfo) throws Exception;
	public int addReservationUserComment(ReservationUserComment comment) throws Exception;
	public Long addReservationInfo(ReservationInfo info,ReservationInfoPrice infoPrice);
	
	public int addreservationInfo(ReservationInfo info);
	public Long addProductQuestion(ProductQuestion productQuestion);
	public int insertPrQuestion(ProductQuestion productQuestion);
	
	public List<MyCart>getCartProduct(Map<String, Object>params);
	public int deleteReservation(Long id);
	public int getMaxReservationId(Long userId);
	public MyCart getMaxCartPr(Long userId);
	public MyCart getOlder(int reservationId);
	
	public List<BuyInfo>getBuyInfo(Long id);
	public List<BuyCount>getCount(Long id);
	public List<MyInfoProductQuestion>getMyPrQuestion(Long userId);
	public List<MyInfoComment>getMyComment(Long userId);
	
	public Long insertOrderList(OrderList list);
	public int updateReservationCancleFlag(Long reservationId);
	
	public int updateReservationInfo(MemberReservationInfo info) throws Exception;
	
	/*
	 * ??????????????? ????????? ?????? ?????? ??????
	 * @params OrderList
	 * 
	 */
	public void insertOrderLists(kr.or.connect.healthproject.member.dto.OrderList vo)throws Exception;
	/*
	 * @params MemberReservationInfo
	 * ???????????? ??????, ?????? ?????? ???????????? ?????????
	 */	
	public int updateReservationInfos(kr.or.connect.healthproject.member.dto.ReservationInfo vo) throws Exception;
	
	/*
	 * @params Map
	 * ????????? ?????? ?????? ?????? ??????
	 */
	public List<Map<String, Object>> selectMemeberOrder(Map<String, Object> params) throws Exception;
	
	/*
	 * @params Map
	 * ???????????? ????????? ????????? ??????
	 */
	public List<Map<String, Object>> selectComment(Map<String, Object>params) throws Exception;

	/*
	 * @params Map
	 * ???????????? ????????? ???????????? ?????? ??????
	 */
	public List<Map<String, Object>>selectProductQuestion(Map<String, Object>params) throws Exception;
	
	/*
	 * @params Map
	 * qa ?????? ????????????
	 */
	public Map<String, Object>selectQaAnwser(Map<String, Object>params) throws Exception;
	
}
