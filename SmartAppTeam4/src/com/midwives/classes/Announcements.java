package com.midwives.classes;

import java.util.Date;

public class Announcements {
	
	private int announcementsId;
	private String announcementsNote;
	private Date announcementsDate;
	private boolean blockingStatus;
	
	Announcements(int id, String note, Date date, boolean status){
		this.announcementsId=id;
		this.announcementsNote=note;
		this.announcementsDate=date;
		this.blockingStatus=status;

	public String getAnnouncementsNote() {
		return this.announcementsNote;
	}

	public void setAnnouncementsNote(String announcementsNote) {
		this.announcementsNote = announcementsNote;
	}

	public int getAnnouncementsId() {
		return this.announcementsId;
	}
	public void setAnnouncementsId(int announcementsId) {
		this.announcementsId = announcementsId;
	}
	public Date getAnnouncementsDate() {
		return this.announcementsDate;
	}
	public void setAnnouncementsDate(Date announcementsDate) {
		this.announcementsDate = announcementsDate;
	}
	public boolean getBlockingStatus() {
		return this.blockingStatus;
	}
	public void setBlockingStatus(boolean blockingStatus) {
		this.blockingStatus = blockingStatus;
	}
	
	
	
	

}
