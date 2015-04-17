
package ForumService.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "editPassword", namespace = "http://ForumService/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "editPassword", namespace = "http://ForumService/", propOrder = {
    "newPass",
    "userName"
})
public class EditPassword {

    @XmlElement(name = "newPass", namespace = "")
    private String newPass;
    @XmlElement(name = "userName", namespace = "")
    private String userName;

    /**
     * 
     * @return
     *     returns String
     */
    public String getNewPass() {
        return this.newPass;
    }

    /**
     * 
     * @param newPass
     *     the value for the newPass property
     */
    public void setNewPass(String newPass) {
        this.newPass = newPass;
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
