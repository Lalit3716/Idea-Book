package app

import (
	firebase "firebase.google.com/go"
	"github.com/Lalit3716/ideabook_server/app/config"
	"github.com/gorilla/mux"
	"log"
	"net/http"
)

type App struct {
	Router      *mux.Router
	FirebaseApp *firebase.App
}

func (a *App) Initialize() {
	a.Router = mux.NewRouter()
	a.FirebaseApp = config.InitFirebaseApp()
}

func (a *App) Run(addr string) {
	go log.Printf("Server started on PORT %s\n\n", addr)
	err := http.ListenAndServe(addr, a.Router)
	if err != nil {
		log.Fatal("Could not start server :(")
	}
}
