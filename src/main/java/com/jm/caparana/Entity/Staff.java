package com.jm.caparana.Entity;

import com.jm.caparana.Enum.StaffRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Staff extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStaff;

    private StaffRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategority")
    private Categority categority;


}
