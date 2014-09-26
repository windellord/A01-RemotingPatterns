package evs2009.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Login {
	// TODO svncs element adding
	private String clID = "";
	private String pw = "";

	private Options options = new Options();

	public Login() {
		// TODO Auto-generated constructor stub
	}

	public Login(String clID, String pw) {
		super();
		this.clID = clID;
		this.pw = pw;
	}

	public String getClID() {
		return clID;
	}

	public String getPw() {
		return pw;
	}

	public void setClID(String clID) {
		this.clID = clID;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	@XmlType
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Options {
		private String version = "1.0";
		private String lang = "en";
	}
}
