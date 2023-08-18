package io.github.vahansahakyan.CodeInspect.web;

import io.github.vahansahakyan.CodeInspect.domain.Authority;
import io.github.vahansahakyan.CodeInspect.domain.User;
import io.github.vahansahakyan.CodeInspect.dto.AuthCredentialsRequest;
import io.github.vahansahakyan.CodeInspect.dto.SignUpRequest;
import io.github.vahansahakyan.CodeInspect.repository.AuthorityRepository;
import io.github.vahansahakyan.CodeInspect.repository.UserRepository;
import io.github.vahansahakyan.CodeInspect.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private AuthorityRepository authorityRepo;

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
                  request.getUsername(), request.getPassword()));

      User user = (User) authenticate.getPrincipal();

      System.out.println();
      System.out.println(user);
      System.out.println();

      var token = jwtUtil.generateToken(user);

      user.setPassword(token);

      return ResponseEntity.ok()
          .header(
              HttpHeaders.AUTHORIZATION,
              token)
          .body((user));
    } catch (BadCredentialsException ex) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

  }

  @PostMapping("signup")
  public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {

    try {
      User user = new User();
      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      String encodedPassword = passwordEncoder.encode(request.getPassword());

      user.setId((Long) (userRepo.count() + 1L));
      user.setName(request.getName());
      user.setUsername(request.getUsername());
      user.setPassword(encodedPassword);
      user.setAuthorities(Set.of(new Authority(request.getAuthority())));

      System.out.println();
      System.out.println(request);
      System.out.println();
      System.out.println(user);
      System.out.println();
      System.out.println(encodedPassword);

      userRepo.save(user);

      Authority newAuthority = new Authority();
      newAuthority.setId((Long) (authorityRepo.count() + 1L));
      newAuthority.setAuthority(request.getAuthority());
      newAuthority.setUser(user);

      authorityRepo.save(newAuthority);

      return ResponseEntity.ok()
          .body(user);
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

  }
}
