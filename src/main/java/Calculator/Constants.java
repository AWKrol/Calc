package Calculator;

import java.util.HashMap;
import java.util.Map;


public class Constants {
    static final Map<String, String> CALC_DATA = new HashMap<>();

    static {  // словарь текстового представления
        CALC_DATA.put("fioDict", "ФИО заемщика: ");     // входных данных
        CALC_DATA.put("loanTermDict", "срок кредитования в годах: ");// словарь текстового представления
        CALC_DATA.put("loanAmountDict", "сумма ипотеки: ");// расчетных данных
        CALC_DATA.put("interestRateDict", "годовая процентная ставка: ");
        CALC_DATA.put("mountlyPaymentDict", "месячный платеж: ");
        CALC_DATA.put("overPaymentDict", "переплата: ");
        CALC_DATA.put("principalDebtDict", "погашение основного долга: ");
        CALC_DATA.put("mountlyRateDict", "месячная процентная ставка: ");
        CALC_DATA.put("totalRateDict", "общая процентная ставка: ");
        CALC_DATA.put("remainingDebtDict", "остаток долга: ");
        CALC_DATA.put("repaymentPercentDict", "погашение процентов: ");
        }

    }
