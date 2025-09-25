# Account Balance Service 💰

![Java](https://img.shields.io/badge/Java-21-red?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.6-green?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Compose-blue?style=for-the-badge&logo=docker)

Serviço Spring Boot para gerenciamento de saldo de contas, desenvolvido com Java 21 e arquitetura moderna.

## 📋 Índice

- [Funcionalidades](#-funcionalidades)
- [Pré-requisitos](#-pré-requisitos)
- [🚀 Execução Rápida](#-execução-rápida)
- [🔧 Desenvolvimento](#-desenvolvimento)
- [📚 API Documentation](#-api-documentation)
- [🏗️ Arquitetura](#️-arquitetura)
- [⚙️ Configuração](#️-configuração)
- [🧪 Testes](#-testes)
- [📊 Monitoring](#-monitoring)

## ✨ Funcionalidades

- ✅ **Consulta de Saldos**
- ✅ **API RESTful** - Endpoints documentados com OpenAPI
- ✅ **Persistência** - Banco PostgreSQL com JPA
- ✅ **Containerização** - Docker e Docker Compose


## 📋 Pré-requisitos

Antes de executar a aplicação, certifique-se de ter instalado:

| Ferramenta | Versão | Download |
|------------|--------|----------|
| **Java** | 21+ | [Oracle JDK](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) |
| **Docker** | 20.10+ | [Docker Desktop](https://docs.docker.com/desktop/) |
| **Docker Compose** | 2.0+ | [Instalação](https://docs.docker.com/compose/install/) |
| **Maven** | 3.6+ | [Maven](https://maven.apache.org/) |

## 🚀 Execução Rápida

### Método 1: Docker Compose (Recomendado)

```bash
# Clone o repositório
git clone <seu-repositorio>
cd account-balance

# Execute o docker compose
docker-compose up -d