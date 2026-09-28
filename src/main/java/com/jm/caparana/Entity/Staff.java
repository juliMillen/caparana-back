package com.jm.caparana.Entity;

import com.jm.caparana.Enum.StaffPosition;
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

    private StaffPosition position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategority")
    private Categority categority;


}
