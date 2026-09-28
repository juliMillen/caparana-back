package com.jm.caparana.DTO;

import com.jm.caparana.Entity.Person;
import com.jm.caparana.Enum.StaffPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StaffDTO extends Person {

    private Long idStaff;

    private StaffPosition position;
}
