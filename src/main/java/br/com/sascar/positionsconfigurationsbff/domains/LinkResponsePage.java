package br.com.sascar.positionsconfigurationsbff.domains;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkResponsePage {
    private HrefResponsePage href;
    private HrefResponsePage simpleTarget;
    private HrefResponsePage configuration;
}
