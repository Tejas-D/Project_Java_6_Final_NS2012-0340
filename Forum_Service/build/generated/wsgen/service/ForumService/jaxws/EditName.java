
package ForumService.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "editName", namespace = "http://ForumService/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "editName", namespace = "http://ForumService/", propOrder = {
    "newName",
    "userName"
})
public class EditName {

    @XmlElement(name = "newName", namespace = "")
    private String newName;
    @XmlElement(name = "userName", namespace = "")
    private String userName;

    /**
     * 
     * @return
     *     returns String
     */
    public String getNewName() {
        return this.newName;
    }

    /**
     * 
     * @param newName
     *     the value for the newName property
     */
    public void setNewName(String newName) {
        this.newName = newName;
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
