pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Build') {
            steps {
                sh './gradlew assemble'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
        stage('Build Docker image') {
            steps {
                sh './gradlew docker'
            }
        }
        stage('Push Docker image') {
            environment {
                DOCKER_HUB_LOGIN = credentials('docker-hub')
            }
            steps {
                sh 'docker login --username=$DOCKER_HUB_LOGIN_USR --password=$DOCKER_HUB_LOGIN_PSW'
                sh './gradlew dockerPush -PdockerHubUsername=$DOCKER_HUB_LOGIN_USR'
            }
        }
        stage('Deploy to AWS') {
            environment {
                DOCKER_HUB_LOGIN = credentials('docker-hub')
            }
            steps {
                withAWS(credentials: 'aws-credentials', region: env.AWS_REGION) {
                    sh './gradlew awsCfnMigrateStack awsCfnWaitStackComplete -PsubnetId=$SUBNET_ID -PdockerHubUsername=$DOCKER_HUB_LOGIN_USR -Pregion=$AWS_REGION'
                }
            }
        }
    }
}