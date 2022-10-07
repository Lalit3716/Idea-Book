package config

import (
	"context"
	"fmt"

	firebase "firebase.google.com/go"
	"google.golang.org/api/option"
)

func InitFirebaseApp() *firebase.App {
	opt := option.WithCredentialsFile("../google-services.json")

	app, err := firebase.NewApp(context.Background(), nil, opt)

	if err != nil {
		fmt.Println("Error initializing firebase app:", err)
	}

	fmt.Println("Firebase app initialized successfully")
	return app
}
