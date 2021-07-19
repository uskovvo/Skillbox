import java.time.LocalDate;

public class Main {

  public static void main(String[] args) {
    LocalDate birthday = LocalDate.of(1995, 05, 23);
    System.out.println(getPeriodFromBirthday(birthday));
  }

  private static String getPeriodFromBirthday(LocalDate birthday) {
    LocalDate now = LocalDate.now();
    return birthday.until(now).getYears() + " years " + birthday.until(now).getMonths() + " months " +
            birthday.until(now).getDays() + " days";
  }

}
