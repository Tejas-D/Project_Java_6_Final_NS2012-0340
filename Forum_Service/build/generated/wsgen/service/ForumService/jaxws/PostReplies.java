
package ForumService.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "postReplies", namespace = "http://ForumService/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "postReplies", namespace = "http://ForumService/", propOrder = {
    "userName",
    "userReply",
    "date",
    "commentId"
})
public class PostReplies {

    @XmlElement(name = "userName", namespace = "")
    private String userName;
    @XmlElement(name = "userReply", namespace = "")
    private String userReply;
    @XmlElement(name = "date", namespace = "")
    private String date;
    @XmlElement(name = "commentId", namespace = "")
    private int commentId;

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
    public String getUserReply() {
        return this.userReply;
    }

    /**
     * 
     * @param userReply
     *     the value for the userReply property
     */
    public void setUserReply(String userReply) {
        this.userReply = userReply;
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
    public int getCommentId() {
        return this.commentId;
    }

    /**
     * 
     * @param commentId
     *     the value for the commentId property
     */
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

}
