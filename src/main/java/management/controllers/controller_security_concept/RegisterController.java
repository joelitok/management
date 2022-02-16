package management.controllers.controller_security_concept;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm; 
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import management.entities.entities_security_concept.AppUser;
import management.entities.entities_security_concept.RegisterUser;
import management.security.SecurityConstants;
import management.services.AccountService;

@CrossOrigin("*")
@RestController
public class RegisterController {
    @Autowired  
    private AccountService accountService;  
    

    //ajouter un nouvelle utilisateur dans le system
    @PostMapping("/register")
    public AppUser register(@RequestBody RegisterUser userForm){
        if(!userForm.getPassword().equals(userForm.getRepassword()))
        throw new RuntimeException("You must confirm your password");
        AppUser user = accountService.findUserByUsername(userForm.getUsername());
        if(user!=null) throw new RuntimeException("this  use already exist");
        AppUser appUser =new AppUser();
        appUser.setUsername(userForm.getUsername());
        appUser.setPassword(userForm.getPassword());
        accountService.saveUser(appUser);
        accountService.addRoleToUser(userForm.getUsername(), "USER");
        return appUser;
      }
      
      //no use
      @PostMapping("/login")
      public AppUser login(@RequestBody AppUser appUser){
        AppUser user =accountService.findUserByUsername(appUser.getUsername());
        if(user!=null) throw new RuntimeException(" Successfull connection ");
        if(user==null) throw new RuntimeException("Not found user");
        return appUser;

      }


      




      //Rafrechir le token de l'utilisateur    
@GetMapping(path="/refreshToken")
public <JWTVerifier> void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
String auhToken =request.getHeader(SecurityConstants.HEADER_STRING);
if(auhToken!=null && auhToken.startsWith(SecurityConstants.TOKEN_PREFIX)){
  try {
    String jwt = auhToken.substring(SecurityConstants.TOKEN_PREFIX.length());
    Algorithm algorithm =Algorithm.HMAC256(SecurityConstants.SECRET);
    com.auth0.jwt.JWTVerifier jwtVerifier =JWT.require(algorithm).build();
    DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
    String username =decodedJWT.getSubject();
   AppUser appUser =accountService.findUserByUsername(username);
    String jwtAccessToken =JWT.create().
    withSubject(appUser.getUsername())
    .withExpiresAt(new Date(System.currentTimeMillis()+5*60*100))
    .withIssuer(request.getRequestURL().toString())
    .withClaim("roles", appUser.getRoles().stream().map(
    r->r.getRoleName()).collect(Collectors.toList())).sign(algorithm);

    Map<String,String> idtoken =new HashMap<>();
    idtoken.put(SecurityConstants.ACCESS_TOKEN, jwtAccessToken);
    idtoken.put(SecurityConstants.REFRESH_TOKEN, jwt);
    response.setContentType("application/json");
    new ObjectMapper().writeValue(response.getOutputStream(), idtoken);
    


  } catch (Exception e) {
    throw e;
  }
}else{
  throw new RuntimeException("Refresh token required!!!");

}


}

@GetMapping(path="/profile")
public AppUser profile(Principal principal){
  return accountService.findUserByUsername(principal.getName());
  
}









}
