package com.mockproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mockproject.entity.Products;
import com.mockproject.entity.Users;
import com.mockproject.service.ProductsService;
import com.mockproject.service.UsersService;

@Controller
public class HomeController {
	
	@Autowired
	private ProductsService productService;
	
	@Autowired
	private UsersService userService;
	
	// localhost:8080/index || localhost:8080 || localhost:8080/
	@GetMapping(value = {"/index", "", "/"})
	public String doGetIndex(Model model) {
		List<Products> products = productService.findAll();
		model.addAttribute("products", products);
		return "user/index";
	}
	
	@GetMapping("/login")
	public String doGetLogin(Model model) {
		model.addAttribute("userRequest", new Users());
		return "user/login";
	}
	
	@GetMapping("/logout")
	public String doGetLogout(HttpSession session) {
		session.removeAttribute("currentUser");
		return "redirect:/index";
	}
	
	@GetMapping("/register")
	public String doGetRegister(Model model) {
		model.addAttribute("userRequest", new Users());
		return "user/register";
	}
	
	@PostMapping("/login")
	public String doPostLogin(@ModelAttribute("userRequest") Users userRequest,
			HttpSession session) {
		Users userResponse = userService.doLogin(userRequest.getUsername(), userRequest.getHashPassword());
		if (userResponse != null) {
			session.setAttribute("currentUser", userResponse);
			return "redirect:/index";
		} else {
			return "redirect:/login";
		}
	}
	
	@PostMapping("/register")
	public String doPostRegister(@ModelAttribute("userRequest") Users userRequest,
			HttpSession session) {
		try {
			Users userResponse = userService.save(userRequest);
			if (userResponse != null) {
				session.setAttribute("currentUser", userResponse);
				return "redirect:/index";
			} else {
				return "redirect:/register";
			}
		} catch (Exception e) {
			return "redirect:/register";
		}
	}
}
