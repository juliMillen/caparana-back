package com.jm.caparana.Entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter @Setter
public abstract class Person {

    protected String name;

    protected String surname;

    protected String urlImage;
}
