package persistence;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class JDBCDriverTest {

	@Test
	public void test() {
		assertNotNull(JDBCDriver.getConnection());
	}

}
