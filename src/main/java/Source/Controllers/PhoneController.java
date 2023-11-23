package Source.Controllers;

import Source.Models.Phone;
import Source.Services.PhoneInCartService;
import Source.Services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @Autowired
    private PhoneInCartService phoneInCartService;

    @RequestMapping("")
    public String home(Model model){
        List<Phone> phones = phoneService.findAll();
        int numInCart = phoneInCartService.findAll().size();

        model.addAttribute("phones", phones);
        model.addAttribute("numInCart", numInCart);
        return "home";
    }
}
