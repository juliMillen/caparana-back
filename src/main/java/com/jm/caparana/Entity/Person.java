package com.jm.caparana.Entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public abstract class Person {

    protected String name;

    protected String surname;

    protected String position;

    protected String urlImage;
}
