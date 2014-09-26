package evs2009;

/**
 *
 * @author  Michael Borko<michael@borko.at>,
 *          Florian Motlik<flomotlik@gmail.com>,
 *			Michael Greifeneder <mikegr@gmx.net>
 *
 */
public enum EppErrorCode {
	LOGIN_FAILED(100),
	NOT_LOGGED_IN(110),
	PERMISSION_DENIED(200),
	RESOURCE_NOT_FOUND(300),
	RESOURCE_EXISTS(400),
	RESOURCE_LOCKED(410),
	TOKEN_NOT_FOUND(500),
	Internal_Server_Error(600),
	ROLLBACK_FAILED(700),
	SERIALIZATION_ERROR(750),
	XML_ERROR(800)
	;

	private int code;
	private EppErrorCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
