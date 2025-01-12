package br.com.profdinho.challenge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class JwtDTO {
    private String name;
    private String role;
    private Integer seed;
}
