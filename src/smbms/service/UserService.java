package smbms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smbms.dao.UserDao;
import smbms.pojo.User;

/**
 * User ҵ����
 * @author Administrator
 *
 */
@Service	// ��@Controlͬ��springMVCר��ҵ����ע��
public class UserService {
	
	@Autowired
	private UserDao userDao;

	// �û���¼
	public User login(String userCode, String userPassword){
		try {
			// ��DB�в�ѯָ��name�û���¼�Ƿ����
			User user = userDao.searchUserByName(userCode);
			// ����û����ڣ���Ϊ����name������¼�Ǵ��ڣ���һ����֤����
			if(user != null && user.getUserPassword().equals(userPassword)){
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("�û���¼���ݲ�ѯ���������⣡" + e.getMessage());
		}	
		return null;
	}
}
