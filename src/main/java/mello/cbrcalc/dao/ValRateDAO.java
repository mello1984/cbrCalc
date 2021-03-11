package mello.cbrcalc.dao;

import mello.cbrcalc.entity.ValRate;

import java.time.LocalDate;


public interface ValRateDAO {
    void saveOrUpdate(ValRate v);

    ValRate findValRateById(String id);

    ValRate findValRateByIdOnDate(String id, LocalDate ld);
}
