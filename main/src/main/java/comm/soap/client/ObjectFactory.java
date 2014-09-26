
package comm.soap.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the comm.soap.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _InvokeResponse_QNAME = new QName("http://soap.comm/", "invokeResponse");
    private final static QName _Invoke_QNAME = new QName("http://soap.comm/", "invoke");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: comm.soap.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.comm/", name = "invokeResponse")
    public JAXBElement<byte[]> createInvokeResponse(byte[] value) {
        return new JAXBElement<byte[]>(_InvokeResponse_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.comm/", name = "invoke")
    public JAXBElement<byte[]> createInvoke(byte[] value) {
        return new JAXBElement<byte[]>(_Invoke_QNAME, byte[].class, null, ((byte[]) value));
    }

}
