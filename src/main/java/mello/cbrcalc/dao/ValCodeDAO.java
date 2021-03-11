package mello.cbrcalc.dao;

import mello.cbrcalc.entity.ValCode;

import java.util.List;

public interface ValCodeDAO {
    ValCode findValutaById(String id);

    List<ValCode> getValutaCodes();

    void saveOrUpdate(ValCode v);
}
