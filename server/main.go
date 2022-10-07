package main

import (
	"github.com/Lalit3716/ideabook_server/app"
	"github.com/Lalit3716/ideabook_server/app/middlewares"
	"github.com/Lalit3716/ideabook_server/app/routes"
)

func main() {
	a := app.App{}
	a.Initialize()
	routes.SetupRoutes(&a)
	middlewares.SetupMiddlewares(&a)
	a.Run(":8080")
}
