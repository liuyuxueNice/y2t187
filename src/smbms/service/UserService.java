package smbms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smbms.dao.UserDao;
import smbms.pojo.User;

/**
 * User 业务类
 * @author Administrator
 *
 */
@Service	// 和@Control同理，springMVC专用业务类注解
public class UserService {
	
	@Autowired
	private UserDao userDao;

	// 用户登录
	public User login(String userCode, String userPassword){
		try {
			// 从DB中查询指定name用户记录是否存在
			User user = userDao.searchUserByName(userCode);
			// 如果用户存在，以为符合name条件记录是存在，进一步验证密码
			if(user != null && user.getUserPassword().equals(userPassword)){
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("用户登录数据查询发生了问题！" + e.getMessage());
		}	
		return null;
	}
}
