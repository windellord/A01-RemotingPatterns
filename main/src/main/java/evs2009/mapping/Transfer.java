package evs2009.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Transfer {

	@XmlAttribute
	private String op;

	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private ObjectData transfer;

	public Transfer() {
		// TODO Auto-generated constructor stub
	}

	public Transfer(String op, ObjectData transfer) {
		super();
		this.op = op;
		this.transfer = transfer;
	}

	public TransferType getOp() {
		return TransferType.valueOf(op);
	}

	public ObjectData getTransfer() {
		return transfer;
	}

	public static enum TransferType {
		Request, Cancel, Execute;
	}
}
