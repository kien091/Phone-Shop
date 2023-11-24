package Source.Controllers;

import Source.Models.User;
import Source.Services.CartService;
import Source.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class SignInController {
    private final UserService userService;

    public SignInController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewSignIn(Model model){
        return "signIn";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String SignIn(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         Model model, RedirectAttributes redirectAttributes){
        User user = userService.findByUsername(username);
        if(user == null){
            model.addAttribute("usernameError", "Username is not exist!");
            return viewSignIn(model);
        } else if (!user.getPassword().equals(password)) {
            model.addAttribute("passwordError", "Wrong password!");
            return viewSignIn(model);
        }

        redirectAttributes.addFlashAttribute("flashMessage", "Welcome back!");
        return "redirect:/home";
    }
}
