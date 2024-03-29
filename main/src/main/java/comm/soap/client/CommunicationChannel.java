
package comm.soap.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.1 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "CommunicationChannel", targetNamespace = "http://soap.comm/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CommunicationChannel {


    /**
     * 
     * @param invoke
     * @return
     *     returns byte[]
     */
    @WebMethod
    @WebResult(name = "invokeResponse", targetNamespace = "http://soap.comm/", partName = "invokeResponse")
    public byte[] invoke(
        @WebParam(name = "invoke", targetNamespace = "http://soap.comm/", partName = "invoke")
        byte[] invoke);

}
