package models

import "gorm.io/gorm"

type Idea struct {
	gorm.Model
	Title          string `json:"title"`
	Description    string `json:"description"`
	UserUid        uint   `json:"user_id"`
	Username       string `json:"username"`
	UserProfilePic string `json:"user_profile_pic"`
}
