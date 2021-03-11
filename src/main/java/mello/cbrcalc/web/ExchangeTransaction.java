package mello.cbrcalc.web;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import mello.cbrcalc.xml.ValRate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExchangeTransaction {
    String currencyFrom;
    String currencyTo;
    double amountFrom;
    double amountTo;
    ValRate valRateFrom;
    ValRate valRateTo;
}
