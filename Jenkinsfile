// Small helper predicates for cleaner `when { expression { ... } }` logic.
// These are evaluated by Jenkins at runtime (when `env` is available).
def isFeatureBranch = { -> return env.BRANCH_NAME?.startsWith('feature/') }
def isReleaseBranch = { -> return env.BRANCH_NAME?.startsWith('release/') }
def isMainBranch = { -> return env.BRANCH_NAME == 'main' }

pipeline {
    agent any

    tools{
        // Use the JDK installation configured in Jenkins global tools.
        // Change this to JDK11 or JDK17 based on your Jenkins tool configuration.
        jdk 'JDK11'
    }
    environment {
        PROJECT_DIR = "spring-boot-microservices-angular"
    }

    stages {


        stage('Build Common Modules') {
            when {
                anyOf {
                    allOf {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                        anyOf {
                            // Feature branches build common libraries when any microservice changed
                            changeset pattern: "${PROJECT_DIR}/backend/answer-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/api-gateway-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/course-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/eureka-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/exam-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/user-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                        }
                    }
                    not {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                    }
                }
            }
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

        stage('Build Services Parallel') {
            parallel {
                stage('Answer Service') {
                    when {
                        anyOf {
                            allOf {
                                expression { env.BRANCH_NAME?.startsWith('feature/') }
                                anyOf {
                                    changeset pattern: "${PROJECT_DIR}/backend/answer-service/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                                }
                            }
                            not {
                                expression { env.BRANCH_NAME?.startsWith('feature/') }
                            }
                        }
                    }
                    steps {
                        dir("${PROJECT_DIR}/backend/answer-service") {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }

                stage('Gateway Service') {
                    when {
                        anyOf {
                            allOf {
                                expression { env.BRANCH_NAME?.startsWith('feature/') }
                                anyOf {
                                    changeset pattern: "${PROJECT_DIR}/backend/api-gateway-service/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                                }
                            }
                            not {
                                expression { env.BRANCH_NAME?.startsWith('feature/') }
                            }
                        }
                    }
                    steps {
                        dir("${PROJECT_DIR}/backend/api-gateway-service") {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }

                stage('Course Service') {
                    when {
                        anyOf {
                            allOf {
                                expression { env.BRANCH_NAME?.startsWith('feature/') }
                                anyOf {
                                    changeset pattern: "${PROJECT_DIR}/backend/course-service/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                                }
                            }
                            not {
                                expression { env.BRANCH_NAME?.startsWith('feature/') }
                            }
                        }
                    }
                    steps {
                        dir("${PROJECT_DIR}/backend/course-service") {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }

                stage('Eureka Service') {
                    when {
                        anyOf {
                            allOf {
                                expression { env.BRANCH_NAME?.startsWith('feature/') }
                                anyOf {
                                    changeset pattern: "${PROJECT_DIR}/backend/eureka-service/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                                }
                            }
                            not {
                                expression { env.BRANCH_NAME?.startsWith('feature/') }
                            }
                        }
                    }
                    steps {
                        dir("${PROJECT_DIR}/backend/eureka-service") {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }

                stage('Exam Service') {
                    when {
                        anyOf {
                            allOf {
                                expression { env.BRANCH_NAME?.startsWith('feature/') }
                                anyOf {
                                    changeset pattern: "${PROJECT_DIR}/backend/exam-service/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                                }
                            }
                            not {
                                expression { env.BRANCH_NAME?.startsWith('feature/') }
                            }
                        }
                    }
                    steps {
                        dir("${PROJECT_DIR}/backend/exam-service") {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }

                stage('User Service') {
                    when {
                        anyOf {
                            allOf {
                                expression { env.BRANCH_NAME?.startsWith('feature/') }
                                anyOf {
                                    changeset pattern: "${PROJECT_DIR}/backend/user-service/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                                    changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                                }
                            }
                            not {
                                expression { env.BRANCH_NAME?.startsWith('feature/') }
                            }
                        }
                    }
                    steps {
                        dir("${PROJECT_DIR}/backend/user-service") {
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }
            }
        }

        stage('Test answer Service') {
            when {
                anyOf {
                    // On feature branches, run only if impacted files changed
                    allOf {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                        anyOf {
                            changeset pattern: "${PROJECT_DIR}/backend/answer-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                        }
                    }
                    // On non-feature branches (develop, release, main...), always run
                    not {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                    }
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
                    allOf {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                        anyOf {
                            changeset pattern: "${PROJECT_DIR}/backend/api-gateway-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                        }
                    }
                    not {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                    }
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
                    allOf {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                        anyOf {
                            changeset pattern: "${PROJECT_DIR}/backend/course-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                        }
                    }
                    not {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                    }
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
                    allOf {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                        anyOf {
                            changeset pattern: "${PROJECT_DIR}/backend/eureka-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                        }
                    }
                    not {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                    }
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
                    allOf {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                        anyOf {
                            changeset pattern: "${PROJECT_DIR}/backend/exam-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                        }
                    }
                    not {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                    }
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
                    allOf {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                        anyOf {
                            changeset pattern: "${PROJECT_DIR}/backend/user-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-exam/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-service/**", comparator: 'GLOB'
                            changeset pattern: "${PROJECT_DIR}/backend/common-student/**", comparator: 'GLOB'
                        }
                    }
                    not {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                    }
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
        
        stage('Test Frontend') {
            when {
                anyOf {
                    allOf {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                        changeset pattern: "${PROJECT_DIR}/frontend/**", comparator: 'GLOB'
                    }
                    not {
                        expression { env.BRANCH_NAME?.startsWith('feature/') }
                    }
                }
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
            when {
                anyOf {
                    allOf {
                        expression { isFeatureBranch() }
                        changeset pattern: "${PROJECT_DIR}/frontend/**", comparator: 'GLOB'
                    }
                    not {
                        expression { isFeatureBranch() }
                    }
                }
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

        stage('Auto Merge Feature → Develop') {
            when {
                allOf {
                    expression { isFeatureBranch() }
                    anyOf {
                        changeset pattern: "${PROJECT_DIR}/backend/**", comparator: 'GLOB'
                        changeset pattern: "${PROJECT_DIR}/frontend/**", comparator: 'GLOB'
                    }
                }
            }
            // Run merge only after all prior stages succeed (stage is placed last for feature branches)
            // and only in stage `post { success }` to avoid accidental merges.
            steps {
                echo 'Auto-merge will run after pipeline succeeds'
            }
            post {
                success {
                    script {
                        if (currentBuild.currentResult == 'SUCCESS') {
                            sh '''
                            git config user.email "jenkins@local"
                            git config user.name "jenkins"

                            git fetch origin --prune
                            git checkout -B develop origin/develop

                            # If a conflict happens, make the build fail (no partial merges).
                            git merge origin/${BRANCH_NAME} --no-ff -m "Auto merge feature" || {
                              echo "Merge failed (likely conflicts). Aborting auto-merge."
                              git merge --abort || true
                              exit 1
                            }

                            git push origin develop
                            '''
                        } else {
                            echo "Skipping merge because build result is: ${currentBuild.currentResult}"
                        }
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            when {
                anyOf {
                    branch 'develop'
                    expression { isReleaseBranch() }
                    branch 'main'
                }
            }
            agent {
                docker {
                    image 'maven:3.9.8-eclipse-temurin-21'
                    args '-u root'
                }
            }
            steps {
                withSonarQubeEnv('SonarQube') {
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                        script {
                            // Scan all microservices (not only answer-service).
                            def services = [
                                'answer-service',
                                'api-gateway-service',
                                'course-service',
                                'eureka-service',
                                'exam-service',
                                'user-service'
                            ]

                            services.each { svc ->
                                dir("${PROJECT_DIR}/backend/${svc}") {
                                    sh """
                                    mvn clean verify sonar:sonar \
                                      -Dsonar.projectKey=${svc} \
                                      -Dsonar.projectName=${svc} \
                                      -Dsonar.host.url=$SONAR_HOST_URL \
                                      -Dsonar.login=$SONAR_TOKEN
                                    """
                                }
                            }
                        }
                    }
                }
            }
        }

        stage('Quality Gate') {
            when {
                anyOf {
                    branch 'develop'
                    expression { isReleaseBranch() }
                    branch 'main'
                }
            }
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        
        stage('Docker Cleanup') {
            when {
                branch 'main'
            }
            steps {
                sh '''
                # Scope cleanup to the compose stack (do NOT kill all containers on this Jenkins machine).
                docker compose down --remove-orphans || true
                '''
            }
        }

        stage('Docker Build') {
            when {
                anyOf {
                    branch 'main'
                    expression { isReleaseBranch() }
                }
            }
            steps {
                sh 'docker compose build'
            }
        }

        stage('Deploy to Staging') {
            when {
                expression { isReleaseBranch() }
            }
            steps {
                sh '''
                if [ -f docker-compose.staging.yml ]; then
                    docker compose -f docker-compose.staging.yml down --remove-orphans || true
                    docker compose -f docker-compose.staging.yml up -d
                else
                    echo "docker-compose.staging.yml not found; skipping staging deploy"
                fi
                '''
            }
        }

        stage('Deploy Containers') {
            when {
                branch 'main'
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
            echo 'Pipeline executed successfully!!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}