package exceptions;

public class EmptyCollectionException extends RuntimeException {
    public EmptyCollectionException(String collectionType){
        super(collectionType + " esta vazio.");
    }
}