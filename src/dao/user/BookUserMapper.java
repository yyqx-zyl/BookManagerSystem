package dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.User;

public interface BookUserMapper {
	//登录
	int loginValidate(@Param("userName") String userName,@Param("userPsw") String userPsw);
	//注册
	int saveUser(User user);
	
	User getName(@Param("userName") String userName);
	
}
