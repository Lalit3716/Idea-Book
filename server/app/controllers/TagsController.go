package controllers

import (
	"github.com/Lalit3716/ideabook_server/app/models"
	"github.com/Lalit3716/ideabook_server/utils"
	"gorm.io/gorm"
	"net/http"
)

func GetTags(db *gorm.DB, w http.ResponseWriter, r *http.Request) {
	var tags []models.Tag

	db.Find(&tags)

	utils.ResponseJSON(w, http.StatusOK, tags)
}

func CreateTag(db *gorm.DB, w http.ResponseWriter, r *http.Request) {
	var tag models.Tag
	var name string

	err := utils.ParseJSON(r, &name)
	if err != nil {
		utils.ResponseJSON(w, http.StatusBadRequest, "Invalid request payload")
	}

	if name == "" {
		utils.ResponseJSON(w, http.StatusBadRequest, "Tag name is required")
		return
	}

	if e := db.Where("name = ?", name).First(&tag).Error; e == nil {
		utils.ResponseJSON(w, http.StatusBadRequest, "Tag already exists")
		return
	}

	tag.Name = name
	db.Create(&tag)

	utils.ResponseJSON(w, http.StatusCreated, tag)
}
