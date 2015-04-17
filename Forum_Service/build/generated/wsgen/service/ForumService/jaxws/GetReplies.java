
package ForumService.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getReplies", namespace = "http://ForumService/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getReplies", namespace = "http://ForumService/")
public class GetReplies {

    @XmlElement(name = "commentId", namespace = "")
    private String commentId;

    /**
     * 
     * @return
     *     returns String
     */
    public String getCommentId() {
        return this.commentId;
    }

    /**
     * 
     * @param commentId
     *     the value for the commentId property
     */
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

}
