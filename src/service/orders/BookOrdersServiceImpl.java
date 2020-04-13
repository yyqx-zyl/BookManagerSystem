package service.orders;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import commons.MyBatisUtils;
import dao.order.BookOrdersMapper;
import pojo.Orders;

public class BookOrdersServiceImpl implements BookOrdersService {
	private SqlSession sqlSession;
	@Override
	public boolean addOrders(Orders orders) {
		sqlSession=MyBatisUtils.createSqlSession();
		int num=sqlSession.getMapper(BookOrdersMapper.class).saveOrder(orders);
		System.out.println("num11111 :"+num);
		if (num>0) {
			sqlSession.commit();
			MyBatisUtils.close(sqlSession);
			return true;
		}else {
			sqlSession.rollback();
			return false;
		}
	}
	//显示订单方法
	@Override
	public List<Orders> findOrders(String userId) {
		sqlSession=MyBatisUtils.createSqlSession();
		List<Orders> list=sqlSession.getMapper(BookOrdersMapper.class).getOrderList(userId);
		MyBatisUtils.close(sqlSession);
		return list;
	}
	//订单总价
	@Override
	public boolean update(String oid, int count, double curPrice) {
		sqlSession=MyBatisUtils.createSqlSession();
		int num=sqlSession.getMapper(BookOrdersMapper.class).update(oid,count,curPrice);
		System.out.println("num :"+num);
		if (num>0) {
			sqlSession.commit();
			MyBatisUtils.close(sqlSession);
			return true;
		}else {
			sqlSession.rollback();
			return false;
		}
	}

}
