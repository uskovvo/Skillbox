public class WrongFormatException extends Exception{
    private final String str;

    public WrongFormatException(String message, String string) {
        super(message);
        str = string;
    }

    public String getStr(){
        return str;
    }
}
