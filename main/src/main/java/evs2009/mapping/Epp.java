package evs2009.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "urn:ietf:params:xml:ns:epp-1.0")
@XmlAccessorType(XmlAccessType.FIELD)
public class Epp {

	private Command command;

	private Response response;

	public Response getResponse() {
		return response;
	}

	public Epp(Command command) {
		this.command = command;
	}

	public Epp(Response response) {
		super();
		this.response = response;
	}

	public Epp() {
		// TODO Auto-generated constructor stub
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
}
