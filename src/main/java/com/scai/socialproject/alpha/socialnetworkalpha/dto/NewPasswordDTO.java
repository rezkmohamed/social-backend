package com.scai.socialproject.alpha.socialnetworkalpha.dto;

public class NewPasswordDTO {
	private String idProfile;
	private String newPassword;
	
	public NewPasswordDTO(String idProfile, String newPassword) {
		super();
		this.idProfile = idProfile;
		this.newPassword = newPassword;
	}
	
	public String getIdProfile() {
		return idProfile;
	}
	
	public void setIdProfile(String idProfile) {
		this.idProfile = idProfile;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	@Override
	public String toString() {
		return "NewPasswordDTO [idProfile=" + idProfile + ", newPassword=" + newPassword + "]";
	}
}
