package co.grandcircus.A1Coffee;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.A1Coffee.dao.CartItemRepository;
import co.grandcircus.A1Coffee.dao.ProductRepository;
import co.grandcircus.A1Coffee.dao.UserRepository;
import co.grandcircus.A1Coffee.entities.CartItem;
import co.grandcircus.A1Coffee.entities.Product;
import co.grandcircus.A1Coffee.entities.User;



@Controller
public class A1CoffeeController {
	
	@Autowired
	UserRepository dao;
	
	@Autowired
	ProductRepository productDao;
	
	@Autowired
	CartItemRepository cartItemDao;
//	===============================Log In============================
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/signup")
	public ModelAndView showSignup() {
		return new ModelAndView("signup-form");
	}
	
	@RequestMapping("/signup-confirmation")
	public ModelAndView submitSignup(User user, HttpSession session) {
		dao.save(user);
		session.setAttribute("user", user);
		ModelAndView mv = new ModelAndView("thanks");
		mv.addObject(user);
		return mv;
	}
	
	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login-form");
	}
	
	@PostMapping("/login")
	public ModelAndView submitLogin(
			@RequestParam("email")String email,
			@RequestParam("password")String password,
			HttpSession session) {
		User user = dao.findByEmailAndPassword(email, password);
//		System.out.println(user);
		if(user == null) {
			return new ModelAndView("login-form", "message", "Incorrect username or password");
		}
		
		session.setAttribute("user", user);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();//This scraps current session
		return new ModelAndView("redirect:/");
	}
//	===============================List============================
	@RequestMapping("/list")
	public ModelAndView list() {
		List<Product> ListOfProducts = productDao.findAll();
//		System.out.println(ListOfProducts);
		return new ModelAndView("list", "product", ListOfProducts);
	}
	@RequestMapping("/add")
	public ModelAndView showAdd() {
		return new ModelAndView("add");
	}
	@RequestMapping("/add-confirmation")
	public ModelAndView submitSignup(Product product, HttpSession session) {
		productDao.save(product);
		session.setAttribute("product", product);
		ModelAndView mv = new ModelAndView("redirect:/list");
		mv.addObject(product);
		return mv;
	}
	@RequestMapping("/edit")
	public ModelAndView showEdit(@RequestParam("id") Long id) {
		Product product = productDao.getOne(id);
		return new ModelAndView("edit", "product", product);
	}
	@PostMapping("/edit")
	public ModelAndView edit(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("description") String description, HttpSession session) {
//		System.out.println("Here");
//		System.out.println(id);
		Product product = productDao.getOne(id);
//		System.out.println("id: " + id + "Name: " + name + "Price: " + price + "Description: " + description);
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		productDao.save(product);
		return new ModelAndView("redirect:/list");
	}
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam("id") Long id) {
		productDao.deleteById(id);
		return new ModelAndView("redirect:/list");
	}
	
//	===============================Cart============================
	@RequestMapping("/add-to-cart")
	public ModelAndView addToCart(
			@RequestParam("productid") Long productId, 
			@RequestParam("userid") Long userId) {
		System.out.println(cartHasItem(productId, userId));
		if(!cartHasItem(productId, userId)) {
			addItemToCart(productId, userId);
		} else {
			CartItem cartItem = cartItemDao.findByProductIdAndUserId(productId, userId);
			cartItem.setQuantity(cartItem.getQuantity() + 1);
			cartItemDao.save(cartItem);
		};
		return new ModelAndView("redirect:/list");
	}
	
	public boolean cartHasItem(Long productId, Long userId) {
		CartItem cartItem = cartItemDao.findByProductIdAndUserId(productId, userId);
		if (cartItem == null) {
			return false;
		} else {
			return true;
		}
//		System.out.println("Item in cart: " + cartItem);
	}
	
	public void addItemToCart(Long productId, Long userId) {
		Product product = productDao.getOne(productId);
		CartItem cartItem = new CartItem();
		cartItem.setProductId(productId);
		cartItem.setUserId(userId);
		cartItem.setDescription(product.getDescription());
		cartItem.setName(product.getName());
		cartItem.setPrice(product.getPrice());
		cartItem.setQuantity(1);
		cartItemDao.save(cartItem);
	}
}
