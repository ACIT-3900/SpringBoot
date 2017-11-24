package cstOptions.SpringSecurity;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


	@Component
	public class AuthSuccessHandler implements AuthenticationSuccessHandler {
		
		private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		
		@Override
		public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication authentication)
				throws IOException, ServletException {
			
			@SuppressWarnings("unchecked")
			Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
			System.out.println("Redirecting based on role...");
			authorities.forEach(authority -> {
				if(authority.getAuthority().equals("ROLE_USER")) {
					try {
						redirectStrategy.sendRedirect(arg0, arg1, "/user");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if(authority.getAuthority().equals("ROLE_ADMIN")) {
					try {
						redirectStrategy.sendRedirect(arg0, arg1, "/admin");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
		            throw new IllegalStateException();
		        }
			});
			
		}
	 
	}
