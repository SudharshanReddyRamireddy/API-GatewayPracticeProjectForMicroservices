package SpringPractice.UserFeignClient.DTOs;

public class MailRequest_DTO {
	
	private String recipient_mail_id;
	private String subject;
	private String msgBody;
	private String attachment;
	public String getRecipient_mail_id() {
		return recipient_mail_id;
	}
	public void setRecipient_mail_id(String recipient_mail_id) {
		this.recipient_mail_id = recipient_mail_id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public MailRequest_DTO(String recipient_mail_id, String subject, String msgBody, String attachment) {
		super();
		this.recipient_mail_id = recipient_mail_id;
		this.subject = subject;
		this.msgBody = msgBody;
		this.attachment = attachment;
	}
	public MailRequest_DTO() {
		super();
	}
	

}
