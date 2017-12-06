package smbms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import smbms.pojo.User;

@Repository	// 效果和@Service同意，springMVC为DAO准备的注解
public class UserDao {
	
	@Resource
	private JdbcTemplate template;
	
	// 匿名内部类对象
	private RowMapper<User> rowMapper = new RowMapper<User>(){
		@Override
		public User mapRow(ResultSet rs, int index) throws SQLException {
			User user = new User();
			user.setId(rs.getInt(1));
			user.setUserName(rs.getString(2));
			user.setUserCode(rs.getString(3));
			user.setUserPassword(rs.getString(4));
			return user;
		}
	};

	// 通过用户名查询用户信息
	public User searchUserByName(String name){
		String sql ="select id, username, usercode, userpassword "
				+ "from smbms_user where usercode = ?";
		
		User user = template.queryForObject(sql, rowMapper, name);	
		return user;
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = (UserDao) ctx.getBean("userDao");	
		User user = dao.searchUserByName("zhaoyan");
		System.out.println(user.getUserName());
		
	}
}
