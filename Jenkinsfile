pipeline {
    agent any

    tools{
        jdk 'JDK11'
    }
    environment {
        PROJECT_DIR = "spring-boot-microservices-angular"
        SONAR_HOST_URL = "http://localhost:9000"
        SONAR_LOGIN = "sqa_c89a3be8cf3b712f4b8ea4c905fafc9e0ee2c5b1"
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

        stage('Test answer Service') {
            when {
                anyOf {
                    changeset pattern: "${PROJECT_DIR}/backend/answer-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                }
            }
            steps {
                dir("${PROJECT_DIR}/backend/answer-service") {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Test gateway Service') {
            when {
                anyOf {
                    changeset pattern: "${PROJECT_DIR}/backend/api-gateway-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                }
            }
            steps {
                dir("${PROJECT_DIR}/backend/api-gateway-service") {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Test course Service') {
            when {
                anyOf {
                    changeset pattern: "${PROJECT_DIR}/backend/course-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                }
            }
            steps {
                dir("${PROJECT_DIR}/backend/course-service") {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Test eureka Service') {
            when {
                anyOf {
                    changeset pattern: "${PROJECT_DIR}/backend/eureka-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                }
            }
            steps {
                dir("${PROJECT_DIR}/backend/eureka-service") {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Test exam Service') {
            when {
                anyOf {
                    changeset pattern: "${PROJECT_DIR}/backend/exam-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                }
            }
            steps {
                dir("${PROJECT_DIR}/backend/exam-service") {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Test user Service') {
            when {
                anyOf {
                    changeset pattern: "${PROJECT_DIR}/backend/user-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                }
            }
            steps {
                dir("${PROJECT_DIR}/backend/user-service") {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('SonarQube Analysis - answer Service') {
            when {
                anyOf {
                    changeset pattern: "${PROJECT_DIR}/backend/answer-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                }
            }
            steps {
                dir("${PROJECT_DIR}/backend/answer-service") {
                    sh '''
                    mvn sonar:sonar \
                      -Dsonar.projectKey=answer-service \
                      -Dsonar.projectName="Answer Service" \
                      -Dsonar.sources=src/main \
                      -Dsonar.tests=src/test \
                      -Dsonar.host.url=${SONAR_HOST_URL} \
                      -Dsonar.login=${SONAR_LOGIN}
                    '''
                }
            }
        }
        
        stage('SonarQube Analysis - gateway Service') {
            when {
                anyOf {
                    changeset pattern: "${PROJECT_DIR}/backend/api-gateway-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                }
            }
            steps {
                dir("${PROJECT_DIR}/backend/api-gateway-service") {
                    sh '''
                    mvn sonar:sonar \
                      -Dsonar.projectKey=api-gateway-service \
                      -Dsonar.projectName="API Gateway Service" \
                      -Dsonar.sources=src/main \
                      -Dsonar.tests=src/test \
                      -Dsonar.host.url=${SONAR_HOST_URL} \
                      -Dsonar.login=${SONAR_LOGIN}
                    '''
                }
            }
        }
        
        stage('SonarQube Analysis - course Service') {
            when {
                anyOf {
                    changeset pattern: "${PROJECT_DIR}/backend/course-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                }
            }
            steps {
                dir("${PROJECT_DIR}/backend/course-service") {
                    sh '''
                    mvn sonar:sonar \
                      -Dsonar.projectKey=course-service \
                      -Dsonar.projectName="Course Service" \
                      -Dsonar.sources=src/main \
                      -Dsonar.tests=src/test \
                      -Dsonar.host.url=${SONAR_HOST_URL} \
                      -Dsonar.login=${SONAR_LOGIN}
                    '''
                }
            }
        }
        
        stage('SonarQube Analysis - eureka Service') {
            when {
                anyOf {
                    changeset pattern: "${PROJECT_DIR}/backend/eureka-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                }
            }
            steps {
                dir("${PROJECT_DIR}/backend/eureka-service") {
                    sh '''
                    mvn sonar:sonar \
                      -Dsonar.projectKey=eureka-service \
                      -Dsonar.projectName="Eureka Service" \
                      -Dsonar.sources=src/main \
                      -Dsonar.tests=src/test \
                      -Dsonar.host.url=${SONAR_HOST_URL} \
                      -Dsonar.login=${SONAR_LOGIN}
                    '''
                }
            }
        }
        
        stage('SonarQube Analysis - exam Service') {
            when {
                anyOf {
                    changeset pattern: "${PROJECT_DIR}/backend/exam-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                }
            }
            steps {
                dir("${PROJECT_DIR}/backend/exam-service") {
                    sh '''
                    mvn sonar:sonar \
                      -Dsonar.projectKey=exam-service \
                      -Dsonar.projectName="Exam Service" \
                      -Dsonar.sources=src/main \
                      -Dsonar.tests=src/test \
                      -Dsonar.host.url=${SONAR_HOST_URL} \
                      -Dsonar.login=${SONAR_LOGIN}
                    '''
                }
            }
        }
        
        stage('SonarQube Analysis - user Service') {
            when {
                anyOf {
                    changeset pattern: "${PROJECT_DIR}/backend/user-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                }
            }
            steps {
                dir("${PROJECT_DIR}/backend/user-service") {
                    sh '''
                    mvn sonar:sonar \
                      -Dsonar.projectKey=user-service \
                      -Dsonar.projectName="User Service" \
                      -Dsonar.sources=src/main \
                      -Dsonar.tests=src/test \
                      -Dsonar.host.url=${SONAR_HOST_URL} \
                      -Dsonar.login=${SONAR_LOGIN}
                    '''
                }
            }
        }
        
        stage('Test Frontend') {
            when {
                changeset pattern: "${PROJECT_DIR}/frontend/**", comparator: 'GLOB'
            }
            agent {
                docker {
                    image 'node:18-bullseye'
                    args '-u root'
                }
            }
            steps {
                dir("${PROJECT_DIR}/frontend") {
                    sh 'apt-get update && apt-get install -y firefox-esr'
                    sh 'npm install'
                    sh 'npm test -- --watch=false --browsers=FirefoxHeadless --reporters=progress,junit'
                }
            }
            post {
                always {
                    echo 'Tests finished successfully'
                }
            }
        }
        
        stage('Build Frontend') {
            agent {
                docker {
                    image 'node:18-bullseye'
                    args '-u root'
                }
            }
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
            echo 'Pipeline executed successfully!!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}