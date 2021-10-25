import net.sf.saxon.style.XSLOutput;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movements {
    private final String DATE_FORMAT = "dd.MM.yyyy";
    private BankAccount bankAccount;
    private Date dateOfOperation;
    private String referenceWiring;
    private String operationDescription;
    private double coming;
    private double expense;
    private List<Movements> list;


    public Movements(String pathMovementsCsv) {
        list = loadMovementsFromFile(pathMovementsCsv);
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
        Map<String, Double> operations = new HashMap<>();
        for (Movements m : list) {
            if (m.getExpense() > 0) {
                String str = m.getOperationDescription();
                str = str.replaceAll("\\\\", "/");
                Pattern pattern = Pattern.compile("^.+[/\\\\](\\S+\\s?\\S+\\s?\\S+)\\s{2,}.+$");
                Matcher matcher = pattern.matcher(str);
                if(matcher.matches()){
                    str = matcher.group(1);
                }
                double value = m.getExpense();
                if(operations.containsKey(str)){
                    operations.put(str, operations.get(str) + value);
                }
                else {
                    operations.put(str, value);
                }
            }
        }
        for(Map.Entry entry: operations.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    private List<Movements> loadMovementsFromFile(String path) {
        List<Movements> movements = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            lines.remove(0);
            for (String line : lines) {
                String[] fragments = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
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
