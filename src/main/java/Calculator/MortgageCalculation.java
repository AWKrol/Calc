package Calculator;

public class MortgageCalculation   {
    final int constTranslation = 12;   // константа перевода года в месяцы
    double principalDebt = 0;   // погашение основного долга
    double mountlyRate;   // месячная процентная ставка
    double totalRate;   // общая процентная ставка
    double mountlyPayment;   // месячный платеж
    double remainingDebt;   // остаток долга
    double repaymentPercent;   // погашение процентов
    double overPayment;   // переплата
    double [] mortCalcul;

    /*MortgageCalculation(String fio1, float lt, float la, float ir) {   // конструктор для выходных данных
        super( fio1, lt, la, ir);                                      // с ФИО заемщика
        mountlyRate = interestRate / (constTranslation * 100);
        totalRate = (float) Math.pow(1 + mountlyRate, loanTerm * constTranslation);
        mountlyPayment = (loanAmount * mountlyRate * totalRate) / (totalRate - 1);
        remainingDebt = loanAmount - principalDebt;
        repaymentPercent = remainingDebt * mountlyRate;
        principalDebt = mountlyPayment - repaymentPercent;
        overPayment = mountlyPayment * loanTerm * constTranslation - loanAmount;

    }
    MortgageCalculation(float lt, float la, float ir) {   // конструктор для выходных данных
        super(lt, la, ir);                                 // без ФИО заемщика
        mountlyRate = interestRate / (constTranslation * 100);
        totalRate = (float) Math.pow(1 + mountlyRate, loanTerm * constTranslation);
        mountlyPayment = (loanAmount * mountlyRate * totalRate) / (totalRate - 1);
        remainingDebt = loanAmount - principalDebt;
        repaymentPercent = remainingDebt * mountlyRate;
        principalDebt = mountlyPayment - repaymentPercent;
        overPayment = mountlyPayment * loanTerm * constTranslation - loanAmount;

    }

    public void calculation() {

    }

    double [] calculator(float loan_term, float loan_amount, float interest_rate) { // расчет ипотеки
        mountlyRate = interest_rate / (constTranslation * 100);
        totalRate = (float) Math.pow(1 + mountlyRate, loan_term * constTranslation);
        mountlyPayment = (loan_amount * mountlyRate * totalRate) / (totalRate - 1);
        remainingDebt = loan_amount - principalDebt;
        repaymentPercent = remainingDebt * mountlyRate;
        principalDebt = mountlyPayment - repaymentPercent;
        overPayment = mountlyPayment * loan_term * constTranslation - loan_amount;
        mortCalcul = new double [] {
                principalDebt,
                mountlyRate,
                totalRate,
                mountlyPayment,
                remainingDebt,
                repaymentPercent,
                overPayment};
        return mortCalcul;

    }
    void displayMortgageCalculator() {   // расчетные данные по ипотеке
        System.out.println(Constants.CALC_DATA.get("mountlyPaymentDict") +
                roundAvoid(mountlyPayment, 2));   // месячный платеж
        System.out.println(Constants.CALC_DATA.get("overPaymentDict") +
                roundAvoid(overPayment, 2));   // переплата
/*    }

    void repaymentSheudle() {   // график погашения ипотеки
        int n = 1;  // инициализация первого платежа
        double loanTermMonth = loanTerm * constTranslation;
        while (loanTermMonth > 0) {
            remainingDebt = loanAmount - principalDebt;
            if(loanTermMonth == 1) {
                remainingDebt = 0;
            }
            System.out.println(n + " " + roundAvoid(mountlyPayment, 2) + " " +
                    roundAvoid(repaymentPercent, 2) +
                    " " + roundAvoid(principalDebt, 2) + " " + roundAvoid(remainingDebt, 2));
            n++;
            loanTermMonth--;
            repaymentPercent = remainingDebt * mountlyRate;
            principalDebt = mountlyPayment - repaymentPercent;
            loanAmount = remainingDebt;

        }
    }*/
/*
    public static double roundAvoid(double value, int places) { // округление до сотых
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }*/
}