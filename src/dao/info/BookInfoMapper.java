package dao.info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Category;
import pojo.Info;

public interface BookInfoMapper {
	//展示所有图书信息
	List<Info> getList();

	List<Category> getCateList();
	//分类查询
	List<Info> findByid(@Param("id") int id);

	int getCount(@Param("condition") String condition);

	List<Info> getpageLists(@Param("condition") String condition,@Param("start")int start,@Param("pageSize") int pageSize);
	//模糊名字查询
	List<Info> findeByname(@Param("condition")String condition);
	//添加分类
	int Addcategory(@Param("category") String category);
	//删除分类
	int detelecategory(@Param("id") int id);
	//删除书籍
	int deteleBook(@Param("id") int id);
	//查找书籍
	List<Info> findBook(@Param("id") int id);

	int saveBook(Info inn);
	
}
