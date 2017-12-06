package smbms.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RootControl {

	// Ä¬ÈÏÊ×Ò³(µÇÂ¼Ò³Ãæ)
	@RequestMapping("/")
	public ModelAndView openLogin() {
		return new ModelAndView("login");
	}
}
