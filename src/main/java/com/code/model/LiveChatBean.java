package com.code.model;

public class LiveChatBean {
	  public String getIp() {
			return ip;
		  }
		  public void setIp(String ip) {
			this.ip = ip;
		  }

		  private String ip="192.12.12.1";
		  private String recipient;
		  
		  public String getRecipient() { return recipient; }
		  public void setRecipient(String recipient) { this.recipient = recipient; }
		  
		  private String sender;
		  
		  public String getSender() { return sender; }
		  public void setSender(String sender) { this.sender = sender; }
		  
		  private String message;
		  
		  private String date = "2016120312";
		  
		  public String getDate() {return date;}
		  public void setDate(String date) {
			this.date = date;
		}
		public String getMessage() { return message; }
		  public void setMessage(String message) { this.message = message; }

}
