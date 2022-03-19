package tis.springcommunityproject.service.member;

public class NotInputDataException extends RuntimeException {
  public NotInputDataException() {
  }

  public NotInputDataException(String message) {
    super(message);
  }
}
