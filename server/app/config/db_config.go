package config

type DBConfig struct {
	Dialect  string
	Host     string
	Port     int64
	Username string
	Password string
	Dbname   string
	Charset  string
}

func GetDBConfig() *DBConfig {
	return &DBConfig{
		Dialect:  "mysql",
		Host:     "localhost",
		Port:     3307,
		Username: "root",
		Password: "root",
		Dbname:   "ideabook",
		Charset:  "utf8mb4",
	}
}
