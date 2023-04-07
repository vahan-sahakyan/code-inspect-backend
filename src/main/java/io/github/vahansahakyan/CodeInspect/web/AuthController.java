package io.github.vahansahakyan.CodeInspect.web;

import io.github.vahansahakyan.CodeInspect.domain.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.vahansahakyan.CodeInspect.domain.User;
import io.github.vahansahakyan.CodeInspect.dto.AuthCredentialsRequest;
import io.github.vahansahakyan.CodeInspect.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @GetMapping("validate")
  public ResponseEntity<?> validateToken(@RequestParam String token, @AuthenticationPrincipal User user) {
    try {
      Boolean isTokenValid = jwtUtil.validateToken(token, user);
      return ResponseEntity.ok(isTokenValid);
    } catch (ExpiredJwtException e) {
      return ResponseEntity.ok(false);
    }
  }

  @PostMapping("login")
  public ResponseEntity<?> login(@RequestBody AuthCredentialsRequest request) {

    try {
      Authentication authenticate = authenticationManager
        .authenticate(
          new UsernamePasswordAuthenticationToken(
            request.getUsername(), request.getPassword()
          )
        );

      User user = (User) authenticate.getPrincipal();

      System.out.println();
      System.out.println(user);
      System.out.println();


      var token = jwtUtil.generateToken(user);

      user.setPassword(token);

      return ResponseEntity.ok()
        .header(
          HttpHeaders.AUTHORIZATION,
          token
        )
        .body((user));
    } catch (BadCredentialsException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

  }
}
