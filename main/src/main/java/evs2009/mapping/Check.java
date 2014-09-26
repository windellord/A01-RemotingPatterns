package evs2009.mapping;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Check {
	public List<String> getName() {
		return name;
	}

	@XmlElementWrapper(name = "check", namespace = "urn:ietf:params:xml:ns:obj")
	private final List<String> name = new ArrayList<String>();

	public Check() {
		// TODO Auto-generated constructor stub
	}

	public Check(String... names) {
		for (String name : names) {
			this.name.add(name);
		}
	}
}
