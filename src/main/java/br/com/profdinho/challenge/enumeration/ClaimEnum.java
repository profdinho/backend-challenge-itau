package br.com.profdinho.challenge.enumeration;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ClaimEnum {
    NAME("Name"),
    ROLE("Role"),
    SEED("Seed");

    private final String description;

    ClaimEnum(String description) {
        this.description = description;
    }
}
