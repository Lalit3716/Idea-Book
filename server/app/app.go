package app

import (
	"github.com/Lalit3716/ideabook_server/app/config"
	"github.com/gorilla/mux"
	"log"
	"net/http"
)

type App struct {
	Router *mux.Router
}

func (a *App) Initialize() {
	a.Router = mux.NewRouter()
	config.InitFirebaseApp()
}

func (a *App) Run(addr string) {
	go log.Printf("Server started on PORT %s\n\n", addr)
	err := http.ListenAndServe(addr, a.Router)
	if err != nil {
		log.Fatal("Could not start server :(")
	}
}
