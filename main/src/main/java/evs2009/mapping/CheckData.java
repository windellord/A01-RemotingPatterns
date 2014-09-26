package evs2009.mapping;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(namespace = "urn:ietf:params:xml:ns:obj")
@XmlAccessorType(XmlAccessType.FIELD)
public class CheckData {
	List<CheckDataInternal> cd = new ArrayList<CheckDataInternal>();

	public List<CheckDataInternal> getCd() {
		return cd;
	}

	public CheckData(CheckDataInternal... cds) {
		for (CheckDataInternal cd : cds) {
			this.cd.add(cd);
		}
	}

	public CheckData() {
		// TODO Auto-generated constructor stub
	}

	@XmlType(namespace = "urn:ietf:params:xml:ns:obj")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class CheckDataInternal {
		@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
		private CheckDataName name;

		@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
		private String reason;

		public CheckDataInternal(CheckDataName name, String reason) {
			super();
			this.name = name;
			this.reason = reason;
		}

		public CheckDataInternal() {
			// TODO Auto-generated constructor stub
		}
	}

	public static class CheckDataName {
		@XmlAttribute
		private Boolean avail;

		private String value;

		@XmlValue
		public String getValue() {
			return value;
		}

		public CheckDataName() {
			// TODO Auto-generated constructor stub
		}

		public CheckDataName(Boolean avail, String value) {
			super();
			this.avail = avail;
			this.value = value;
		}
	}
}
