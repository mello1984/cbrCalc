package mello.cbrcalc.web;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import mello.cbrcalc.entity.ValRate;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExchangeTransaction {
    String currencyFrom;
    String currencyTo;
    @Pattern(regexp = "[\\d]+\\.?[\\d]*", message = "You need use digit amount here")
    String amountFrom;
    String amountTo;
    ValRate valRateFrom;
    ValRate valRateTo;
}
