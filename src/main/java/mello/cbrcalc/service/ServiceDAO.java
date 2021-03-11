package mello.cbrcalc.service;

import mello.cbrcalc.xml.ValCode;
import mello.cbrcalc.xml.ValRate;

import java.util.List;

public interface ServiceDAO {
    void saveOrUpdateValRate(ValRate valRate);

    ValCode findValutaById(String id);

    List<ValCode> getValutaCodes();

    void saveOrUpdateValCode(ValCode valuta);
}
