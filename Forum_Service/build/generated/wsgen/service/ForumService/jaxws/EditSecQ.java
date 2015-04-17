
package ForumService.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "editSecQ", namespace = "http://ForumService/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "editSecQ", namespace = "http://ForumService/", propOrder = {
    "newQues",
    "userName"
})
public class EditSecQ {

    @XmlElement(name = "newQues", namespace = "")
    private String newQues;
    @XmlElement(name = "userName", namespace = "")
    private String userName;

    /**
     * 
     * @return
     *     returns String
     */
    public String getNewQues() {
        return this.newQues;
    }

    /**
     * 
     * @param newQues
     *     the value for the newQues property
     */
    public void setNewQues(String newQues) {
        this.newQues = newQues;
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
