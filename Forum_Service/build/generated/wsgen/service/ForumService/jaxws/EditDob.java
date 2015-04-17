
package ForumService.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "editDob", namespace = "http://ForumService/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "editDob", namespace = "http://ForumService/", propOrder = {
    "newDob",
    "userName"
})
public class EditDob {

    @XmlElement(name = "newDob", namespace = "")
    private String newDob;
    @XmlElement(name = "userName", namespace = "")
    private String userName;

    /**
     * 
     * @return
     *     returns String
     */
    public String getNewDob() {
        return this.newDob;
    }

    /**
     * 
     * @param newDob
     *     the value for the newDob property
     */
    public void setNewDob(String newDob) {
        this.newDob = newDob;
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
