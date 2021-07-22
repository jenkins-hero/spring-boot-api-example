def gv
CODE_CHANGES = true //getGitChanges()

pipeline {
    agent any

    //only gradle, maven and jdk are supported
    //remove 'gradle "Gradle-7.1.1'" from here if using 'withGradle(){}' wrapper within steps
    tools {
        maven 'Maven-3.8.1'
        gradle 'Gradle-7.1.1'
    }

    environment {
        NEW_VERSION = '1.3.0'
        BRANCH_NAME = 'dev'
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
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }

        stage('build App1') {
            steps {
                script {
                    gv.buildApp1()
                }
                
            }
        }

        stage('build App2') {
            steps {
                script {
                   gv.buildApp2() 
                }
            }
        }

        stage('test') {
            when {
                expression {
                    params.executeTests == true
                }
            }
            steps {
                script {
                    gv.testApp()
                }
            }
        }

        stage("deploy") {
            when {
                expression {
                    (BRANCH_NAME == 'dev' || BRANCH_NAME == 'test') && CODE_CHANGES == true
                }
            }
            steps {
                script {
                    gv.deployApp()
                }
            }
        }

    }
}