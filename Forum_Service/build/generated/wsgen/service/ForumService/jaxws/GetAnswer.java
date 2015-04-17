
package ForumService.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAnswer", namespace = "http://ForumService/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAnswer", namespace = "http://ForumService/")
public class GetAnswer {

    @XmlElement(name = "userAns", namespace = "")
    private String userAns;

    /**
     * 
     * @return
     *     returns String
     */
    public String getUserAns() {
        return this.userAns;
    }

    /**
     * 
     * @param userAns
     *     the value for the userAns property
     */
    public void setUserAns(String userAns) {
        this.userAns = userAns;
    }

}
