package exceptions;

public class DSNullPointedException extends DSexception{

    public String message;

    public DSNullPointedException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
