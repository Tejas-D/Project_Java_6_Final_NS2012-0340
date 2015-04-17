
package ForumService.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addUser", namespace = "http://ForumService/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addUser", namespace = "http://ForumService/", propOrder = {
    "name",
    "surname",
    "dob",
    "password",
    "email",
    "secQ",
    "secA"
})
public class AddUser {

    @XmlElement(name = "name", namespace = "")
    private String name;
    @XmlElement(name = "surname", namespace = "")
    private String surname;
    @XmlElement(name = "dob", namespace = "")
    private String dob;
    @XmlElement(name = "password", namespace = "")
    private String password;
    @XmlElement(name = "email", namespace = "")
    private String email;
    @XmlElement(name = "secQ", namespace = "")
    private String secQ;
    @XmlElement(name = "secA", namespace = "")
    private String secA;

    /**
     * 
     * @return
     *     returns String
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @param name
     *     the value for the name property
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * 
     * @param surname
     *     the value for the surname property
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getDob() {
        return this.dob;
    }

    /**
     * 
     * @param dob
     *     the value for the dob property
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 
     * @param password
     *     the value for the password property
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 
     * @param email
     *     the value for the email property
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getSecQ() {
        return this.secQ;
    }

    /**
     * 
     * @param secQ
     *     the value for the secQ property
     */
    public void setSecQ(String secQ) {
        this.secQ = secQ;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getSecA() {
        return this.secA;
    }

    /**
     * 
     * @param secA
     *     the value for the secA property
     */
    public void setSecA(String secA) {
        this.secA = secA;
    }

}
