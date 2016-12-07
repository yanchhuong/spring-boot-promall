package com.code.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.code.model.LoginForm;
import com.code.model.User;
import com.code.service.UserService;

import org.springframework.validation.BindingResult;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class LoginController  {
    @Autowired
	UserService userService;
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String showForm(ModelMap model,HttpServletRequest request) {
		
	/*	HttpSession session=request.getSession(false);
		model.addAttribute("login", new LoginForm());
		if(session==null){
			model.addAttribute("status", "Log In");
		}*/
		return "login";
	}

/*	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String processForm(@ModelAttribute("login")  LoginForm login,BindingResult result,
			ModelMap model,HttpServletRequest request,RedirectAttributes redir) {
		if (result.hasErrors()) {
			return "login";
		}
		
		System.out.println(login.getUserName());
		User user = userService.loginUser(login.getUserName(),login.getPassword());
		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
			return "login";
		}else if(login.getUserName().equals(user.getEmail())
				|| login.getPassword().equals(user.getPassword())) {
			
			HttpSession session=request.getSession();
			session.setAttribute("name",login.getUserName());
			redir.addFlashAttribute("status", "Log out");
			return "redirect:users";
		}else{
			model.addAttribute("msg", "Invalid username and password !");
			return "login";
		}
		
	}*/
	
	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public String home(
			ModelMap model,HttpServletRequest request,RedirectAttributes redir) {
		return "home";
	}
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public String hello(
			ModelMap model,HttpServletRequest request,RedirectAttributes redir) {
		return "hello";
	}
	@RequestMapping(value = "/403",method = RequestMethod.GET)
	public String error(
			ModelMap model,HttpServletRequest request,RedirectAttributes redir) {
		return "403";
	}
}