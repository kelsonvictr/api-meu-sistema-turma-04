// Variáveis mínimas para parametrizar a infraestrutura
variable "region" {
  description = "Região AWS onde os recursos serão criados."
  type        = string
  default     = "us-east-1"
}

variable "app_name" {
  description = "Nome base da aplicação (usado em Elastic Beanstalk, buckets, etc.)."
  type        = string
  default     = "meusistema"
}

variable "artifact_bucket_name" {
  description = "Nome do bucket S3 para uploads do backend (pode ser criado manualmente)."
  type        = string
  default     = "meusistema-backend-artifacts"
}

variable "db_user" {
  description = "Usuário administrador do banco PostgreSQL."
  type        = string
}

variable "db_password" {
  description = "Senha do usuário administrador do banco PostgreSQL."
  type        = string
  sensitive   = true
}
