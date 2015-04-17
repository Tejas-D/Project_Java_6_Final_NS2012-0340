
package ForumService.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "editSecA", namespace = "http://ForumService/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "editSecA", namespace = "http://ForumService/", propOrder = {
    "newAns",
    "userName"
})
public class EditSecA {

    @XmlElement(name = "newAns", namespace = "")
    private String newAns;
    @XmlElement(name = "userName", namespace = "")
    private String userName;

    /**
     * 
     * @return
     *     returns String
     */
    public String getNewAns() {
        return this.newAns;
    }

    /**
     * 
     * @param newAns
     *     the value for the newAns property
     */
    public void setNewAns(String newAns) {
        this.newAns = newAns;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 
     * @param userName
     *     the value for the userName property
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
