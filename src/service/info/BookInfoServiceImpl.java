package service.info;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import commons.MyBatisUtils;
import dao.info.BookInfoMapper;
import pojo.Category;
import pojo.Info;

public class BookInfoServiceImpl implements BookInfoService {
	SqlSession sqlSession;
	
	@Override
	public List<Info> getList() {
		sqlSession=MyBatisUtils.createSqlSession();
		List<Info> list=sqlSession.getMapper(BookInfoMapper.class).getList();
		MyBatisUtils.close(sqlSession);
		return list;
	}
	//获得分类全部类
	@Override
	public List<Category> getCateList() {
		sqlSession=MyBatisUtils.createSqlSession();
		List<Category> list=sqlSession.getMapper(BookInfoMapper.class).getCateList();
		MyBatisUtils.close(sqlSession);
		return list;
	}
	//分类查询
	@Override
	public List<Info> findByid(int id) {
		sqlSession=MyBatisUtils.createSqlSession();
		List<Info> list=sqlSession.getMapper(BookInfoMapper.class).findByid(id);
		MyBatisUtils.close(sqlSession);
		return list;
	}
	//获得数据总条数
	@Override
	public int getCount(String condition) {
		sqlSession=MyBatisUtils.createSqlSession();
		int com=sqlSession.getMapper(BookInfoMapper.class).getCount(condition);
		MyBatisUtils.close(sqlSession);
		return com;
	}
	//获得分页后的数据
	@Override
	public List getpageLists(String condition,int start, int pageSize) {
		sqlSession=MyBatisUtils.createSqlSession();
		List<Info> pageLists=sqlSession.getMapper(BookInfoMapper.class).getpageLists(condition,start,pageSize);
		sqlSession.close();
		return pageLists;
	}
	
	//根据名字查询
	@Override
	public List findeByname(String condition) {
		sqlSession=MyBatisUtils.createSqlSession();
		List<Info> list=sqlSession.getMapper(BookInfoMapper.class).findeByname(condition);
		MyBatisUtils.close(sqlSession);
		return list;
	}
	//添加分类名称
	@Override
	public boolean Addcategory(String category) {
		sqlSession=MyBatisUtils.createSqlSession();
		int num=sqlSession.getMapper(BookInfoMapper.class).Addcategory(category);
		if (num>0) {
			sqlSession.commit();
			MyBatisUtils.close(sqlSession);
			return true;
		}else {
			sqlSession.rollback();
			return false;
		}
	}
	//根据id删除分类
	@Override
	public boolean detelecategory(int id) {
		sqlSession=MyBatisUtils.createSqlSession();
		int num=sqlSession.getMapper(BookInfoMapper.class).detelecategory(id);
		if (num>0) {
			sqlSession.commit();
			MyBatisUtils.close(sqlSession);
			return true;
		}else {
			sqlSession.rollback();
			return false;
		}
	}
	//根据id删除书籍
	@Override
	public boolean deteleBook(int id) {
		sqlSession=MyBatisUtils.createSqlSession();
		int num=sqlSession.getMapper(BookInfoMapper.class).deteleBook(id);
		if (num>0) {
			sqlSession.commit();
			MyBatisUtils.close(sqlSession);
			return true;
		}else {
			sqlSession.rollback();
			return false;
		}
	}
	//根据id查找书籍
	@Override
	public List findBook(int id) {
		sqlSession=MyBatisUtils.createSqlSession();
		List<Info> list=sqlSession.getMapper(BookInfoMapper.class).findBook(id);
		MyBatisUtils.close(sqlSession);
		return list;
	}
	//修改书籍信息
	@Override
	public boolean saveBook(Info inn) {
		sqlSession=MyBatisUtils.createSqlSession();
		int num=sqlSession.getMapper(BookInfoMapper.class).saveBook(inn);
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
