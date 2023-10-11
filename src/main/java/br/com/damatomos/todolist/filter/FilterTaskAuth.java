package br.com.damatomos.todolist.filter;

import java.io.IOException;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.damatomos.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter{

  @Autowired
  private IUserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

        // Get Auth Credentials
        var auth = request.getHeader("Authorization");
        var base64String = auth.split(" ")[1];

        byte[] authDecode = Base64.getDecoder().decode(base64String);

        String[] credentials = new String(authDecode).split(":");
        String username = credentials[0];
        String password = credentials[1];

        // Validate User
        var user = this.userRepository.findByUsername(username);
        if (user == null) response.sendError(401);
        else
        {
          // Validate Password
          var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

          if (passwordVerify.verified)
          {
            chain.doFilter(request, response);
          } else
          {
            response.sendError(401);
          }
        }
  }

  
}
