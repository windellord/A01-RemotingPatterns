package evs2009.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {
	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	@XmlAttribute
	private String code;

	private String msg;

	public Result() {
	}

	public Result(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
}
