package comms;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "movie")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ResponseObject{
	
	@XmlElement(name = "Response")
	private String response;

	public ResponseObject(String response) {
		super();
		this.response = response;
	}
	
	public ResponseObject() {
		
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	
}
