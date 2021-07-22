pipeline {
    agent any

    //only gradle, maven and jdk are supported
    //remove 'gradle 'Gradle-7.1.1'' from here if using 'withGradle(){}' wrapper within steps
    tools {
        maven 'Maven-3.6.3'
        gradle 'Gradle-7.1.1'
    }

    environment {
        NEW_VERSION = '1.3.0'
        SERVER_CREDENTIALS = credentials('server-credentials') //declare here if needed in multiple stages
    }

    parameters {
        string(name: 'VERSION', defaultValue: '', description: '')
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }


    triggers {
        pollSCM '* * * * *'
    }

    stages {
        stage('build frontend') {
            steps {
                echo 'executing yarn...'
                nodejs('Node-10.17'){
                     sh 'yarn install'
                }
            }
        }

        stage('build backend') {
            steps {
                echo 'building the application...'
                echo "building version ${NEW_VERSION} ..."

                //method1
                echo "using ${SERVER_CREDENTIALS} ..."
                sh "${SERVER_CREDENTIALS}"

                //method2
                withCredentials([
                    usernamePassword(credentials: 'server-credentials', usernameVariable: USER, passwordVariable: PWD)
                ]){
                    sh "some script ${USER} ${PWD}"
                }
            }
        }

        stage('test') {
            steps {
                echo 'executing gradle...'

                //method1
                sh './gradlew -v'

                //method2
                // withGradle(){
                //     sh './gradlew -v'
                // }

                //using maven
                echo 'executing gradle...'
                sh "mvn install"
            }
        }
    }
}