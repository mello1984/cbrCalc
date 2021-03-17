package mello.cbrcalc.service;

import mello.cbrcalc.entity.ValCodeDaily;
import mello.cbrcalc.entity.ValRate;

import java.time.LocalDate;
import java.util.List;

public interface ServiceDAO {
    void saveOrUpdateValCodeDaily(ValCodeDaily v);

    void saveOrUpdateValRate(ValRate valRate);

    ValCodeDaily findValCodeDailyById(String id);

    List<ValCodeDaily> getValCodeDailys();


    ValRate findValRateById(String id);

    boolean updateRateDB();


    boolean updateCodeDailyDB();

    List<String> getCharCodeList();

    LocalDate getMaxDate();
}
