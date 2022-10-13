package models

import "gorm.io/gorm"

type Tag struct {
	gorm.Model
	Name  string `gorm:"type:varchar(100);not null" json:"name"`
	Ideas []Idea `gorm:"many2many:idea_tags;" json:"ideas"`
}
