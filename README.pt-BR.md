# 📚 Livraria API – Projeto Java (Spring Boot & PostgreSQL)
🇧🇷 Português | 🇺🇸 [English](README.md)
### Sobre o projeto

Este projeto simula o gerenciamento completo de uma livraria digital, incluindo:

- Controle de livros e múltiplas cópias
- Aluguel de livros com prazo e cálculo de taxas de atraso
- Sistema de reservas para livros indisponíveis
- Autenticação e autorização com JWT
- Criptografia segura de senhas com BCrypt

O projeto foi desenvolvido como estudo avançado, com foco em boas práticas de arquitetura, segurança e persistência de dados.

### Tecnologias e Ferramentas
- Back-end: Java 17+, Spring Boot, Spring Security
- Persistência: JPA + Hibernate + PostgreSQL
- Segurança: JWT, BCrypt, Roles e permissões
- Build & Dependency Management: Maven
- Princípios de design: Camadas de Controller / Service / Repository / DTOs

### Arquitetura e Boas Práticas
O sistema segue arquitetura em camadas, garantindo separação de responsabilidades e escalabilidade:

- Controller Layer – API RESTful, validação de requisições, expõe endpoints.
- Service Layer – Contém regras de negócio, lógica de aluguel, reservas e cálculos de taxas.
- Repository Layer (JPA/Hibernate) – Acesso ao PostgreSQL, mapeamento de entidades e consultas complexas.
- Security Layer – JWT Authentication, autorização baseada em roles, hashing seguro de senhas.

## Padrões e boas práticas aplicados:

- Exception handling centralizado
- DTOs para separar modelo de dados do front-end
- Uso de Optional e Streams para manipulação segura de dados
- Validações de entrada com Bean Validation
  
### Funcionalidades Principais
- CRUD de livros e cópias
- Aluguel de livros com prazo e cálculo de multas
- Reservas quando estoque esgota
- Cadastro e autenticação de usuários com roles (ADMIN / USER)
- Segurança avançada: JWT + criptografia de senhas

### Possíveis melhorias
- Notificações de devolução próxima ou atraso.
- Histórico detalhado de empréstimos por usuário.
- Notificação automática quando um livro reservado estiver disponível.
- Melhorar interface de reviews com média de estrelas e comentários mais visíveis.
- Internacionalização para suportar vários idiomas.

------
## Diagrama de classes

<img width="5217" height="7080" alt="Library User Management-2026-03-21-094514" src="https://github.com/user-attachments/assets/9d5fc097-189e-443e-9f9c-ec667e2d4c02" />

