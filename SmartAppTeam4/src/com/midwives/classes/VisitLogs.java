package com.midwives.classes;

/**
 * class handles serviceUser's visit notes ???...
 * @author kudlaty
 *
 */
public class VisitLogs {
	private String visitNote, visitDate;

	public VisitLogs(String visitDate, String visitNote) {
		this.visitNote = visitNote;
		this.visitDate = visitDate;
	}

	public String getVisitNote() {
		return visitNote;
	}

	public void setVisitNote(String visitNote) {
		this.visitNote = visitNote;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

}
