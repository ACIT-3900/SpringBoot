package spring.test.sbTest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @Value("${spring.application.name}")
    String appName;
 
    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "index";
    }
}