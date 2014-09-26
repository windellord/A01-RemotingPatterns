package evs2009;

/**
 *
 * @author Michael Borko<michael@borko.at>, Florian Motlik<flomotlik@gmail.com>,
 *         Michael Greifeneder <mikegr@gmx.net>
 *
 */
public class EppErrorException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 5526126551270831971L;
	private final EppErrorCode code;

	public EppErrorException(EppErrorCode code) {
		super(codeMessage(code, ""));
		this.code = code;
	}
	
	public EppErrorException(EppErrorCode code, String message) {
		super(codeMessage(code, message));
		this.code = code;
	}

	public EppErrorException(EppErrorCode code, String message, Throwable cause) {
		super(codeMessage(code, message), cause);
		this.code = code;
	}

	public EppErrorCode getCode() {
		return code;
	}

	private static String codeMessage(EppErrorCode code, String message) {
		return code + ": " + message;
	}
}
