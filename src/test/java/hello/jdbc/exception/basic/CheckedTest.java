package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@Slf4j
public class CheckedTest {
	
	@Test
	void checked_catch() {
		Service service = new Service();
		service.callCatch();
	}

	@Test
	void checked_throw() {
		Service service = new Service();
		assertThatThrownBy(() -> service.callThrow()).isInstanceOf(MyCheckedException.class);
	}

	/**
	 * Exception�� ��ӹ��� ���ܴ� üũ ���ܰ� �ȴ�.
	 */
	static class MyCheckedException extends Exception {
		public MyCheckedException(String message) {
			super(message);
		}
	}

	/**
	 * Checked ���ܴ� ���ܸ� ��Ƽ� ó���ϰų�, �����ų� ���� �ϳ��� �ʼ��� �����ؾ� �Ѵ�.
	 */
	static class Service {
		Repository repository = new Repository();

		/**
		 * ���ܸ� ��Ƽ� ó���ϴ� �ڵ�
		 */
		public void callCatch() {
			try {
				repository.call();
			} catch (MyCheckedException e) {
				// ���� ó�� ����
				log.info("���� ó��, message={}", e.getMessage(), e);
			}
		}

		/**
		 * üũ ���ܸ� ������ ������ �ڵ� üũ ���ܴ� ���ܸ� ���� �ʰ� ������ �������� throws ���ܸ� �޼��忡 �ʼ��� �����ؾ��Ѵ�.
		 */
		public void callThrow() throws MyCheckedException {
			repository.call();
		}
	}

	static class Repository {
		public void call() throws MyCheckedException {
			throw new MyCheckedException("ex");
		}
	}

}
