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
@RequestMapping("/user") // URL路径中添加user，代表user实体业务功能实现
public class UserControl {
	
	@Autowired
	private UserService userService;

	// 类声明的@RequestMappint和方法声明的@RequestMapping自动结合
	// 结果"/user/dologin.html"
	
	// springMVC自动把表单字段名和实体类中属性名进行匹配
	// 通过实体对象接收参数好处：把很多表单对象按对象单位进行处理
	@RequestMapping("/dologin.html")
	public ModelAndView userLogin(@Valid User loginuser, BindingResult result){
		
		ModelAndView mv = new ModelAndView();
		
		// BindingResult对象存储的是表单验证的结果
		if(result.hasErrors() == true){ // 验证出错
			mv.setViewName("login");    // 跳转到表单页面
			return mv;
		}
		
		// 调用Service方法实现登录验证
		User user = userService.login(loginuser.getUserCode(), 
									  loginuser.getUserPassword());
		//User user = null;
		// 如果user不为null代表登录成功
		if(user != null){
			mv.setViewName("frame");
		}else{
			mv.addObject("error", "用户名或密码错误！");
			mv.setViewName("login");
		}
		return mv;
	}
	
	// 精准对应指定异常类型进行处理
//	@ExceptionHandler(RuntimeException.class)
//	public ModelAndView processsException(RuntimeException ex){
//		return new ModelAndView("error", "exception", ex);
//	}
}
