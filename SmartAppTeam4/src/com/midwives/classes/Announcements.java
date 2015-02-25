package com.midwives.classes;

import java.util.Date;

public class Announcements {
	
	private int announcementsId;
	private String announcementsNote;
	private String announcementsDate; //change from type date to type string - Bola
	private boolean blockingStatus;
	
	public Announcements(int id, String note, String date, boolean status){
		this.announcementsId=id;
		this.announcementsNote=note;
		this.announcementsDate=date;
		this.blockingStatus=status;
	} 
	
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
	public String getAnnouncementsDate() {
		return this.announcementsDate;
	}
	public void setAnnouncementsDate(String announcementsDate) {
		this.announcementsDate = announcementsDate;
	}
	public boolean getBlockingStatus() {
		return this.blockingStatus;
	}
	public void setBlockingStatus(boolean blockingStatus) {
		this.blockingStatus = blockingStatus;
	}
	
	
	
	

}
