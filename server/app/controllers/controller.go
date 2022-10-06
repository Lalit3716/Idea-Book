package controllers

import (
	"net/http"

	"github.com/Lalit3716/ideabook_server/utils"
)

func GetHello(w http.ResponseWriter, _ *http.Request) {
	utils.ResponseJSON(w, http.StatusOK, "Hello World!!")
}
