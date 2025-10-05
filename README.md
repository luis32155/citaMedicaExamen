# Citas Médicas – Backend (Spring Boot)

Servicio REST para gestionar **pacientes, médicos, especialidades, horarios y citas** con **MySQL**. Pensado para clases/examen: código limpio por capas, DTOs, mappers y validaciones básicas.

---

## 🧭 Arquitectura por capas

```
src/main/java/<paquete-raiz>/
 ├── controller/        # Endpoints REST (no reglas de negocio)
 ├── service/           # Interfaces (contratos)
 │   └── impl/          # Implementaciones (reglas, transacciones)
 ├── entity/            # Entidades JPA + enums (persistencia)
 ├── repository/        # Spring Data JPA (CRUD + queries)
 ├── dto/
 │   ├── request/       # ...Request (datos de entrada)
 │   └── response/      # ...Response (datos de salida)
 └── mapper/            # *MapperUtil (fromRequest/toResponse)
```
> Si tu paquete base es `pe.com.cibertec.citamedica`, ajusta los *packages* en el código según eso.

- **DTOs**: *request/response* desacoplan API ↔ dominio.
- **Mappers UtilityClass**: `fromRequest(...)`, `toResponse(...)` (Builder + Optional + @Nullable) → controlan qué expones y dejan controladores limpios.
- **ServiceImpl**: reglas de negocio + `@Transactional`.
- **Repository**: `JpaRepository` + consultas derivadas.
- **Controller**: orquesta HTTP ↔ servicio, sin lógica de negocio.

---

## 🧰 Stack técnico

- Java 17 · Spring Boot 3.x
- Spring Web, Spring Data JPA, Validation
- MySQL 8 (mysql-connector-j)
- Lombok (`@Data`, `@Builder`, etc.)

---

## ✅ Requisitos

- JDK 17
- Maven 3.9+
- MySQL 8 (o Docker)
- IDE con **annotation processing** habilitado (Lombok)

---

## ⚙️ Configuración

**`src/main/resources/application.yml` (local):**
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/citasdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Lima&useUnicode=true&characterEncoding=utf8
    username: citasuser
    password: citaspass
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update     # en prod: validate + Flyway
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.MySQL8Dialect
server:
  port: 8080
```

**(Opcional) `docker-compose.yml`:**
- Servicio MySQL + Adminer para inspección.
- Variables por defecto: DB `citasdb`, usuario `citasuser` / `citaspass`.

---

## 🚀 Puesta en marcha

### Opción A: con Docker (BD)
```bash
docker compose up -d
mvn spring-boot:run
```
> La app usará `jdbc:mysql://localhost:3306/citasdb` si corres localmente.

### Opción B: todo local
1. Crea BD/usuario en MySQL (o usa el compose).
2. Ajusta `application.yml` si cambias credenciales/puerto.
3. Ejecuta:
```bash
mvn clean spring-boot:run
```

---

## 🔌 Endpoints principales

### Pacientes
- `GET /api/pacientes`
- `POST /api/pacientes`
```json
{ "nombre":"Juan Perez", "dni":"12345678", "telefono":"999888777" }
```
- `PUT /api/pacientes/{id}`
- `DELETE /api/pacientes/{id}`

### Médicos
- `GET /api/medicos`
- `GET /api/medicos/especialidad/{id}`
- `POST /api/medicos`
```json
{ "nombre":"Dra. Ana Torres", "especialidadId":1 }
```
- `PUT /api/medicos/{id}`
- `DELETE /api/medicos/{id}`

### Especialidades
- `GET /api/especialidades`
- *(Opcional)* `POST /api/especialidades` si habilitas creación

### Horarios
- `GET /api/horarios/{medicoId}?fecha=YYYY-MM-DD`
- `POST /api/horarios`
```json
{ "fecha":"2025-10-10", "horaInicio":"09:00:00", "horaFin":"11:00:00", "medicoId":1 }
```
- `DELETE /api/horarios/{id}`

### Citas
- `POST /api/citas`
```json
{ "pacienteId":1, "medicoId":1, "fechaHora":"2025-10-10T09:30:00" }
```
- `DELETE /api/citas/{id}`   *(cancela)*
- `GET /api/citas/paciente/{id}`
- `GET /api/citas/medico/{id}`

---

## 🔄 Flujo para reservar

1) Crear **Paciente** → 2) Crear **Especialidad** → 3) Crear **Médico** (con especialidad)  
4) Crear **Horario** del médico → 5) **POST /api/citas** (valida conflictos y rango horario)

---

## 🧪 Postman y Diagrama

- Colección **Postman** (variables y tests): `citas-service-postman.json`  
  ➜ Descarga: `sandbox:/mnt/data/citas-service-postman.json`

- Diagrama **draw.io** (capas, entidades, relaciones): `citas-service-actualizado.drawio`  
  ➜ Descarga: `sandbox:/mnt/data/citas-service-actualizado.drawio`

---

## 🗃️ SQL útil (Especialidades)

```sql
CREATE TABLE IF NOT EXISTS especialidad (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uq_especialidad_nombre (nombre)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT IGNORE INTO especialidad (nombre) VALUES
('Medicina General'), ('Pediatría'), ('Dermatología'), ('Ginecología'), ('Cardiología');
```

---

## 🧱 Construcción (Maven)

Compilar:
```bash
mvn clean compile
```

Empaquetar:
```bash
mvn clean package
```

---

## 🩹 Troubleshooting

- **`builder() no existe`** → Asegura `@Builder` en tus `*Response` y Lombok con *annotation processing* habilitado (IDE) y versión en `pom.xml`.
- **Error de conexión MySQL** → Verifica URL/credenciales y que MySQL esté levantado.
- **Zonas horarias** → Ajusta `serverTimezone=America/Lima` y TZ del contenedor si usas Docker.

---

## 📌 Notas de diseño

- DTOs *request/response* desacoplan API ↔ dominio.
- Mappers *UtilityClass* simplifican conversiones y mantienen controladores limpios.
- `@Transactional` en escrituras garantiza consistencia.
- Reglas clave en **CitaServiceImpl**: existencia de entidades, no-conflicto y validación dentro del horario.
