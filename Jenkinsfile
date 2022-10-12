pipeline {
//     agent {
//         docker {
//             image 'maven:3.8.4-jdk-11'
//             args '-v /root/.m2:/root/.m2'
//         }
//     }
    agent any
    tools { 
//         maven 'Maven 3.3.9' 
//         jdk 'jdk8' 
           docker 'docker'
    }
    stages {
        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }
      
      
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        
        
        stage('Build') {
            steps {
                sh 'mvn package'
            }
        }
    }
}
