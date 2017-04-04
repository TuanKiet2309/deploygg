package G05.WebKhoaCLC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
	
	@RequestMapping("/")
    public String hello() {
        
        return "jsp/pagebody1";
    }
}
