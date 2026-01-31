
package com.enrique.xmlservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.xmlservice package. 
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

    private final static QName _DoAction_QNAME = new QName("http://example.com/xmlservice", "doAction");
    private final static QName _DoActionResponse_QNAME = new QName("http://example.com/xmlservice", "doActionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.xmlservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Params }
     * 
     */
    public Params createParams() {
        return new Params();
    }

    /**
     * Create an instance of {@link XmlRequest }
     * 
     */
    public XmlRequest createXmlRequest() {
        return new XmlRequest();
    }

    /**
     * Create an instance of {@link XmlResponse }
     * 
     */
    public XmlResponse createXmlResponse() {
        return new XmlResponse();
    }

    /**
     * Create an instance of {@link Params.Param }
     * 
     */
    public Params.Param createParamsParam() {
        return new Params.Param();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XmlRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XmlRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.com/xmlservice", name = "doAction")
    public JAXBElement<XmlRequest> createDoAction(XmlRequest value) {
        return new JAXBElement<XmlRequest>(_DoAction_QNAME, XmlRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XmlResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XmlResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://example.com/xmlservice", name = "doActionResponse")
    public JAXBElement<XmlResponse> createDoActionResponse(XmlResponse value) {
        return new JAXBElement<XmlResponse>(_DoActionResponse_QNAME, XmlResponse.class, null, value);
    }

}
