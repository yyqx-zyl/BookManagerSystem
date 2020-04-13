package service.user;

import java.util.List;

import pojo.User;

public interface BookUserService {
	//登录
	boolean loginValidate( String userId,String userPsw);
	//注册
	boolean saveUser(User user);
	//显示用户登录的名称
	User getName(String userId);
}
