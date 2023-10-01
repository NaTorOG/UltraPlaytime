package net.pinodev.ultraplaytime.placeholder;


import lombok.Getter;

public class Placeholders {

    @Getter
    private final String toSubstitute;

    @Getter
    private final String substitutor;

    public Placeholders(String toSubstitute, String substitutor) {
        this.toSubstitute = toSubstitute;
        this.substitutor = substitutor;
    }
}
