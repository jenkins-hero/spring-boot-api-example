pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('run frontend') {
            steps {
                echo 'executing yarn'
                nodejs('Node-10.17'){
                     sh 'yarn install'
                }
            }
        }
        stage('Test') {
            steps {
                echo 'executing gradle'
                withGradle(){
                    sh './gradlew -v'
                }
            }
        }
    }
}