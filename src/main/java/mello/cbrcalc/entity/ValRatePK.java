package mello.cbrcalc.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@EqualsAndHashCode
public class ValRatePK implements Serializable {
    String valuta_id;
    LocalDate date;
}
