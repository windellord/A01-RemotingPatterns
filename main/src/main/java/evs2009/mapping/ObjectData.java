package evs2009.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class ObjectData {
	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private String name;

	public String getName() {
		return name;
	}

	public byte[] getMetaData() {
		return metaData;
	}
	
	public byte[] getData() {
		return data;
	}
	

	public String getRoid() {
		return roid;
	}

	public Boolean getOnlyMetadata() {
		if (onlyMetadata == null) {
			return false;
		}
		return onlyMetadata;
	}

	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private byte[] data;
	
	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private byte[] metaData;

	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private String roid;

	@XmlElement(namespace = "urn:ietf:params:xml:ns:obj")
	private Boolean onlyMetadata;

	public void setRoid(String roid) {
		this.roid = roid;
	}

	public ObjectData() {
	}

	public ObjectData(String name) {
		this.name = name;
	}

	public ObjectData(String name, boolean onlyMetadata) {
		this.name = name;
		if (onlyMetadata) {
			this.onlyMetadata = onlyMetadata;
		}
	}

	public ObjectData(String name, byte[] data) {
		super();
		this.name = name;
		this.data = data;
	}
	
	public ObjectData(String name, byte[] metaData, byte[] data) {
		super();
		this.name = name;
		this.metaData = metaData;
		this.data = data;
	}

	public ObjectData(String name, byte[] data, String roid) {
		super();
		this.name = name;
		this.data = data;
		this.roid = roid;
	}
}
