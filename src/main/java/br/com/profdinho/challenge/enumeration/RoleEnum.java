package br.com.profdinho.challenge.enumeration;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum RoleEnum {
    ADMIN("Admin"),
    MEMBER("Member"),
    EXTERNAL("External");

    private final String description;

    RoleEnum(String description) {
        this.description = description;
    }
}
