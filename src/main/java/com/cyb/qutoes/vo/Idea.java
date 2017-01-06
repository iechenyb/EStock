package com.cyb.qutoes.vo;


public class Idea {
	public String id = "";
	public Long userType=0l;
	public String phone="";
	public String email="";
	public Long ideaType=0l;
	public String message="";
    public String fileId ;
    public long submitTime=0l;
	public Long getUserType() {
		return userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdeaType() {
		return ideaType;
	}

	public void setIdeaType(Long ideaType) {
		this.ideaType = ideaType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String toString(){
		return this.email+"#"+this.ideaType+"#"+this.phone+"#"+this.userType+"#"+this.message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public long getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(long submitTime) {
		this.submitTime = submitTime;
	}
}
