package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class UncheckedTest {
	
	@Test
	void unchecked_catch() {
		Service service = new Service();
		service.callCatch();
	}

	@Test
	void unchecked_throw() {
		Service service = new Service();
		assertThatThrownBy(() -> service.callThrow()).isInstanceOf(MyUncheckedException.class);
	}

	/**
	 * RuntimeException�� ��ӹ��� ���ܴ� ��üũ ���ܰ� �ȴ�.
	 */
	static class MyUncheckedException extends RuntimeException {
		public MyUncheckedException(String message) {
			super(message);
		}
	}

	/**
	 * UnChecked ���ܴ� ���ܸ� ��ų�, ������ �ʾƵ� �ȴ�. ���ܸ� ���� ������ �ڵ����� ������ ������.
	 */
	static class Service {
		Repository repository = new Repository();

		/**
		 * �ʿ��� ��� ���ܸ� ��Ƽ� ó���ϸ� �ȴ�.
		 */
		public void callCatch() {
			try {
				repository.call();
			} catch (MyUncheckedException e) {
				// ���� ó�� ����
				log.info("���� ó��, message={}", e.getMessage(), e);
			}
		}

		/**
		 * ���ܸ� ���� �ʾƵ� �ȴ�. �ڿ������� ������ �Ѿ��. üũ ���ܿ� �ٸ��� throws ���� ������ ���� �ʾƵ� �ȴ�.
		 */
		public void callThrow() {
			repository.call();
		}
	}

	static class Repository {
		public void call() {
			throw new MyUncheckedException("ex");
		}
	}
}