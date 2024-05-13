package dev.aloysius.librarymanagementsystem.Controllers;

import dev.aloysius.librarymanagementsystem.Domains.AuthenticationRequest;
import dev.aloysius.librarymanagementsystem.Services.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authenticate")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/")
    public String authenticate(@RequestBody AuthenticationRequest request){
        return authenticationService.authenticate(request);
    }
}
