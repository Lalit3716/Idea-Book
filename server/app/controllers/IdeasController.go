package controllers

import (
	"github.com/Lalit3716/ideabook_server/app/models"
	"github.com/Lalit3716/ideabook_server/utils"
	"github.com/gorilla/mux"
	"gorm.io/gorm"
	"net/http"
	"strconv"
)

func GetIdeas(db *gorm.DB, w http.ResponseWriter, _ *http.Request) {
	var ideas []models.Idea

	err := db.Model(&models.Idea{}).Preload("Likes").Find(&ideas).Error

	if err != nil {
		utils.ResponseJSON(w, http.StatusInternalServerError, err)
		return
	}

	utils.ResponseJSON(w, http.StatusOK, ideas)
}

func GetIdea(db *gorm.DB, w http.ResponseWriter, r *http.Request) {
	params := mux.Vars(r)

	id, err := strconv.Atoi(params["id"])

	if err != nil {
		utils.ResponseJSON(w, http.StatusBadRequest, err)
		return
	}

	var idea models.Idea

	result := db.Model(&models.Idea{}).Preload("Likes").First(&idea, id)

	if result.Error != nil {
		utils.ResponseJSON(w, http.StatusInternalServerError, result.Error)
		return
	}

	utils.ResponseJSON(w, http.StatusOK, idea)
}

func CreateIdea(db *gorm.DB, w http.ResponseWriter, r *http.Request) {
	var idea models.Idea

	if err := utils.ParseJSON(r, &idea); err != nil {
		utils.ResponseJSON(w, http.StatusBadRequest, err)
		return
	}

	uid := r.Context().Value("uid").(string)
	idea.UserUid = uid

	username := r.Context().Value("username").(string)
	idea.Username = username

	result := db.Create(&idea)

	if result.Error != nil {
		utils.ResponseJSON(w, http.StatusInternalServerError, result.Error)
		return
	}

	utils.ResponseJSON(w, http.StatusCreated, "Idea created successfully")
}

func UpdateIdea(db *gorm.DB, w http.ResponseWriter, r *http.Request) {
	params := mux.Vars(r)

	id, err := strconv.Atoi(params["id"])

	if err != nil {
		utils.ResponseJSON(w, http.StatusBadRequest, err)
		return
	}

	var idea models.Idea

	if err := utils.ParseJSON(r, &idea); err != nil {
		utils.ResponseJSON(w, http.StatusBadRequest, err)
		return
	}

	// Find the idea
	result := db.First(&idea, id)

	if result.Error != nil {
		utils.ResponseJSON(w, http.StatusNotFound, "Idea not found")
		return
	}

	// Check if the user is the owner of the idea
	uid := r.Context().Value("uid").(string)

	if idea.UserUid != uid {
		utils.ResponseJSON(w, http.StatusUnauthorized, "Unauthorized")
		return
	}

	// Update the idea
	result = db.Model(&idea).Updates(idea)

	if result.Error != nil {
		utils.ResponseJSON(w, http.StatusInternalServerError, result.Error)
		return
	}

	utils.ResponseJSON(w, http.StatusOK, "Idea updated successfully")
}

func DeleteIdea(db *gorm.DB, w http.ResponseWriter, r *http.Request) {
	params := mux.Vars(r)

	id, err := strconv.Atoi(params["id"])

	if err != nil {
		utils.ResponseJSON(w, http.StatusBadRequest, err)
		return
	}

	// Find the idea
	var idea models.Idea

	result := db.First(&idea, id)

	if result.Error != nil {
		utils.ResponseJSON(w, http.StatusNotFound, "Idea not found")
		return
	}

	// Check if the user is the owner of the idea
	uid := r.Context().Value("uid").(string)

	if idea.UserUid != uid {
		utils.ResponseJSON(w, http.StatusUnauthorized, "Unauthorized")
		return
	}

	// Delete the idea
	result = db.Delete(&idea)

	if result.Error != nil {
		utils.ResponseJSON(w, http.StatusInternalServerError, result.Error)
		return
	}

	utils.ResponseJSON(w, http.StatusOK, "Idea deleted successfully")
}
