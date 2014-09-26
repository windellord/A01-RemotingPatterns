package evs2009.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Update {

	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private ObjectData update;

	public Update(ObjectData create) {
		super();
		this.update = create;
	}

	public Update() {
		// TODO Auto-generated constructor stub
	}

	public ObjectData getUpdate() {
		return update;
	}

}
