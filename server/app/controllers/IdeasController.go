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

	result := db.Find(&ideas)

	if result.Error != nil {
		utils.ResponseJSON(w, http.StatusInternalServerError, result.Error)
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

	result := db.First(&idea, id)

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

	result := db.Create(&idea)

	if result.Error != nil {
		utils.ResponseJSON(w, http.StatusInternalServerError, result.Error)
		return
	}

	utils.ResponseJSON(w, http.StatusCreated, idea)
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

	result := db.Model(&models.Idea{}).Where("id = ?", id).Updates(idea)

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

	result := db.Delete(&models.Idea{}, id)

	if result.Error != nil {
		utils.ResponseJSON(w, http.StatusInternalServerError, result.Error)
		return
	}

	utils.ResponseJSON(w, http.StatusOK, "Idea deleted successfully")
}