/*
 * @author Tejas Dwarkaram
 * @version 1.5.0_9
 * @since  18 October 2012, 10:09
 *
 *
 *  fileName    BannerTag.java
 *  description creating a java file to create
 *              a custom tag, that will display 
 *              the banner on every jsp page
 */

package ForumService;

import java.io.IOException;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;

/**
 *
 * @author  Administrator
 * @version
 */

public class BannerTag extends TagSupport {
    
    /**Called by the container to invoke this tag.
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    public int doStartTag() throws JspException {
        try {
            //creating a new JspWriter
            JspWriter out = pageContext.getOut();
            //printing out the html code to display he image
            out.println("<img src=\"both.gif\" width=\"600px\" height=\"100px\"/>");
        } catch(IOException ioe) {
            System.out.println("Client Error -> IOException on FormatTag : " + ioe.getMessage());
        }
        return(EVAL_BODY_INCLUDE);
    }
    
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        return(EVAL_PAGE);
    }
}