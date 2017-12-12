package cstOptions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {


    @RequestMapping("/studentSelection")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="Your ID was not properly registered") String name, Model model) {
        model.addAttribute("name", name);
        return "studentSelection";
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
    
    @RequestMapping("/admin/upload")
    public String upload() {
        return "uploadForm";
    }

    @RequestMapping("/admin/viewStudents")
    public String viewStudents() {
        return "viewStudents";
    }

    @RequestMapping("/admin/editStudents")
    public String editStudents() {
        return "editStudents";
    }
    
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/403")
    public String error403() {
    	return "../public/error/403";
    }

}