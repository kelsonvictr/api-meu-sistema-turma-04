resource "aws_elastic_beanstalk_application" "backend" {
  name        = "${var.app_name}-backend"
  description = "Aplicação Elastic Beanstalk para o backend Spring Boot."
}

resource "aws_elastic_beanstalk_environment" "backend_env" {
  name          = "${var.app_name}-backend-env"
  application   = aws_elastic_beanstalk_application.backend.name
  platform_arn  = "arn:aws:elasticbeanstalk:${var.region}::platform/Corretto 17 running on 64bit Amazon Linux 2023/4.1.3"
  tier          = "WebServer"

  // Mantém as instâncias dentro do free tier
  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "InstanceType"
    value     = "t3.micro"
  }

  // Usa profile padrão criado automaticamente pela AWS (ajuste se a conta não tiver)
  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "IamInstanceProfile"
    value     = "aws-elasticbeanstalk-ec2-role"
  }

  // Variáveis de ambiente que serão alimentadas pelos recursos do RDS
  setting {
    namespace = "aws:elasticbeanstalk:application:environment"
    name      = "SPRING_DATASOURCE_URL"
    value     = "jdbc:postgresql://${aws_db_instance.postgres.address}:5432/${aws_db_instance.postgres.db_name}"
  }

  setting {
    namespace = "aws:elasticbeanstalk:application:environment"
    name      = "SPRING_DATASOURCE_USERNAME"
    value     = var.db_user
  }

  setting {
    namespace = "aws:elasticbeanstalk:application:environment"
    name      = "SPRING_DATASOURCE_PASSWORD"
    value     = var.db_password
  }

  // Configuração padrão do load balancer da Beanstalk (mantida simples)
  setting {
    namespace = "aws:elb:loadbalancer"
    name      = "ManagedActionsEnabled"
    value     = "true"
  }
}
