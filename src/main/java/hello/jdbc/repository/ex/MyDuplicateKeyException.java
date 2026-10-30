package hello.jdbc.repository.ex;

/**
 * MyDbException을 상송 받았으므로
 * 런타임 예외(=언체크 예외)
 * 
 * 데이터 중복인 경우에만 사용해야함
 */
public class MyDuplicateKeyException extends MyDbException {

    public MyDuplicateKeyException() {
    }

    public MyDuplicateKeyException(String message) {
        super(message);
    }

    public MyDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDuplicateKeyException(Throwable cause) {
        super(cause);
    }
}
