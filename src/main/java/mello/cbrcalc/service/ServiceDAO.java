package mello.cbrcalc.service;

import mello.cbrcalc.entity.ValCode;
import mello.cbrcalc.entity.ValCodeDaily;
import mello.cbrcalc.entity.ValRate;

import java.util.List;

public interface ServiceDAO {
    void saveOrUpdateValCodeDaily(ValCodeDaily v);

    void saveOrUpdateValRate(ValRate valRate);

    ValCode findValutaById(String id);

    List<ValCode> getValutaCodes();

    void saveOrUpdateValCode(ValCode valuta);

    ValRate findValRateById(String id);

    boolean updateRateDB();

    boolean updateCodeDB();

    boolean updateCodeDailyDB();

    List<String> getCharCodeList();
}
