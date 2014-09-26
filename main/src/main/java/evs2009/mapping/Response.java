package evs2009.mapping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {
	private Result result;

	private ResData resData;

	private TrId trID;

	public Response() {
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public ResData getResData() {
		return resData;
	}

	public void setResData(ResData resData) {
		this.resData = resData;
	}

	public TrId getTrID() {
		return trID;
	}

	public void setTrID(TrId trID) {
		this.trID = trID;
	}

	public Response(Result result, TrId trID) {
		super();
		this.result = result;
		this.trID = trID;
	}

	public Response(Result result, ResData resData, TrId trID) {
		this(result, trID);
		this.resData = resData;
	}
}
