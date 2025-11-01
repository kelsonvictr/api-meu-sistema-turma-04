// Define AWS provider and Terraform requirements
terraform {
  required_version = ">= 1.5.0"

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
    random = {
      source  = "hashicorp/random"
      version = "~> 3.5"
    }
  }
}

provider "aws" {
  region = var.region

  # Credenciais serão lidas de variáveis de ambiente:
  # AWS_ACCESS_KEY_ID e AWS_SECRET_ACCESS_KEY (configuradas como GitHub Secrets).
}
