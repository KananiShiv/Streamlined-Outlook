import java.util.GregorianCalendar;
import java.io.Serializable;

/**
 * The Email class represents an email message with various attributes such as
 * recipients, subject, body, and timestamp. It implements the Serializable interface
 * to allow objects of this class to be serialized.
 * 
 * Author: Shiv Kanani
 * SBU ID: 115171965
 * Homework #5 for CSE 214, Summer 2023
 */
public class Email implements Serializable {
	
	/**
     * The main recipient email address.
     */
    private String to;

    /**
     * The carbon copy recipients email addresses.
     */
    private String cc;

    /**
     * The blind carbon copy recipients email addresses.
     */
    private String bcc;

    /**
     * The subject of the email.
     */
    private String subject;

    /**
     * The body content of the email.
     */
    private String body;

    /**
     * The timestamp of when the email was created.
     */
    private GregorianCalendar timestamp;

    /**
     * Constructs an Email object with the specified attributes.
     * 
     * @param to      The recipient of the email.
     * @param cc      The carbon copy recipients of the email.
     * @param bcc     The blind carbon copy recipients of the email.
     * @param subject The subject of the email.
     * @param body    The body content of the email.
     */
    public Email(String to, String cc, String bcc, String subject, String body) {
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        this.timestamp = new GregorianCalendar();
    }

    /**
     * Gets the main recipient of the email.
     * 
     * @return The main recipient email address.
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the main recipient of the email.
     * 
     * @param to The main recipient email address.
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Gets the carbon copy recipients of the email.
     * 
     * @return The carbon copy recipients email addresses.
     */
    public String getCc() {
        return cc;
    }

    /**
     * Sets the carbon copy recipients of the email.
     * 
     * @param cc The carbon copy recipients email addresses.
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * Gets the blind carbon copy recipients of the email.
     * 
     * @return The blind carbon copy recipients email addresses.
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * Sets the blind carbon copy recipients of the email.
     * 
     * @param bcc The blind carbon copy recipients email addresses.
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * Gets the subject of the email.
     * 
     * @return The subject of the email.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the email.
     * 
     * @param subject The subject of the email.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets the body content of the email.
     * 
     * @return The body content of the email.
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body content of the email.
     * 
     * @param body The body content of the email.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Gets the timestamp of when the email was created.
     * 
     * @return The timestamp of the email.
     */
    public GregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of when the email was created.
     * 
     * @param timestamp The timestamp of the email.
     */
    public void setTimestamp(GregorianCalendar timestamp) {
        this.timestamp = timestamp;
    }
}
