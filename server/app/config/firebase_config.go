package config

import (
	"context"
	"firebase.google.com/go/auth"
	"fmt"

	firebase "firebase.google.com/go"
)

var Client *auth.Client
var Ctx *context.Context

func InitFirebaseApp() {
	ctx := context.Background()
	app, err := firebase.NewApp(ctx, nil)

	if err != nil {
		fmt.Println("Error initializing app:", err)
	}

	client, e := app.Auth(ctx)
	if e != nil {
		fmt.Println("Error getting Auth client:", err)
	}

	fmt.Println("Firebase app initialized Successfully")

	Client = client
	Ctx = &ctx
}
