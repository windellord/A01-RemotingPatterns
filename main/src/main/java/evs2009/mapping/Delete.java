package evs2009.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Delete {

	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private ObjectData delete;

	public Delete(ObjectData create) {
		super();
		this.delete = create;
	}

	public Delete() {
		// TODO Auto-generated constructor stub
	}

	public ObjectData getDelete() {
		return delete;
	}

}
