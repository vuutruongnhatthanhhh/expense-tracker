# 💰 Expense Tracker

A personal expense tracking app built with React Native, Spring Boot, and MongoDB.

---

## 🛠️ Tech Stack

| Layer    | Tech                |
| -------- | ------------------- |
| Mobile   | React Native (Expo) |
| Backend  | Spring Boot 4.x     |
| Database | MongoDB Atlas       |
| Auth     | JWT                 |
| Build    | EAS Build           |

---

## 📁 Project Structure

```
expense-tracker/
├── backend/        # Spring Boot API
└── frontend/       # React Native (Expo)
```

---

## ✨ Features

- Register / Login with JWT authentication
- Add, edit, and delete expenses
- Categorize expenses (Food, Transport, Shopping, Entertainment, Health, Other)
- Filter expenses by month, year, and category
- Statistics with bar chart (monthly) and pie chart (by category)
- Build APK and install directly on Android devices

---

## Demo

📺 Watch the full demo on YouTube:  
[Click to Watch Demo](https://www.youtube.com/watch?v=080oQLMAtt0)

---

## Project Overview

For a complete breakdown of features, architecture, and implementation details:  
[Read Full Project Documentation](https://portfolio.tjzenn.com/projects/flashcard)

Explore more of my work on my portfolio:  
[Visit My Portfolio](https://portfolio.tjzenn.com)

---

## 🚀 Getting Started

### Prerequisites

- JDK 21
- Node.js 18+
- MongoDB Atlas account
- Expo Go app (for testing on device)

---

### Backend

**1. Configure MongoDB**

Open `backend/src/main/resources/application.properties` and fill in your credentials:

```properties
spring.application.name=backend
spring.mongodb.uri=mongodb+srv://<username>:<password>@<cluster>.mongodb.net/<dbname>?retryWrites=true&w=majority
server.port=8080
```

**2. Run the backend**

Open the `backend/` folder in IntelliJ IDEA, wait for Maven to load dependencies, then click **Run**.

Or run via terminal:

```bash
cd backend
./mvnw spring-boot:run
```

The backend will be available at `http://localhost:8080`

---

### Frontend

**1. Install dependencies**

```bash
cd frontend
npm install
```

**2. Configure API base URL**

Open `frontend/src/services/api.ts` and update the IP:

```typescript
const api = axios.create({
  baseURL: "http://<YOUR_LOCAL_IP>:8080/api",
});
```

> Get your local IP by running `ipconfig` (Windows) or `ifconfig` (Mac/Linux) and use the IPv4 address.  
> Your computer and phone must be on the same Wi-Fi network.

**3. Start the app**

```bash
npx expo start
```

Scan the QR code with the **Expo Go** app on your phone.

---

### Build APK (Android)

```bash
cd frontend
eas build -p android --profile preview
```

Once the build is complete, EAS will provide a download link for the APK.

---

## 📡 API Endpoints

### Auth

| Method | Endpoint             | Description             |
| ------ | -------------------- | ----------------------- |
| POST   | `/api/auth/register` | Register a new user     |
| POST   | `/api/auth/login`    | Login and get JWT token |

### Expenses

| Method | Endpoint            | Description          |
| ------ | ------------------- | -------------------- |
| GET    | `/api/expenses`     | Get all expenses     |
| POST   | `/api/expenses`     | Create a new expense |
| PUT    | `/api/expenses/:id` | Update an expense    |
| DELETE | `/api/expenses/:id` | Delete an expense    |

> All Expense endpoints require the header: `Authorization: Bearer <token>`

---

## 👤 Author

**Vuu Truong Nhat Thanh**  
GitHub: [@vuutruongnhatthanhhh](https://github.com/vuutruongnhatthanhhh)
