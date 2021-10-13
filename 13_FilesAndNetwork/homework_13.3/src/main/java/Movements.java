import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Movements {
    private final String dateFormat = "dd.MM.yyyy";
    private BankAccount bankAccount;
    private Date dateOfOperation;
    private String referenceWiring;
    private String operationDescription;
    private double coming;
    private double consumption;
    private String path;

    public Movements(String pathMovementsCsv) {
        this.path = pathMovementsCsv;
    }

    private Movements(BankAccount bankAccount, Date dateOfOperation, String referenceWiring,
                      String operationDescription, double coming, double consumption) {
        this.dateOfOperation = dateOfOperation;
        this.referenceWiring = referenceWiring;
        this.operationDescription = operationDescription;
        this.coming = coming;
        this.consumption = consumption;
    }

    public double getExpenseSum() {
        List<Movements> list = loadMovementsFromFile(path);
        return list.stream().mapToDouble(Movements::getConsumption).sum();
    }

    public double getIncomeSum() {
        List<Movements> list = loadMovementsFromFile(path);
        return list.stream().mapToDouble(Movements::getComing).sum();
    }

    public List<Movements> loadMovementsFromFile(String path) {
        List<Movements> movements = new ArrayList<>();
        String[] fragments;
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            lines.remove(0);
            for (String line : lines) {
                fragments = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                for(int a = 0; a < fragments.length; a++){
                    if(fragments[a].contains("\"")){
                        fragments[a] = fragments[a].replaceAll("\"", "");
                        if(a == 6 || a == 7){
                            fragments[a] = fragments[a].replaceAll(",", ".");
                        }
                    }
                }
                if (fragments.length != 8) {
                    continue;
                }
                movements.add(new Movements(new BankAccount(fragments[0], fragments[1], fragments[2]),
                        new SimpleDateFormat(dateFormat).parse(fragments[3]), fragments[4], fragments[5],
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

    private double getConsumption() {
        return consumption;
    }

    private void setConsumption(double consumption) {
        this.consumption = consumption;
    }
}
