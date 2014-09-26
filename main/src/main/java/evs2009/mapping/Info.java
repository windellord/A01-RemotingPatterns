package evs2009.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Info {

	public ObjectData getInfo() {
		return info;
	}

	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private ObjectData info;

	public Info(ObjectData create) {
		super();
		this.info = create;
	}

	public Info() {
		// TODO Auto-generated constructor stub
	}
}
