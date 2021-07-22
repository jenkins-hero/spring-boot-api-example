pipeline {
    agent any

    tools {
        gradle 'Gradle-7.1.1' //method2 remove withGradle(){} wrapper
    }

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
                sh './gradlew -v'
                
                // withGradle(){
                //     sh './gradlew -v'
                // }
            }
        }
    }
}