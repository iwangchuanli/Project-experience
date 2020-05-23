package com.controller;
 
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.pojo.User;
import com.service.UserService;
 
@Controller
@RequestMapping("/user")  
public class UserController {
	
	@Resource(name="userService")
	private UserService userService;
	/**
	 * ¸ù¾Ýid²éÑ¯
	 */
	@RequestMapping(value="/queryById")
	public ModelAndView queryById(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String id = request.getParameter("id");
		try{
			User var = userService.findById(id);
			mv.setViewName("index");
			mv.addObject("var", var);
		} catch(Exception e){
            e.printStackTrace();
		}
		return mv;
	}
}
