# Client App Management Service (PoC)

## Running in IntelliJ
1. Open IntelliJ IDEA.
2. Select 'Open' and choose this project directory.
3. Let IntelliJ import the Gradle project.
4. Open `ClientAppManagementServiceApplication.java` and run it (right-click > Run).
5. The app will start on port 8080 by default.

## Testing with Postman

### Register Client
**POST** `http://localhost:8080/clients`

Request Body (JSON):
```
{
  "client_name": "Mobile Task App",
  "client_type": "mobile",
  "redirect_uris": ["https://app.example.com/callback"],
  "public_key": "-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqh...\n-----END PUBLIC KEY-----",
  "metadata": {
    "description": "iOS/Android mobile application",
    "contact_email": "dev@example.com"
  }
}
```

### Get Access Token
**POST** `http://localhost:8080/token`

Request Body (JSON):
```
{
  "grant_type": "client_credentials",
  "client_id": "client_123abc",
  "encrypted_credentials": {
    "key_id": "kms_key_123",
    "encrypted_value": "aGVsbG8gd29ybGQK...base64encoded...",
    "encryption_scheme": "AES-256-GCM",
    "iv": "base64encodediv"
  }
}
```

---

You can use these requests in Postman to test the endpoints after starting the app. 