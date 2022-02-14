package management.controllers.controller_security_concept;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/api/v1/login")
    public void login () {}

}
