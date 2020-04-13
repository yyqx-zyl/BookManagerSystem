package service.info;

import java.util.List;

import pojo.Category;
import pojo.Info;

public interface BookInfoService {
	//获得数据总条数
			int getCount(String condition);

			List getpageLists(String condition,int start, int pageSize);
			//展示所有图书信息
		List<Info> getList();

		List<Category> getCateList();
		
		List<Info> findByid(int id);
		
		List findeByname(String condition);

		boolean Addcategory(String category);

		boolean detelecategory(int id);

		boolean deteleBook(int id);

		List findBook(int id);

		boolean saveBook(Info inn);
}
