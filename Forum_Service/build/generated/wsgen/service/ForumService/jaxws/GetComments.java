
package ForumService.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getComments", namespace = "http://ForumService/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getComments", namespace = "http://ForumService/")
public class GetComments {

    @XmlElement(name = "topicName", namespace = "")
    private String topicName;

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

}
