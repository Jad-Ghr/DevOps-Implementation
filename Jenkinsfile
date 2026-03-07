pipeline {
    agent any

    tools{
        jdk 'JDK11'
    }
    environment {
        PROJECT_DIR = "spring-boot-microservices-angular"
    }

    stages {

        stage('Build Common Modules') {
            steps {
                dir("${PROJECT_DIR}/backend/common-exam") {
                    sh 'mvn clean install -DskipTests'
                }

                dir("${PROJECT_DIR}/backend/common-service") {
                    sh 'mvn clean install -DskipTests'
                }

                dir("${PROJECT_DIR}/backend/common-student") {
                    sh 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Build answer Service') {
            steps {
                dir("${PROJECT_DIR}/backend/answer-service") {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build gateway Service') {
            steps {
                dir("${PROJECT_DIR}/backend/api-gateway-service") {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build course Service') {
            steps {
                dir("${PROJECT_DIR}/backend/course-service") {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build eureka Service') {
            steps {
                dir("${PROJECT_DIR}/backend/eureka-service") {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build exam Service') {
            steps {
                dir("${PROJECT_DIR}/backend/exam-service") {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build user Service') {
            steps {
                dir("${PROJECT_DIR}/backend/user-service") {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir("${PROJECT_DIR}/frontend") {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        stage('Docker Cleanup') {
            steps {
                sh '''
                docker stop $(docker ps -aq) || true
                docker rm $(docker ps -aq) || true
                '''
            }
        }


        stage('Docker Build') {
            steps {
                sh 'docker compose build'
            }
        }

        stage('Deploy Containers') {
            steps {
                sh '''
                docker compose down --remove-orphans || true
                docker compose up --build -d
                '''
            }
        }
    }
    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}