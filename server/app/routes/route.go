package routes

import (
	"github.com/Lalit3716/ideabook_server/app"
	"github.com/Lalit3716/ideabook_server/app/controllers"
)

func SetupRoutes(a *app.App) {
	router := a.Router
	router.HandleFunc("/api/v1", controllers.GetHello).Methods("GET")
}
