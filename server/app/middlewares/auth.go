package middlewares

import (
	"fmt"
	"github.com/Lalit3716/ideabook_server/app/config"
	"net/http"
)

func authMiddleware(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		// Get auth token from header
		token := r.Header.Get("Authorization")
		// Check if token is valid
		idToken, err := config.Client.VerifyIDToken(*config.Ctx, token)

		if err != nil {
			http.Error(w, "Forbidden", http.StatusForbidden)
			return
		}

		fmt.Println("Verified ID token:", idToken)
		next.ServeHTTP(w, r)
	})
}