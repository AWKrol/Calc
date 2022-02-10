package Calculator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static Calculator.Constants.CALC_DATA;


public class OutputData extends InputData {

    final int constTranslation = 12;   // константа перевода года в месяцы
    double principalDebt = 0;   // погашение основного долга
    double mountlyRate;   // месячная процентная ставка
    double totalRate;   // общая процентная ставка
    double mountlyPayment;   // месячный платеж
    double remainingDebt;   // остаток долга
    double repaymentPercent;   // погашение процентов
    double overPayment;   // переплата
    double [] mortCalcul;

    String fileMortgageData;
    String fileRepaymentData;

    public OutputData() {
    }

    public OutputData(String fio, double loanTerm, double loanAmount, double interestRate) {
        super(fio, loanTerm, loanAmount, interestRate);
    }

    /**
     * метод расчитывает данные по ипотеке "РАСЧЕТНЫЕ"
     */
    public void calculatorData() {
        mountlyRate = interestRate / (constTranslation * 100);
        totalRate = (float) Math.pow(1 + mountlyRate, loanTerm * constTranslation);
        mountlyPayment = (loanAmount * mountlyRate * totalRate) / (totalRate - 1);
        remainingDebt = loanAmount - principalDebt;
        repaymentPercent = remainingDebt * mountlyRate;
        principalDebt = mountlyPayment - repaymentPercent;
        overPayment = mountlyPayment * loanTerm * constTranslation - loanAmount;
    }

    /**
     * метод выводит на консоль график погашения
     */
    void repaymentScheduleDisplay() {   // график погашения ипотеки
        double loanAmountCalculated = loanAmount;
        int n = 1;  // инициализация первого платежа
        double loanTermMonth = loanTerm * constTranslation;
        while (loanTermMonth > 0) {
            remainingDebt = loanAmountCalculated - principalDebt;
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
            loanAmountCalculated = remainingDebt;

        }
    }

    /**
     * метод сохраняет данные по ипотеке в файл, имя файла присваивается прерменной
     * экземпляра класа fileMortgageData
     */
    public void mortgageDataFile() {  // данные по ипотеке сохраняем в файл
        Date date = new Date();
        File file = new File(
                pathFile("") + fio + "_" + date.toString().replaceAll("[ :]", "_")+ ".txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(CALC_DATA.get("fioDict") + fio + "\n"
                + CALC_DATA.get("loanTermDict") + loanTerm + "\n"
                + CALC_DATA.get("loanAmountDict") + loanAmount + "\n"
                + CALC_DATA.get("interestRateDict") + roundAvoid(interestRate, 2) + "\n"
                + CALC_DATA.get("mountlyPaymentDict") + roundAvoid(mountlyPayment, 2) + "\n"
                + (CALC_DATA.get("overPaymentDict") + roundAvoid(overPayment, 2)));
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileMortgageData = file.getName();
    }



    /**
     * метод загружает данные из файла построчно в List
     */
     public List<String> inputMortgageDataFile(String fileName) {
        List<String> allLines = new ArrayList<>();
        try {
            allLines = Files.readAllLines(Paths.get(pathFile(fileName)), StandardCharsets.UTF_8);
            //for (String line : allLines) {
            //    System.out.println(line);
            //}
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(allLines);
        return allLines;
    }

    /**
     * Метод извлекает из файла ФИО заемщика
     */
    public String getFIOFromFile(String fileName) {
        String subStr;
        String fioFile = "";
        List<String> listData;
        listData = inputMortgageDataFile(fileName);
        for(String str : listData) {
            int index = str.indexOf(":");
            subStr = str.substring(0, index);
            //System.out.println(subStr);
            switch (subStr) {
                case ("ФИО заемщика"):
                    fioFile = str.substring(index + 1).trim();
                    break;
            }
        }
        if (fioFile == "") {
            System.out.println("В файле '"+fileName+"' не содержится ФИО заемщика!!!\n" +
                    "Выберете другой файл");
            System.exit(-1);
        }
        return fioFile;
    }

    /**
     * Метод извлекает из файла данные: ФИО, срок кредитования в годах, сумма ипотеки, годовая процентная ставка
     */
    public void dataForCalculation(String fileName) {
        String subStr;
        List<String> listData;
        listData = inputMortgageDataFile(fileName);
        for(String str : listData) {
            int index = str.indexOf(":");
            subStr = str.substring(0, index);
            //System.out.println(subStr);
            switch (subStr) {
                case ("ФИО заемщика"):
                    fio = str.substring(index + 1).trim();
                    break;
                case ("срок кредитования в годах"):
                    loanTerm = Float.parseFloat(str.substring(index + 1).trim());
                    break;
                case ("сумма ипотеки"):
                    loanAmount = Float.parseFloat(str.substring(index + 1).trim());
                    break;
                case ("годовая процентная ставка"):
                    interestRate = roundAvoid(Float.parseFloat(str.substring(index + 1).trim()), 2);
                    break;
            }
        }
        if (fio == "" || loanTerm <= 0 || loanAmount <= 0 || interestRate <= 0) {
            System.out.println("В файле '"+fileName+"' содержаться не все данные для расчета!!!\n" +
                    "Выберете другой файл");
            System.exit(-1);
        }
    }

    /**
     * метод сохраняет график погашения в List
     */

    public List<String> repaymentScheduleList() {
        List<String> repaymentList = new ArrayList<>();
        int n = 1;  // инициализация первого платежа
        repaymentList.add("\n");
        double loanTermMonth = loanTerm * constTranslation;
        while (loanTermMonth > 0) {
            remainingDebt = loanAmount - principalDebt;
            if(loanTermMonth == 1) {
                remainingDebt = 0;
            }
            repaymentList.add(n + " " + roundAvoid(mountlyPayment, 2) + " " +
                    roundAvoid(repaymentPercent, 2) +
                    " " + roundAvoid(principalDebt, 2) + " " + roundAvoid(remainingDebt, 2));
            n++;
            loanTermMonth--;
            repaymentPercent = remainingDebt * mountlyRate;
            principalDebt = mountlyPayment - repaymentPercent;
            loanAmount = remainingDebt;
        }
        return repaymentList;
    }

    /**
     * метод добавляет график погашения в файл с данными по ипотеке
     */
    public void addRepaymentScheduleFile(String filePath) {
        Path path = Paths.get(filePath);
        List<String> list = repaymentScheduleList();
        try {
            Files.write(path, list,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод добавляет график погашения в файл с данными по ипотеке,
     * имя файла берется из экземпляра класса
     */
    public void addRepaymentScheduleFile() {
        Path path = Paths.get(fileRepaymentData);
        List<String> list = repaymentScheduleList();
        try {
            Files.write(path, list,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * метод копирует файл с данными по ипотеки из одной директории в другую
     */
    public void copyFile(String fileName) {
        File start = new File(pathFile(fileName));
        File finish = new File(pathFile(fileName).replace("mortgageData", "repaymentSchedule"));
        try {
            Files.copy(start.toPath(), finish.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод копирует файл с данными по ипотеки из директории mortgageDat в repaymentSchedule,
     * имя файла берется из экзеипляра класса
     */
    public void copyFile() {
        // fileMortgageData почему то null ???
        System.out.println(fileMortgageData);
        File start = new File(pathFile(fileMortgageData));
        File finish = new File(pathFile(fileMortgageData).replace("mortgageData", "repaymentSchedule"));
        try {
            Files.copy(start.toPath(), finish.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * метод метод копирует файл с данными по ипотеки из одной директории в другую
     * и добавляет в файл график погашения в качестве параметра принимает имя файла
     */
    public void copyAddRepaymentScheduleFile(String fileName) {
        String newFilePath = pathFile(fileName).replace("mortgageData", "repaymentSchedule");
        copyFile(fileName);
        addRepaymentScheduleFile(newFilePath);
    }

    /**
     * метод метод копирует файл с данными по ипотеки из одной директории в другую
     * и добавляет в файл график погашения, имя файла берется из экземпляра класса
     * метод присваивает переменной класса fileRepaymentData значение
     */
    public void copyAddRepaymentScheduleFile() {
        System.out.println(fileMortgageData);
        String newFilePath = pathFile(fileMortgageData).replace("mortgageData", "repaymentSchedule");
        copyFile();
        addRepaymentScheduleFile(newFilePath);
        fileRepaymentData = fileMortgageData;
    }

    /**
     * метод удаляет файл
     */
    public void deleteFile(String fileName) {
        File file = new File(pathFile(fileName));
        file.delete();
    }

    /**
     * метод добавляет к файлу путь до директории по умолчанию
     */
    public String pathFile(String fileName) {
        File file = new File("C:\\Users\\User\\IdeaProjects\\mortgageCalculator\\src\\main\\resources\\application.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("directory.path") + fileName;
    }

    /**
     * метод округляет значение числа до сотых
     */
    public static double roundAvoid(double value, int places) { // округление до сотых
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }




    /**
     *НЕ НАДО
     */
    public static void repaymentScheduleFile(String filePath) {  // данные по ипотеке загружаем из файла и добавляем к ним график погашения
        OutputData outputData;
        List<String> mortgageDataList = new ArrayList<>();
        String subStr;
        String fio = "";
        float loanTerm = 0;
        float loanAmount = 0;
        float interestRate = 0;
        try {
            File file = new File(filePath);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line;// = reader.readLine();
            while (true) {
                // считываем остальные строки в цикле
                line = reader.readLine();
                if(line == null){
                    break;
                }
                System.out.println(line);
                mortgageDataList.add(line);
            }   //заменить код line = reader.readLine(); на listOfUrls.add(reader.readLine())
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(mortgageDataList);

        for(String str : mortgageDataList) {
            int index = str.indexOf(":");
            subStr = str.substring(0, index);
            System.out.println(subStr);
            switch (subStr) {
                case ("ФИО заемщика"):
                    fio = str.substring(index + 1).trim();
                    break;
                case ("срок кредитования в годах"):
                    loanTerm = Float.parseFloat(str.substring(index + 1).trim());
                    break;
                case ("сумма ипотеки"):
                    loanAmount = Float.parseFloat(str.substring(index + 1).trim());
                    break;
                case ("годовая процентная ставка"):
                    interestRate = Float.parseFloat(str.substring(index + 1).trim());
                    break;
            }
        }
        System.out.println(fio);
        System.out.println(loanTerm);
        System.out.println(loanAmount);
        System.out.println(interestRate);

        outputData = new OutputData(fio, loanTerm, loanAmount, interestRate); // Возможно не надо!!!???

        Date date = new Date();
        File file = new File(filePath);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.append("");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                roundAvoid(overPayment, 2));   // переплата*/
    }
}
