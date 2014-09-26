package evs2009;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationTests {

	private Peer peer;

	@Before
	public void setUp() throws Exception {
		@SuppressWarnings("unused")
		Application app1 = new Application("testsocket1");
		Application app2 = new Application("testsocket2");

		this.peer = app2.getPeerLookup().lookup("testsocket1");
	}

	@After
	public void tearDown() throws Exception {
		peer = null;
	}

	@Test
	public void correctLogin() throws Exception {
		peer.login(Helper.correctPassword, Helper.correctPassword);
		peer.logout();
	}

	@Test(expected = EppErrorException.class)
	public void wrongUserName() throws EppErrorException {
		peer.login("wrongUserName", "wrongPassword");
	}

	@Test(expected = EppErrorException.class)
	public void wrongPassword() throws EppErrorException {
		peer.login(Helper.correctUserName, "wrongPassword");
	}

	@Test(expected = EppErrorException.class)
	public void logoutNotLoggedIn() throws EppErrorException {
		peer.logout();
	}
}
