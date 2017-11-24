package cstOptions.SpringSecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("admin");
    	System.out.println("Logged in as Admin");
    	return model;
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user() {
    	ModelAndView model = new ModelAndView();
    	model.setViewName("user");
    	System.out.println("Logged in user");
    	return model;
    }

}