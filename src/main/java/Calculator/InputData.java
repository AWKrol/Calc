package Calculator;

import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import static Calculator.Constants.CALC_DATA;
import static Calculator.OutputData.roundAvoid;


public class InputData extends Helper {   // входные данные
    String fio;   // ФИО заемщика
    double loanTerm;   // срок кредитования в годах
    double loanAmount;   // сумма ипотеки
    double interestRate;   // годовая процентная ставка

    double [] inputDateArray;

    static Scanner console = new Scanner(System.in);
    Properties properties = new Properties();

    public InputData() {
    }

    public InputData(String fio, double loanTerm, double loanAmount, double interestRate) {
        this.fio = fio;
        this.loanTerm = loanTerm;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
    }

    /**
     * метод получает с консоли входные данные для расчета ипотеки
     */

    public void addCalculationData() {
        while (true) {
            try {
                fio = InputData.getFio();
                loanTerm = InputData.getLoanTerm();
                loanAmount = InputData.getLoanAmount();
                interestRate = InputData.getInterestRate();
                break;

            } catch (InputMismatchException e) {
                System.out.println("Введено некорректное значение!!!" +
                        "Введите корректные данные.");
                String fail = InputData.getFail();
                continue;
            }
        }
    }

    void displayInputData() {   // вывод на консоль входных данных
        if(fio != null) {
            System.out.println(CALC_DATA.get("fioDict") + fio);
        }
        System.out.println(CALC_DATA.get("loanTermDict") + loanTerm);
        System.out.println(CALC_DATA.get("loanAmountDict") + loanAmount);
        System.out.println(CALC_DATA.get("interestRateDict") + roundAvoid(interestRate, 2));

    }

    static String getFio() {
        System.out.println("Введите ФИО заемщика");
        return console.nextLine();
    }

    static float getLoanTerm() {
        System.out.println("Введите срок кредитования в годах");
        return console.nextFloat();
    }

    static float getLoanAmount() {
        System.out.println("Введите сумму ипотеки");
        return console.nextFloat();
    }

    static float getInterestRate() {
        System.out.println("Введите годовую процентную ставку");
        return console.nextFloat();
    }

    static String getFail() {
        return console.nextLine();
    }

    public void setLoanTerm(double loanTerm) {
        this.loanTerm = loanTerm;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
