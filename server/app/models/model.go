package models

import (
	"gorm.io/gorm"
	"log"
)

func DBMigrate(db *gorm.DB) *gorm.DB {
	err := db.AutoMigrate(&Idea{}, &Like{})
	if err != nil {
		log.Fatal("Could not migrate the tables")
	}

	return db
}
