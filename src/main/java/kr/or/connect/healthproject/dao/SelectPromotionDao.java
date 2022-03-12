package kr.or.connect.healthproject.dao;



import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import static kr.or.connect.healthproject.dao.SelectPromotionDaoSqls.*;
import kr.or.connect.healthproject.dto.SelectPromotion;
import kr.or.connect.healthproject.member.dto.MemberReservationInfo;

@Repository
public class SelectPromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<SelectPromotion>rowMapper=BeanPropertyRowMapper.newInstance(SelectPromotion.class);
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	public SelectPromotionDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<SelectPromotion>getPromotionItem(){
		return sessionTemplate.selectList("kr.or.connect.healthproject.dao.SelectPromotionsDao.getPromotionItem");
	}
	
	//
	public int updateReservationInfo(MemberReservationInfo info) {
		String sql="kr.or.connect.healthproject.dao.SelectPromotionsDao";
		int b=sessionTemplate.update(sql+".updateReservationInfo", info);
		System.out.println(b);
		int a=sessionTemplate.update("kr.or.connect.healthproject.dao.SelectPromotionsDao.updateReservationInfo",info);
		System.out.println(a);
		return 1;
	}
}
