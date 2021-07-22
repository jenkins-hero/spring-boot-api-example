def buildApp1() {
    echo 'executing yarn...'
    nodejs('Node-10.17'){
            sh 'yarn install'
        //  sh "mvn install" //when maven is "maven 'Maven-3.8.1'" is not declared in tools
    }
}

def buildApp2() {
    echo 'building the application...'
    echo "building version ${NEW_VERSION}"

    //method1
    echo "deploying with ${SERVER_CREDENTIALS}"
    sh "${SERVER_CREDENTIALS}"

    //method2
    withCredentials([
        usernamePassword(credentials: 'server-credentials', usernameVariable: USER, passwordVariable: PWD)
    ]){
        sh "${USER} ${PWD}"
    }
}

def testApp() {
    echo 'executing gradle...'
    //method1
    sh './gradlew -v'

    //method2
    // withGradle(){
    //     sh './gradlew -v'
    // }

    //using maven
    echo 'executing mvn...'
    // sh "mvn install" //POM file must be present
}

def deployApp() {
    echo 'deploying the application ...'
    echo "deploying version ${params.VERSION}"
}