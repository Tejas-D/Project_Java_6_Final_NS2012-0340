
package ForumService.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addTopic", namespace = "http://ForumService/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addTopic", namespace = "http://ForumService/", propOrder = {
    "user",
    "topicName",
    "topicDesc",
    "topicDate"
})
public class AddTopic {

    @XmlElement(name = "user", namespace = "")
    private String user;
    @XmlElement(name = "topicName", namespace = "")
    private String topicName;
    @XmlElement(name = "topicDesc", namespace = "")
    private String topicDesc;
    @XmlElement(name = "topicDate", namespace = "")
    private String topicDate;

    /**
     * 
     * @return
     *     returns String
     */
    public String getUser() {
        return this.user;
    }

    /**
     * 
     * @param user
     *     the value for the user property
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getTopicName() {
        return this.topicName;
    }

    /**
     * 
     * @param topicName
     *     the value for the topicName property
     */
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getTopicDesc() {
        return this.topicDesc;
    }

    /**
     * 
     * @param topicDesc
     *     the value for the topicDesc property
     */
    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getTopicDate() {
        return this.topicDate;
    }

    /**
     * 
     * @param topicDate
     *     the value for the topicDate property
     */
    public void setTopicDate(String topicDate) {
        this.topicDate = topicDate;
    }

}
