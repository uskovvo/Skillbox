import lombok.Getter;
import lombok.Setter;

public class Account {

    @Getter
    @Setter
    private long money;

    @Getter
    @Setter
    private String accNumber;

    @Getter
    @Setter
    private boolean blockedAcc;
}
