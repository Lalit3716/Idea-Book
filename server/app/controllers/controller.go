package controllers

import (
	"github.com/Lalit3716/ideabook_server/utils"
	"net/http"
)

func GetHello(w http.ResponseWriter, _ *http.Request) {
	utils.ResponseJSON(w, http.StatusOK, "Hello World")
}
