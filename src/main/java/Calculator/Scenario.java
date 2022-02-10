package Calculator;

public class Scenario extends ConnectionDB {

    /**
     * Сценарий1: пришел клиент мы получили входные данные(ФИО, сумму, срок, процентную ставку),
     * расчитали ипотеку(месячный платеж, переплата). Клиент ушел думать. Под этот сценарий подходит:
     * клиент вернулся с новыми данными, расчитал, ушел думать.
     */
    public static void scenarioOne() {
        Scenario scenarioOne = new Scenario();
        scenarioOne.addCalculationData();
        scenarioOne.calculatorData();
        scenarioOne.mortgageDataFile();
        scenarioOne.addClientBD();
        scenarioOne.addDataMortgageBD();
    }

    /**
     * Сценарий2: пришел клиент мы получили входные данные(ФИО, сумму, срок, процентную ставку),
     * расчитали ипотеку(месячный платеж, переплата). Клиент оформил ипотеку.
     */
    public static void scenarioTwo() {
        Scenario scenarioTwo = new Scenario();
        scenarioTwo.addCalculationData();
        scenarioTwo.calculatorData();
        scenarioTwo.mortgageDataFile();
        scenarioTwo.addClientBD();
        scenarioTwo.addDataMortgageBD();
        scenarioTwo.copyAddRepaymentScheduleFile();
        scenarioTwo.addDataRepaymentBD();
    }

    public static void scenarioThree(String fileName) {
        Scenario scenarioThree = new Scenario();
        scenarioThree.dataForCalculation(fileName);
        scenarioThree.copyAddRepaymentScheduleFile(fileName);
        scenarioThree.addDataRepaymentBD(fileName);
    }

}
