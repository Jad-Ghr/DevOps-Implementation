pipeline {
    agent any

    tools {
        jdk 'JDK11'
    }

    environment {
        PROJECT_DIR = "spring-boot-microservices-angular"
        PR_TARGET_BRANCH = "develop"
        // Optional GitLab environment variables for automatic merge request creation
        // Configure these in Jenkins credentials if you want MR creation.
        // GITLAB_API_URL = credentials('gitlab-api-url')
        // GITLAB_PROJECT_ID = credentials('gitlab-project-id')
        // GITLAB_TOKEN = credentials('gitlab-token')
        // SonarQube configuration
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_LOGIN = credentials('sonar-token')  // Configure 'sonar-token' in Jenkins credentials
    }

    stages {
        stage('Identify Feature Service') {
            steps {
                script {
                    def branchName = env.BRANCH_NAME ?: sh(script: 'git rev-parse --abbrev-ref HEAD', returnStdout: true).trim()
                    def serviceMap = [
                        answer: 'backend/answer-service',
                        'api-gateway': 'backend/api-gateway-service',
                        gateway: 'backend/api-gateway-service',
                        course: 'backend/course-service',
                        eureka: 'backend/eureka-service',
                        exam: 'backend/exam-service',
                        user: 'backend/user-service',
                        frontend: 'frontend'
                    ]

                    def selectedService = serviceMap.findResult { key, path ->
                        if (branchName =~ /(^|[\\/_-])${key}([\\/_-]|$)/) {
                            return path
                        }
                        return null
                    }

                    env.FEATURE_SERVICE = selectedService ?: ''
                    echo "Branch=${branchName}, FEATURE_SERVICE=${env.FEATURE_SERVICE ?: 'all services'}"
                }
            }
        }

        stage('Validate Feature Branch') {
            when {
                not {
                    branch 'feature/*'
                }
            }
            steps {
                error 'This Jenkinsfile is intended to run only on feature/* branches.'
            }
        }

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
            when {
                expression { env.FEATURE_SERVICE == '' || env.FEATURE_SERVICE == 'backend/answer-service' }
            }
            steps {
                dir("${PROJECT_DIR}/backend/answer-service") {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build gateway Service') {
            when {
                expression { env.FEATURE_SERVICE == '' || env.FEATURE_SERVICE == 'backend/api-gateway-service' }
            }
            steps {
                dir("${PROJECT_DIR}/backend/api-gateway-service") {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build course Service') {
            when {
                expression { env.FEATURE_SERVICE == '' || env.FEATURE_SERVICE == 'backend/course-service' }
            }
            steps {
                dir("${PROJECT_DIR}/backend/course-service") {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build eureka Service') {
            when {
                expression { env.FEATURE_SERVICE == '' || env.FEATURE_SERVICE == 'backend/eureka-service' }
            }
            steps {
                dir("${PROJECT_DIR}/backend/eureka-service") {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build exam Service') {
            when {
                expression { env.FEATURE_SERVICE == '' || env.FEATURE_SERVICE == 'backend/exam-service' }
            }
            steps {
                dir("${PROJECT_DIR}/backend/exam-service") {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build user Service') {
            when {
                expression { env.FEATURE_SERVICE == '' || env.FEATURE_SERVICE == 'backend/user-service' }
            }
            steps {
                dir("${PROJECT_DIR}/backend/user-service") {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Test answer Service') {
            when {
                expression { env.FEATURE_SERVICE == '' || env.FEATURE_SERVICE == 'backend/answer-service' }
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
                expression { env.FEATURE_SERVICE == '' || env.FEATURE_SERVICE == 'backend/api-gateway-service' }
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
                expression { env.FEATURE_SERVICE == '' || env.FEATURE_SERVICE == 'backend/course-service' }
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
                expression { env.FEATURE_SERVICE == '' || env.FEATURE_SERVICE == 'backend/eureka-service' }
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
                expression { env.FEATURE_SERVICE == '' || env.FEATURE_SERVICE == 'backend/exam-service' }
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
                expression { env.FEATURE_SERVICE == '' || env.FEATURE_SERVICE == 'backend/user-service' }
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

        stage('SonarQube Analysis') {
            when {
                expression { env.FEATURE_SERVICE == '' || env.FEATURE_SERVICE.contains('backend/') }
            }
            steps {
                script {
                    if (env.FEATURE_SERVICE == '') {
                        // Run for all backend services
                        def services = ['answer-service', 'api-gateway-service', 'course-service', 'eureka-service', 'exam-service', 'user-service']
                        services.each { service ->
                            dir("${PROJECT_DIR}/backend/${service}") {
                                sh "mvn clean verify sonar:sonar -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_LOGIN"
                            }
                        }
                    } else {
                        dir("${PROJECT_DIR}/${env.FEATURE_SERVICE}") {
                            sh "mvn clean verify sonar:sonar -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_LOGIN"
                        }
                    }
                }
            }
        }

        stage('Test Frontend') {
            when {
                expression { env.FEATURE_SERVICE == '' || env.FEATURE_SERVICE == 'frontend' }
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
                    echo 'Frontend tests finished'
                }
            }
        }

        stage('Build Frontend') {
            when {
                expression { env.FEATURE_SERVICE == '' || env.FEATURE_SERVICE == 'frontend' }
            }
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
            when {
                expression { env.FEATURE_SERVICE == '' }
            }
            steps {
                sh '''
                docker stop $(docker ps -aq) || true
                docker rm $(docker ps -aq) || true
                '''
            }
        }

        stage('Docker Build') {
            when {
                expression { env.FEATURE_SERVICE == '' }
            }
            steps {
                sh 'docker compose build'
            }
        }

        stage('Deploy Containers') {
            when {
                expression { env.FEATURE_SERVICE == '' }
            }
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
            script {
                if (env.BRANCH_NAME?.startsWith('feature/') && env.FEATURE_SERVICE) {
                    // GitLab MR creation commented out as credentials are not configured
                    // if (env.GITLAB_API_URL && env.GITLAB_PROJECT_ID && env.GITLAB_TOKEN) {
                    //     sh '''
                    //     curl -s -X POST "${GITLAB_API_URL}/projects/${GITLAB_PROJECT_ID}/merge_requests" \
                    //       -H "PRIVATE-TOKEN: ${GITLAB_TOKEN}" \
                    //       -d "source_branch=${BRANCH_NAME}" \
                    //       -d "target_branch=${PR_TARGET_BRANCH}" \
                    //       -d "title=Merge ${BRANCH_NAME} into ${PR_TARGET_BRANCH}"
                    //     '''
                    // } else {
                        echo 'Merge request creation skipped: GitLab credentials not configured.'
                    // }
                } else {
                    echo 'Not a feature branch or no feature service identified, skipping merge request creation.'
                }
            }
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
