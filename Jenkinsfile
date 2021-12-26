pipeline {
    agent {
        docker {
            image 'maven:3.8.4-jdk-11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }
      
        stage('Build') {
            steps {
                sh 'mvn package'
            }
        }
      
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
