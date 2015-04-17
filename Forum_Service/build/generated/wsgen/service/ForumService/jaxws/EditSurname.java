
package ForumService.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "editSurname", namespace = "http://ForumService/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "editSurname", namespace = "http://ForumService/", propOrder = {
    "newSurname",
    "userName"
})
public class EditSurname {

    @XmlElement(name = "newSurname", namespace = "")
    private String newSurname;
    @XmlElement(name = "userName", namespace = "")
    private String userName;

    /**
     * 
     * @return
     *     returns String
     */
    public String getNewSurname() {
        return this.newSurname;
    }

    /**
     * 
     * @param newSurname
     *     the value for the newSurname property
     */
    public void setNewSurname(String newSurname) {
        this.newSurname = newSurname;
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
