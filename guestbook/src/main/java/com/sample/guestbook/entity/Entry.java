package com.sample.guestbook.entity;

import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="entry")
public class Entry {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="user_id")
	private String userId;
 
	@Column(name="txt_entry")
	private String txtEntry;
	
	@Lob
	@Column(name="img_entry")
	private byte[] imgEntry;
	
	@Column(name="img_type")
	private String imgType;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update")
	private Date lastUpdate;
	
	@Column(name="is_approved")
	private int isApproved;
	
	public Entry() {
		
	}

	public Entry(int id, String userId, String txtEntry, byte[] imgEntry, String imgType, Date lastUpdate,
			int isApproved) {
		super();
		this.id = id;
		this.userId = userId;
		this.txtEntry = txtEntry;
		this.imgEntry = imgEntry;
		this.imgType = imgType;
		this.lastUpdate = lastUpdate;
		this.isApproved = isApproved;
	}



	public Entry(String userId, String txtEntry, byte[] imgEntry, String imgType, Date lastUpdate, int isApproved) {
	
		this.userId = userId;
		this.txtEntry = txtEntry;
		this.imgEntry = imgEntry;
		this.imgType = imgType;
		this.lastUpdate = lastUpdate;
		this.isApproved = isApproved;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTxtEntry() {
		return txtEntry;
	}

	public void setTxtEntry(String txtEntry) {
		this.txtEntry = txtEntry;
	}

	public byte[] getImgEntry() {
		return imgEntry;
	}

	public void setImgEntry(byte[] imgEntry) {
		this.imgEntry = imgEntry;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
	
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public String generateBase64Image()
	{
		String imgStr = defaultImg;
		if(this.getImgEntry() != null) {
			imgStr = Base64.getEncoder().encodeToString(this.getImgEntry());
			return imgStr;
		} 
		return defaultImg;
	}

	@Override
	public String toString() {
		return "Entry [userId=" + userId + ", txtEntry=" + txtEntry + ", imgEntry=" + Arrays.toString(imgEntry)
				+ ", imgType=" + imgType + ", lastUpdate=" + lastUpdate + ", isApproved="
				+ isApproved + "]";
	}
	
	static String defaultImg = "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";

	
	
}
