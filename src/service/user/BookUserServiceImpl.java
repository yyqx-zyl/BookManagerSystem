package service.user;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import commons.MyBatisUtils;
import dao.user.BookUserMapper;
import pojo.User;

public class BookUserServiceImpl implements BookUserService {
	SqlSession sqlSession;
	//登录
	@Override
	public boolean loginValidate(String userId, String userPsw) {
		sqlSession=MyBatisUtils.createSqlSession();
		int num=sqlSession.getMapper(BookUserMapper.class).loginValidate(userId, userPsw);
		if (num>0) {
			MyBatisUtils.close(sqlSession);
			return true;
		}else {
			MyBatisUtils.close(sqlSession);
			return false;
		}
	}
	//注册
	@Override
	public boolean saveUser(User user) {
		sqlSession=MyBatisUtils.createSqlSession();
		int num=sqlSession.getMapper(BookUserMapper.class).saveUser(user);
		if (num>0) {
			sqlSession.commit();
			MyBatisUtils.close(sqlSession);
			return true;
		}else {
			sqlSession.rollback();
			MyBatisUtils.close(sqlSession);
			return false;
		}
	}
	//登录的用户名
	@Override
	public User getName(String userName) {
		sqlSession=MyBatisUtils.createSqlSession();
		User user=sqlSession.getMapper(BookUserMapper.class).getName(userName);
		if(user!=null) {
			MyBatisUtils.close(sqlSession);
			return user;
		}else {
			MyBatisUtils.close(sqlSession);
			return user;
		}
	}

}
