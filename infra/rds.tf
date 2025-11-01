// Banco PostgreSQL gerenciado (RDS) para uso didático
data "aws_vpc" "default" {
  default = true
}

resource "aws_security_group" "rds" {
  name        = "${var.app_name}-rds-sg"
  description = "Permite acesso externo ao PostgreSQL para fins de aula."
  vpc_id      = data.aws_vpc.default.id

  ingress {
    description = "Acesso ao PostgreSQL (ajuste para restringir em produção)"
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_db_instance" "postgres" {
  identifier              = "${var.app_name}-db"
  engine                  = "postgres"
  engine_version          = "15.4"
  instance_class          = "db.t3.micro" // Compatível com free tier
  allocated_storage       = 20
  db_name                 = "meusistema"
  username                = var.db_user
  password                = var.db_password
  publicly_accessible     = true // Simples para aula; considere usar SG específicos
  skip_final_snapshot     = true
  deletion_protection     = false
  backup_retention_period = 1
  vpc_security_group_ids  = [aws_security_group.rds.id]
}
