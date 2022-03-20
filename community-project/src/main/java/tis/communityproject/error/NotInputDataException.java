package tis.communityproject.error;

public class NotInputDataException extends RuntimeException {
  public NotInputDataException() {
  }

  public NotInputDataException(String message) {
    super(message);
  }
}
