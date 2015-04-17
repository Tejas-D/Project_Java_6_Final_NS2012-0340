
package ForumService.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getQuestion", namespace = "http://ForumService/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getQuestion", namespace = "http://ForumService/")
public class GetQuestion {

    @XmlElement(name = "userEmail", namespace = "")
    private String userEmail;

    /**
     * 
     * @return
     *     returns String
     */
    public String getUserEmail() {
        return this.userEmail;
    }

    /**
     * 
     * @param userEmail
     *     the value for the userEmail property
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
