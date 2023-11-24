package Source.Controllers;

import Source.Models.PhoneInCart;
import Source.Models.User;
import Source.Services.CartService;
import Source.Services.PhoneInCartService;
import Source.Services.PhoneService;
import Source.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController {
    private final PhoneInCartService phoneInCartService;

    private final PhoneService phoneService;

    private final CartService cartService;

    public CartController(PhoneInCartService phoneInCartService, PhoneService phoneService,
                          CartService cartService) {
        this.phoneInCartService = phoneInCartService;
        this.phoneService = phoneService;
        this.cartService = cartService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String viewCart(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");

        if(user == null)
            return "redirect:/";

        List<PhoneInCart> phoneInCarts = phoneInCartService.findAllByIdCart(user.getCart().getId());
        model.addAttribute("phoneInCarts", phoneInCarts);

        int totalPrice = phoneInCarts
                .stream()
                .mapToInt(phoneInCart -> phoneInCart.getQuantity() * phoneInCart.getPhone().getPrice())
                .sum();

        model.addAttribute("totalPrice", totalPrice);

        int fastDelivery = totalPrice / 100;
        int standardDelivery = totalPrice / 1000;
        int deliveryPrice = 0;

        model.addAttribute("fastDelivery", fastDelivery);
        model.addAttribute("standardDelivery", standardDelivery);
        model.addAttribute("deliveryPrice", deliveryPrice);

        return "cart";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addToCart(@RequestParam("id") int id, HttpSession session,
                            RedirectAttributes redirectAttributes){
        User user = (User) session.getAttribute("user");

        PhoneInCart phone = phoneInCartService.findByIdCartAndIdPhone(user.getCart().getId(), id);
        if(phone != null){
            phone.setQuantity(phone.getQuantity() + 1);
            phoneInCartService.update(phone);
        }else{
            phone = new PhoneInCart(0, phoneService.findById(id), cartService.findById(user.getCart().getId()), 1);
            phoneInCartService.save(phone);
        }

        redirectAttributes.addFlashAttribute("flashMessage", "Thêm vào giỏ hàng thành công!");

        return "redirect:/home";
    }

    @RequestMapping(value = "/minus", method = RequestMethod.GET)
    public String minusInCart(@RequestParam("id") int id, Model model,
                              HttpSession session){
        PhoneInCart phone = phoneInCartService.findById(id);
        if(phone.getQuantity() > 1){
            phone.setQuantity(phone.getQuantity() - 1);
            phoneInCartService.update(phone);
        }else{
            phoneInCartService.delete(phone);
        }

        return viewCart(model, session);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addInAdd(@RequestParam("id") int id, Model model,
                           HttpSession session){
        PhoneInCart phone = phoneInCartService.findById(id);
        phone.setQuantity(phone.getQuantity() + 1);
        phoneInCartService.update(phone);
        return viewCart(model, session);
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public String cancelInCart(@RequestParam("id") int id, Model model,
                               HttpSession session){
        PhoneInCart phone = phoneInCartService.findById(id);
        phoneInCartService.delete(phone);
        return viewCart(model, session);
    }

    @RequestMapping(value = "/back", method = RequestMethod.GET)
    public String backToHome(){
        return "redirect:/home";
    }

    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public String payment(RedirectAttributes redirectAttributes,
                          HttpSession session){
        User user = (User) session.getAttribute("user");

        phoneInCartService.deleteAllByIdCart(user.getCart().getId());

        redirectAttributes.addFlashAttribute("flashMessage", "Thanh toán thành công!");
        return "redirect:/home";
    }
}
