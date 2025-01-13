package br.com.profdinho.challenge.controller;

import br.com.profdinho.challenge.dto.JwtDTO;
import br.com.profdinho.challenge.validation.JwtValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(JwtController.class)
public class JwtControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtValidator jwtValidator;

    @Test
    public void testValidateJwtSuccess() throws Exception {
        String jwt = "valid.jwt.token";
        when(jwtValidator.validateJwt(jwt)).thenReturn(true);

        mockMvc.perform(post("/api/validate/" + jwt)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testValidateJwtFailure() throws Exception {
        String jwt = "invalid.jwt.token";
        when(jwtValidator.validateJwt(jwt)).thenReturn(false);

        mockMvc.perform(post("/api/validate/" + jwt)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("false"));
    }

    @Test
    public void testCreateJwtToken() throws Exception {
        JwtDTO jwtDTO = new JwtDTO("TestUser","Admin",7);

        String jwt = "generated.jwt.token";
        when(jwtValidator.createJWT(jwtDTO.getRole(), jwtDTO.getSeed(), jwtDTO.getName())).thenReturn(jwt);

        mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"TestUser\",\"role\":\"Admin\",\"seed\":7}"))
                .andExpect(status().isOk())
                .andExpect(content().string(jwt));
    }
}
