package models

import "gorm.io/gorm"

type Idea struct {
	gorm.Model
	Title       string    `json:"title"`
	Description string    `json:"description"`
	Color       int       `json:"color"`
	UserUid     string    `json:"user_id"`
	Username    string    `json:"username"`
	Likes       []Like    `json:"likes"`
	Comments    []Comment `json:"comments"`
	Tags        []Tag     `json:"tags" gorm:"many2many:idea_tags;"`
}
