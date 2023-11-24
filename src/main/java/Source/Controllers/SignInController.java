package Source.Controllers;

import Source.Services.CartService;
import Source.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class SignInController {
    private final UserService userService;
    private final CartService cartService;

    public SignInController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewSignIn(){
        return "signIn";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String SignIn(Model model){
        return null;
    }
}
