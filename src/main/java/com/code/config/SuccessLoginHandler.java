package com.code.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.code.model.UserSessionBean;
import com.code.service.UserService;
import com.code.session.SessionManager;

public class SuccessLoginHandler implements AuthenticationSuccessHandler{
	@Autowired
	private UserService iUserDao;
	SuccessLoginHandler(UserService iUserDao){
		this.iUserDao=iUserDao;
	}
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		UserSessionBean user = iUserDao.getSessionDao(currentUser);
		SessionManager.setSession(request, response, user);

//	
		   String targetUrl = determineTargetUrl(authentication);

	        if (response.isCommitted()) {
	            System.out.println("Can't redirect");
	            return;
	        }  
	      
	//   redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	 protected String determineTargetUrl(Authentication authentication) {
	        String url = "";
	        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

	        List<String> roles = new ArrayList<String>();

	        for (GrantedAuthority a : authorities) {
	            roles.add(a.getAuthority());
	        }
 
	        if (isUser(roles)) {
	        	url = "/";
	        } else if (isAdmin(roles)) {
	            url = "/admin";
	        } else {
	            url = "/";
	        }

	        return url;
	    }
	    private boolean isUser(List<String> roles) {
	        if (roles.contains("ROLE_USER")) {
	            return true;
	        }
	        return false;
	    }
	    private boolean isAdmin(List<String> roles) {
	        if (roles.contains("ROLE_ADMIN")) {
	            return true;
	        }
	        return false;
	    }
	    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
	        this.redirectStrategy = redirectStrategy;
	    }

	    protected RedirectStrategy getRedirectStrategy() {
	        return redirectStrategy;
	    }
}
