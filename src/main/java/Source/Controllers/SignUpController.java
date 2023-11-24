package Source.Controllers;

import Source.Models.Cart;
import Source.Models.User;
import Source.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signUp")
public class SignUpController {
    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewSignUp(Model model){
        return "signUp";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String signUp(@RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("re_pass") String re_pass,
                         Model model, RedirectAttributes redirectAttributes){
        if(userService.checkIfExistUsername(name)){
            model.addAttribute("usernameError", "Username is already exist!");
            return viewSignUp(model);
        } else if (userService.checkIfExistEmail(email)) {
            model.addAttribute("emailError", "Email is already exist!");
            return viewSignUp(model);
        } else if (!password.equals(re_pass)) {
            model.addAttribute("rePassError", "Re-Password isn't same password!");
            return viewSignUp(model);
        }

        User user = new User(0, name, email, password, false, new Cart());
        userService.save(user);

        redirectAttributes.addFlashAttribute("flashMessage", "Welcome!");
        return "redirect:/home";
    }
}
