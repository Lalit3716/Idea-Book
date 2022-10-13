package utils

import (
	"github.com/Lalit3716/ideabook_server/app/models"
	"gorm.io/gorm"
	"strings"
)

func ParseTags(db *gorm.DB, tagsStr string, delimiter string) ([]models.Tag, error) {
	var tagArray []string
	var tags []models.Tag
	if tagsStr != "" {
		tagArray = strings.Split(tagsStr, delimiter)
	}

	for _, tag := range tagArray {
		var t models.Tag
		result := db.Where("name = ?", tag).First(&t)
		if result.Error != nil {
			return nil, result.Error
		}
		tags = append(tags, t)
	}

	return tags, nil
}

func FilterIdeas(ideas []models.Idea, search string) []models.Idea {
	search = strings.ToLower(search)
	for i, idea := range ideas {
		if !strings.Contains(strings.ToLower(idea.Title), search) && !strings.Contains(strings.ToLower(idea.Description), search) {
			ideas = append(ideas[:i], ideas[i+1:]...)
		}
	}

	return ideas
}
