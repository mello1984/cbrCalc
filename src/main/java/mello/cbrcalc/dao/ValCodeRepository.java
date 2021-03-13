package mello.cbrcalc.dao;

import mello.cbrcalc.entity.ValCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValCodeRepository extends JpaRepository<ValCode, String> {
}
