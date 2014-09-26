package evs2009.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Create {

	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private ObjectData create;

	public Create(ObjectData create) {
		super();
		this.create = create;
	}

	public Create() {
		// TODO Auto-generated constructor stub
	}

	public ObjectData getCreate() {
		return create;
	}
}
