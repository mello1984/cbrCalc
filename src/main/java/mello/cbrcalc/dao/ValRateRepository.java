package mello.cbrcalc.dao;

import mello.cbrcalc.entity.ValRate;
import mello.cbrcalc.entity.ValRatePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ValRateRepository extends JpaRepository<ValRate, ValRatePK> {
    ValRate findFirstByCharCodeOrderByDateDesc(String charCode);

    @Query("SELECT DISTINCT charCode FROM ValRate ORDER BY charCode")
    List<String> getCharCodeList();

    @Query("SELECT MAX(date) FROM ValRate")
    LocalDate getMaxDate();
}
