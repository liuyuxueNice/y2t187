package smbms.control;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import smbms.pojo.User;
import smbms.service.UserService;

@Controller
@RequestMapping("/user") // URL·�������user������userʵ��ҵ����ʵ��
public class UserControl {
	
	@Autowired
	private UserService userService;

	// ��������@RequestMappint�ͷ���������@RequestMapping�Զ����
	// ���"/user/dologin.html"
	
	// springMVC�Զ��ѱ��ֶ�����ʵ����������������ƥ��
	// ͨ��ʵ�������ղ����ô����Ѻܶ�����󰴶���λ���д���
	@RequestMapping("/dologin.html")
	public ModelAndView userLogin(@Valid User loginuser, BindingResult result){
		
		ModelAndView mv = new ModelAndView();
		
		// BindingResult����洢���Ǳ���֤�Ľ��
		if(result.hasErrors() == true){ // ��֤����
			mv.setViewName("login");    // ��ת����ҳ��
			return mv;
		}
		
		// ����Service����ʵ�ֵ�¼��֤
		User user = userService.login(loginuser.getUserCode(), 
									  loginuser.getUserPassword());
		//User user = null;
		// ���user��Ϊnull�����¼�ɹ�
		if(user != null){
			mv.setViewName("frame");
		}else{
			mv.addObject("error", "�û������������");
			mv.setViewName("login");
		}
		return mv;
	}
	
	// ��׼��Ӧָ���쳣���ͽ��д���
//	@ExceptionHandler(RuntimeException.class)
//	public ModelAndView processsException(RuntimeException ex){
//		return new ModelAndView("error", "exception", ex);
//	}
}
