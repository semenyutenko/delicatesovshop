package exceptions;

public class DSDBexception extends DSexception {

    private final String message;

    public DSDBexception(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
