
package ForumService.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "postComments", namespace = "http://ForumService/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "postComments", namespace = "http://ForumService/", propOrder = {
    "userName",
    "userComment",
    "date",
    "topicId"
})
public class PostComments {

    @XmlElement(name = "userName", namespace = "")
    private String userName;
    @XmlElement(name = "userComment", namespace = "")
    private String userComment;
    @XmlElement(name = "date", namespace = "")
    private String date;
    @XmlElement(name = "topicId", namespace = "")
    private int topicId;

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

    /**
     * 
     * @return
     *     returns String
     */
    public String getUserComment() {
        return this.userComment;
    }

    /**
     * 
     * @param userComment
     *     the value for the userComment property
     */
    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getDate() {
        return this.date;
    }

    /**
     * 
     * @param date
     *     the value for the date property
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     returns int
     */
    public int getTopicId() {
        return this.topicId;
    }

    /**
     * 
     * @param topicId
     *     the value for the topicId property
     */
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

}
