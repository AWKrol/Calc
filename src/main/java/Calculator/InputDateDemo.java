package Calculator;

import Calculator.InputData;

import java.util.InputMismatchException;
import java.util.List;

public class InputDateDemo {
    public static void main(String [] args) {
        String fileName = "Dad_Fri_Feb_04_21_36_28_MSK_2022.txt";
        String fileNameTwo = "Andre_Thu_Feb_03_21_56_36_MSK_2022.txt";


        ConnectionDB outputData = new ConnectionDB();
        outputData.dataForCalculation(fileName);
        outputData.calculatorData();
        outputData.copyAddRepaymentScheduleFile(fileName);
        System.out.println(outputData.getClientIdFio());
        outputData.addClientBD();



        ConnectionDB outputDataTwo = new ConnectionDB();
        outputDataTwo.dataForCalculation(fileNameTwo);
        outputDataTwo.calculatorData();
        outputDataTwo.copyAddRepaymentScheduleFile(fileNameTwo);
        System.out.println(outputDataTwo.getClientIdFio());
        outputDataTwo.addClientBD();

        //outputDataTwo.deleteDataTablDB("client_data");
/*
        String filePath = "C:\\Users\\User\\IdeaProjects\\mortgageCalculator\\src\\main\\java\\Data\\mortgageData\\Dad_Fri_Feb_04_21_36_28_MSK_2022.txt";
        OutputData outputData = new OutputData();
        outputData.dataForCalculation(filePath);
        outputData.calculatorData();
        outputData.copyAddRepaymentScheduleFile(filePath);
        //outputData.deleteFile("C:\\Users\\User\\IdeaProjects\\mortgageCalculator\\src\\main\\java\\Data\\mortgageData\\Dady_Fri_Feb_04_21_29_54_MSK_2022.txt");
*/

        //inputMortgageDataFile("C:\\Users\\User\\IdeaProjects\\mortgageCalculator\\src\\main\\java\\Data\\mortgageData\\Dad_Fri_Feb_04_21_36_28_MSK_2022.txt");

        /*String fio;
        float loanTerm;
        float loanAmount;
        float interestRate;
        OutputData calculator;
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

        System.out.println("РАСЧЕТ:");
        calculator = new OutputData(fio, loanTerm, loanAmount, interestRate);
        calculator.calculatorData();
        calculator.displayInputData();
        calculator.displayMortgageCalculator();
        System.out.println();
        calculator.repaymentSheudleDisplay();
        calculator.mortgageDataFile();*/


        //OutputData.repaymentScheduleFile("C:\\Users\\User\\IdeaProjects\\mortgageCalculator\\src\\main\\java\\Data\\mortgageData\\Кролевецкий А.В._Thu_Feb_03_22_14_12_MSK_2022.txt");



        //calculator = new OutputData(fio, loanTerm, loanAmount, interestRate);









        /*System.out.println("РАСЧЕТ:");
        MortageCalculator indtdemo =
                new MortageCalculator(fio ,loanTerm, loanAmount, interestRate);
        indtdemo.displayInputData();
        indtdemo.displayMortagageCalculator();
        System.out.println();
        indtdemo.repaymentSheudle();*/
        // System.out.println();

        /*System.out.println("РАСЧЕТ С ФИО ЗАЕМЩИКА");
        MortageCalculator indtdemo =
                new MortageCalculator("Иванов Иван Иванович" ,20, 1465000, 9);
        indtdemo.displayInputData();
        indtdemo.displayMortagageCalculator();
        System.out.println();
        indtdemo.repaymentSheudle();
        System.out.println();

        System.out.println("РАСЧЕТ БЕЗ ФИО ЗАЕМЩИКА");
        MortageCalculator indtdemo1 =
                new MortageCalculator(15, 1000000, 7.5f);
        indtdemo1.displayInputData();
        indtdemo1.displayMortagageCalculator();
        System.out.println();
        indtdemo1.repaymentSheudle();
        System.out.println();*/

        /*indtdemo.display(indtdemo);
        float [] calc = indtdemo.calculator(indtdemo.loanTerm, indtdemo.loanAmount, indtdemo.interestRate);
        for(float x : calc) {
            System.out.println(x);
        }*/

      /*  System.out.println(dictInputDate);
        System.out.println(dictInputDate.get("loanTermDict"));*/

    }
}
