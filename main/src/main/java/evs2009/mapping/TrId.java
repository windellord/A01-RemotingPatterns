package evs2009.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class TrId {
	private String clTRID;
	private String svTRID;

	public TrId(String clTRID, String svTRID) {
		super();
		this.clTRID = clTRID;
		this.svTRID = svTRID;
	}

	public TrId() {
	}

	public String getClTRID() {
		return clTRID;
	}

	public String getSvTRID() {
		return svTRID;
	}
}
