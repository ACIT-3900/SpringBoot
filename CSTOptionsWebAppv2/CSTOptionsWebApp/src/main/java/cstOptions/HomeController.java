package cstOptions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {


    @RequestMapping("/home")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "home";
    }
    
    @RequestMapping("/")
    public String def() {
        return "index";
    }
    
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/done")
    public String done() {
        return "done";
    }
    
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
    
    @RequestMapping("/user")
    public String user() {
        return "user";
    }
    
    @RequestMapping("/admin/upload")
    public String upload() {
        return "uploadForm";
    }
    
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    
    @RequestMapping("/students")
    public String students() {
    	return "students";
    }
    
    @RequestMapping("/403")
    public String error403() {
    	return "../public/error/403";
    }
    

}