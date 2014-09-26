package evs2009.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class TrnData {
	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private String name;

	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private String trStatus;

	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private String reId;

	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private String acId;

	public TrnData() {
		// TODO Auto-generated constructor stub
	}

	public TrnData(String name, String trStatus, String reId, String acId) {
		super();
		this.name = name;
		this.trStatus = trStatus;
		this.reId = reId;
		this.acId = acId;
	}
}
