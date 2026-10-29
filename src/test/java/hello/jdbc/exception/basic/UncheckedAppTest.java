package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

@Slf4j
public class UncheckedAppTest {

    @Test
    void unchecked() {
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(() -> controller.request())
                .isInstanceOf(Exception.class);
    }

    @Test
    void printException() {
        Controller controller = new Controller();

        try {
            controller.request();
        } catch (Exception e) {
//            e.printStackTrace();
            log.info("ex", e); // 로그 출력시 마지막 파라미터에 예외를 넣어주면 로그에 스택 트레이스를 출력할 수 있음
        }
    }

    static class Controller {
        Service service  = new Service();

        public void request() {
            service.logic();
        }
    }

    static class Service {
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic() {
            repository.call();
            networkClient.call();
        }
    }

    static class NetworkClient {
        public void call() {
            throw new RuntimeConnectException("연결 실패");
        }
    }

    static class Repository {
        public void call() {
            try {
                runSQL();
            } catch (SQLException e) {
//                throw new RuntimeSQLException(); // 기존 예외(e)를 포함하지 않으면 확인이 불가능함
                throw new RuntimeSQLException(e);
            }
        }

        private void runSQL() throws SQLException {
            throw new SQLException("ex");
        }
    }

    static class RuntimeConnectException extends RuntimeException {
        public RuntimeConnectException(String message) {
            super(message);
        }
    }

    static class RuntimeSQLException extends RuntimeException {
        public RuntimeSQLException() {
        }

        // 예외 전환시 꼭! 기존 예외 포함할 것
        public RuntimeSQLException(Throwable cause) {
            super(cause);
        }
    }
}
