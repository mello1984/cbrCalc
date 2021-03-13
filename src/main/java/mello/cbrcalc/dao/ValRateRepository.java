package mello.cbrcalc.dao;

import mello.cbrcalc.entity.ValRate;
import mello.cbrcalc.entity.ValRatePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface ValRateRepository extends JpaRepository<ValRate, ValRatePK> {
    ValRate findValRateByValutaIdAndAndDate(String valutaId, LocalDate ld);
}
