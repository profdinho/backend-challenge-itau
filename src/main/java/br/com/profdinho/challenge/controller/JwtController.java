package br.com.profdinho.challenge.controller;

import br.com.profdinho.challenge.dto.JwtDTO;
import br.com.profdinho.challenge.validation.JwtValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JwtController {

    @Autowired
    private JwtValidator jwtValidator;

    @PostMapping("/validate/{jwt}")
    public ResponseEntity<Boolean> validateJwt(@PathVariable String jwt) {
        if (jwtValidator.validateJwt(jwt)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> getToken(@RequestBody JwtDTO body) {
        String token = jwtValidator.createJWT(body.getRole(), body.getSeed(), body.getName());
        return ResponseEntity.ok(token);
    }

}