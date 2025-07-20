package com.hexaware.dto;

import com.hexaware.enums.UserStatus;

public class UserStatusUpdateDTO {
	private UserStatus status;

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
}
