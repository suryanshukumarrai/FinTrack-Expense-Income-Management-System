
# FinTrack — Expense & Income Management System

Full-stack expense/income tracker:
- **Backend:** Spring Boot (REST) + Spring Security + JWT + JPA (H2 in-memory by default)
- **Frontend:** React + Vite + TailwindCSS + Axios

## Requirements

- Java **21+** (project is configured for Java 21)
- Maven 3.9+
- Node.js 18+ and npm

## Quick Start (Dev)

### 1) Start Backend (Spring Boot)

```bash
cd backend
mvn clean package -DskipTests
java -jar target/expense-tracker-backend-0.0.1-SNAPSHOT.jar
```

Backend runs on:
- http://localhost:8080

### 2) Start Frontend (Vite)

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on:
- http://localhost:5173

## Authentication (JWT)

- Login/Signup returns a JWT.
- The frontend stores the token in `localStorage` under key `token`.
- Axios automatically attaches it as:
  - `Authorization: Bearer <token>`

## API Endpoints

Base URL: `http://localhost:8080/api`

### Auth
- `POST /auth/signup`
- `POST /auth/login`

Example login payload:
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

### Transactions (protected)
- `GET /transactions`
- `POST /transactions`
- `PUT /transactions/{id}`
- `DELETE /transactions/{id}`

> Note: Transaction routes require a valid JWT.

## Response Format

Most endpoints respond using an `ApiResponse` wrapper:

```json
{
  "success": true,
  "message": "...",
  "data": {}
}
```

Validation errors typically look like:

```json
{
  "success": false,
  "message": "Validation failed",
  "errors": {
    "email": "must not be blank",
    "password": "must not be blank"
  }
}
```

## Common Troubleshooting

### Frontend not loading
- Ensure you started Vite from the correct folder:
  - `cd frontend && npm run dev`
- Verify port 5173:
  - `lsof -i :5173`

### 401/403 on protected endpoints
- Make sure you are logged in and `localStorage.getItem('token')` is present.
- Confirm requests include `Authorization: Bearer <token>`.

### 400 Bad Request on login
- Backend expects JSON keys **exactly**: `email`, `password`.
- Check DevTools → Network → Request Payload.

### 500 on `GET /api/transactions`
- Ensure the backend was rebuilt with Java parameter metadata enabled.
- If you changed build settings, run:
  - `cd backend && mvn clean package -DskipTests`

## Optional: Docker (Backend)

If you have a Dockerfile in `backend/` (or you created one), you can build:

```bash
cd backend
docker build -t fintrack-backend:v1 .
```

## Project Structure

```
backend/   # Spring Boot API
frontend/  # React + Vite UI
```
