import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movements {
    private final String DATE_FORMAT = "dd.MM.yyyy";
    private final String template = "%s - %.2f";
    private BankAccount bankAccount;
    private Date dateOfOperation;
    private String referenceWiring;
    private String operationDescription;
    private double coming;
    private double expense;
    private String path;
    private List<Movements> list;


    public Movements(String pathMovementsCsv) {
        this.path = pathMovementsCsv;
        list = loadMovementsFromFile(path);
    }

    private Movements(BankAccount bankAccount, Date dateOfOperation, String referenceWiring,
                      String operationDescription, double coming, double expense) {
        this.dateOfOperation = dateOfOperation;
        this.referenceWiring = referenceWiring;
        this.operationDescription = operationDescription;
        this.coming = coming;
        this.expense = expense;
    }

    public double getExpenseSum() {
        return list.stream().mapToDouble(Movements::getExpense).sum();
    }

    public double getIncomeSum() {
        return list.stream().mapToDouble(Movements::getComing).sum();
    }

    public void expenseByOrg() {
        List<String> operations = new ArrayList<>();
        String[] oper;
        StringBuilder operation = new StringBuilder();
        for (Movements m : list) {
            if (m.getExpense() > 0) {
                String str = m.getOperationDescription();
//                oper = str.split("\\\\/");
//                if(str.equals("\\")) {
//                    int start = str.indexOf("\\");
//                    int end = str.lastIndexOf("\\");
//                    int end1 = str.indexOf(" ", end);
//                    str = str.substring(start, end1);
//                }else if(str.equals("/")){
//                    int start = str.indexOf("\\");
//                    int end = str.lastIndexOf("\\");
//                    int end1 = str.indexOf(" ", end);
//                    str = str.substring(start, end1);
//                }
                Pattern pattern = Pattern.compile("^(\\w+\\W+\\w+)(\\s+\\w+)");
                Matcher matcher = pattern.matcher(str);
                while (matcher.find()){
                    int start = matcher.start();
                    int end = matcher.end();
                    str = str.substring(start, end);
                }
//                oper = str.split("[\\\\][a-zA-Z0-9\\\\/]+");
//                for(int a = 1; a < oper.length; a++){
//                    operation.append(oper[a]).append(" ");
//                }
//                str = str.replaceAll("[0-9]", " ");
                String description = str + " - " + m.getExpense() + " руб.";
                operations.add(description);
            }
        }
        operations.stream().forEach(System.out::println);
    }

    private List<Movements> loadMovementsFromFile(String path) {
        List<Movements> movements = new ArrayList<>();
        String[] fragments;
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            lines.remove(0);
            for (String line : lines) {
                fragments = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                for (int a = 0; a < fragments.length; a++) {
                    if (fragments[a].contains("\"")) {
                        fragments[a] = fragments[a].replaceAll("\"", "");
                        if (a == 6 || a == 7) {
                            fragments[a] = fragments[a].replaceAll(",", ".");
                        }
                    }
                }
                if (fragments.length != 8) {
                    continue;
                }
                movements.add(new Movements(new BankAccount(fragments[0], fragments[1], fragments[2]),
                        new SimpleDateFormat(DATE_FORMAT).parse(fragments[3]), fragments[4], fragments[5],
                        Double.parseDouble(fragments[6]), Double.parseDouble(fragments[7])));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return movements;
    }

    private BankAccount getBankAccount() {
        return bankAccount;
    }

    private void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    private Date getDateOfOperation() {
        return dateOfOperation;
    }

    private void setDateOfOperation(Date dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    private String getReferenceWiring() {
        return referenceWiring;
    }

    private void setReferenceWiring(String referenceWiring) {
        this.referenceWiring = referenceWiring;
    }

    private String getOperationDescription() {
        return operationDescription;
    }

    private void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    private double getComing() {
        return coming;
    }

    private void setComing(double coming) {
        this.coming = coming;
    }

    private double getExpense() {
        return expense;
    }

    private void setConsumption(double consumption) {
        this.expense = consumption;
    }
}
