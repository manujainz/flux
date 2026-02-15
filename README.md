# Flux

**Flux** is a Netflix-like video streaming platform built as a full-stack, production-oriented system.

This project aims to replicate real-world streaming architecture including:

- Web client
- Android app
- iOS app
- Backend API
- Video processing pipeline
- Cloud infrastructure
- CDN-based streaming

Flux is not a demo project. It is built to simulate real production systems from day one.

---

# 🏗 Architecture Overview

Flux is structured as a monorepo:


### MVP Architecture (Phase 1)

- **Backend:** Java 17 + Spring Boot
- **Database:** PostgreSQL
- **Authentication:** Auth0 (JWT-based)
- **Storage:** AWS S3
- **CDN:** AWS CloudFront
- **CI/CD:** GitHub Actions
- **Deployment:** Docker + AWS

The backend is implemented as a **modular monolith** and may evolve into microservices in later phases.

---

