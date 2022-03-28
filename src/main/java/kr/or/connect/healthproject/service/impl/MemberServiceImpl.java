package kr.or.connect.healthproject.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.healthproject.dao.FileInfoDao;
import kr.or.connect.healthproject.dao.ProductPriceDao;
import kr.or.connect.healthproject.dao.SelectPromotionDao;
import kr.or.connect.healthproject.dto.FileInfo;
import kr.or.connect.healthproject.dto.ProductPrice;
import kr.or.connect.healthproject.login.dao.BuyInfoDao;
import kr.or.connect.healthproject.login.dao.MyCartDao;
import kr.or.connect.healthproject.login.dao.OrderListDao;
import kr.or.connect.healthproject.login.dao.ProductQuestionDao;
import kr.or.connect.healthproject.login.dao.ProductQuestionListDao;
import kr.or.connect.healthproject.login.dao.ReservationInfoDao;
import kr.or.connect.healthproject.login.dao.ReservationInfoPriceDao;
import kr.or.connect.healthproject.login.dao.ReservationUserCommentDao;
import kr.or.connect.healthproject.login.dao.ReservationUserCommentImageDao;
import kr.or.connect.healthproject.login.dao.UserDao;
import kr.or.connect.healthproject.login.dao.UserRoleDao;
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
import kr.or.connect.healthproject.login.dto.ReservationUserCommentImage;
import kr.or.connect.healthproject.login.dto.User;
import kr.or.connect.healthproject.login.dto.UserRole;
import kr.or.connect.healthproject.member.dao.MemberReservationInfoDao;
import kr.or.connect.healthproject.member.dto.MemberReservationInfo;
import kr.or.connect.healthproject.service.MemberService;
import kr.or.connect.healthproject.service.security.UserEntity;
import kr.or.connect.healthproject.service.security.UserRoleEntity;

@Service
public class MemberServiceImpl implements MemberService{

	   // ���깆���� ���� 二쇱������ 媛�泥댁�닿�, �대�� 媛�泥대�� 珥�湲고���� ����媛� �댄���� ��湲� ��臾몄�� final濡� ���명������.
	 // final濡� ���명��怨� 珥�湲고��瑜� ���� ������ ���깆������ 珥�湲고��瑜� �댁���.
	 private final UserDao userDao;
	 private final UserRoleDao userRoleDao;
	 
	 // @Service媛� 遺��� 媛�泥대�� �ㅽ��留��� �����쇰� Bean�쇰� ���깊������
	 // 湲곕낯���깆��媛� ��怨� ������ 媛��� �몄��瑜� 諛��� ���깆��留� ���� 寃쎌�� �����쇰� 愿��⑤�� ������ Bean�쇰� ���� 寃쎌�� 二쇱���댁�� �ъ�⑺��寃� ����.
	 public MemberServiceImpl(UserDao userDao, UserRoleDao userRoleDao) {
	     this.userDao = userDao;
	     this.userRoleDao = userRoleDao;
	 }
	 @Autowired
	 ReservationUserCommentDao reservationUserCommentDao;
	 @Autowired
	 ReservationUserCommentImageDao reservationUserCommentImageDao;
	 @Autowired
	 FileInfoDao fileInfoDao;
	 @Autowired
	 ReservationInfoDao reservationInfoDao;
	 @Autowired
	 ReservationInfoPriceDao reservationInfoPriceDao;
	 @Autowired
	 ProductPriceDao productPriceDao;
	 @Autowired 
	 ProductQuestionDao productQuestionDao;
	 @Autowired
	 ProductQuestionListDao productQuestionListDao;
	 @Autowired
	 MyCartDao myCartDao;
	 @Autowired
	 BuyInfoDao buyInfoDao;
	 @Autowired
	 OrderListDao orderListDao;
	 @Autowired
	 MemberReservationInfoDao memberReservationInfoDao;
	 @Autowired
	 kr.or.connect.healthproject.member.dao.OrderListsDao dao;
	 //
	 @Autowired
	 SelectPromotionDao selectPromotionDao;
	@Override
	@Transactional
	public UserEntity getUser(String loginUserId) {
		User user=userDao.getUserByLoginId(loginUserId);
		return new UserEntity(user.getEmail(), user.getPassword());
	}

	@Override
	@Transactional
	public List<UserRoleEntity> getUserRoles(String loginUserId) {
		List<UserRole>userRoles=userRoleDao.getUserRole(loginUserId);
		List<UserRoleEntity>list= new ArrayList<>();
		
		for(UserRole userRole:userRoles) {
			list.add(new UserRoleEntity(loginUserId, userRole.getRoleName()));
		}
		
		return list;
	}
	@Override
	@Transactional(readOnly = false)
	public Long addUser(User user) {
		
		user.setCreateDate(new Date());
		user.setModifyDate(new Date());
		
		userDao.addUser(user);
		
		User users =userDao.getUserByLoginId(user.getEmail());
		
		UserRole userRole=new UserRole();
		userRole.setUserId(users.getId());
		userRole.setRoleName("ROLE_USER");
		
		Long id=userRoleDao.addUserRole(userRole);
		return id;
	}
	@Override
	@Transactional
	public User getUse(String loginId) {
		User user=userDao.getUserByLoginId(loginId);
		return user;
	}
	
	
	////�ъ��� ������
	@Override
	@Transactional(readOnly = false)
	public Long addReservationUserComment(ReservationUserComment comment,FileInfo fileInfo) {
		//Long reservationid=reservationUserCommentDao.insertReservationUserComment(comment);
		reservationUserCommentDao.addReservationUserComment(comment);
		Long reservationid=reservationUserCommentDao.getId();
		fileInfo.setCreateDate(new Date());
		fileInfo.setModifyDate(new Date());
		Long fileInfoId=fileInfoDao.addFileInfo(fileInfo);
	
		
		ReservationUserCommentImage commentImage=new ReservationUserCommentImage();
		commentImage.setReservationUserCommentId(reservationid);
		commentImage.setFileId(fileInfoId);
		commentImage.setReservationInfoId(comment.getReservationInfoId());
		
		reservationUserCommentImageDao.addReservationUserCommentImage(commentImage);
		
		return reservationid;
	}
	///�ъ��� ������
	@Override
	@Transactional(readOnly = false)
	public int addReservationUserComment(ReservationUserComment comment) {
		int reservationid=reservationUserCommentDao.addReservationUserComment(comment);
		
		return reservationid;
	}

	@Override
	@Transactional(readOnly = false)
	public Long addReservationInfo(ReservationInfo info,ReservationInfoPrice infoPrice) {
		reservationInfoDao.addReservation(info);
		Long reservationInfoId=reservationInfoDao.getReservationInfoId();///�������쎈�깅�
		
		ProductPrice price=productPriceDao.getPrice(info.getProductId());///����媛�寃⑷��몄�ㅺ린
		
		infoPrice.setReservationInfoId(reservationInfoId);
		infoPrice.setProductPriceId(price.getId());
		reservationInfoPriceDao.addReservationInfoPrice(infoPrice);///���� 媛�寃� ��蹂� �깅�
		
		//return reservationInfoId;
		return infoPrice.getReservationInfoId();
	}

	@Override
	@Transactional(readOnly = false)
	public int addreservationInfo(ReservationInfo info) {
		int id=reservationInfoDao.addReservation(info);
		return id;
	}
	////
	@Override
	@Transactional(readOnly = false)
	public Long addProductQuestion(ProductQuestion productQuestion) {
		Long id=productQuestionDao.addProductQuestion(productQuestion);
		return id;
	}
/////
	@Override
	@Transactional(readOnly = false)
	public int insertPrQuestion(ProductQuestion productQuestion) {
		int id=productQuestionDao.insertProductQuestion(productQuestion);
		return id;
	}

	@Override
	@Transactional
	public List<MyCart> getCartProduct(Map<String, Object>params) {
		List<MyCart>list=myCartDao.getCartProduct(params);
		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public int deleteReservation(Long id) {
		int deleteCount=myCartDao.deleteReservation(id);
		return deleteCount;
	}
	@Override
	@Transactional
	public MyCart getMaxCartPr(Long userId) {
		int reservationId=myCartDao.getMaxReservationId(userId);
		MyCart myCart=myCartDao.getMaxProduct(reservationId);
		
		return myCart;
		
	}
	@Override
	@Transactional
	public MyCart getOlder(int reservationId) {
		MyCart cart=myCartDao.getMaxProduct(reservationId);
		return cart;
	}
	
	@Override
	@Transactional
	public int getMaxReservationId(Long userId) {
		int maxId=myCartDao.getMaxReservationId(userId);
		return maxId;
	}
	@Override
	@Transactional
	public List<BuyInfo> getBuyInfo(Long id) {
		List<BuyInfo>list=buyInfoDao.getBuyInfo(id);
		return list;
	}

	@Override
	@Transactional
	public List<BuyCount> getCount(Long id) {
		List<BuyCount>list=buyInfoDao.getButCount(id);
		return list;
	}

	@Override
	@Transactional
	public List<MyInfoProductQuestion> getMyPrQuestion(Long userId) {
		List<MyInfoProductQuestion>list=buyInfoDao.getMyQuestion(userId);
		return list;
	}

	@Override
	@Transactional
	public List<MyInfoComment> getMyComment(Long userId) {
		List<MyInfoComment>list=buyInfoDao.getMyComment(userId);
		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public Long insertOrderList(OrderList list) {
		list.setCreateDate(new Date());
		list.setModifyDate(new Date());
		
		Long id=orderListDao.insertOrderList(list);
		return id;
	}

	@Override
	@Transactional(readOnly = false)
	public int updateReservationCancleFlag(Long reservationId) {
		int id=orderListDao.updateReservationInfo(reservationId);
		return id;
	}
	/*
	 * @params MemberReservationInfo
	 * ���쎌���� 蹂�寃쎌�� ���쎌��蹂� ��������媛�怨� ���� 蹂�寃쎌�� ���� 留ㅼ����
	 */
	@Override
	@Transactional(readOnly = false)
	public int updateReservationInfo(MemberReservationInfo info) {
		int sucess=memberReservationInfoDao.updateReservationInfo(info);
		return sucess;
	}
	/*
	 * 결제완료된 사용자 결제 정보 등록
	 * @params OrderList
	 */

	@Override
	@Transactional(readOnly = false)
	public void insertOrderList(kr.or.connect.healthproject.member.dto.OrderList vo) throws Exception {
		dao.insertOrderList(vo);
		
	}

}
