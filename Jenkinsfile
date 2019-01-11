#!groovy

pipeline {

  agent none

  stages {
    stage('BuildAndTest') {
      agent { label 'linux' }
      tools {
        jdk   'openjdk-9-hotspot'
        maven 'maven-3.5.3'
      }
      steps {
        withMaven(
          maven: 'maven-3.5.3',
          mavenLocalRepo: '.repository') {
          sh 'mvn -C -e -U clean install'
        }
      }
    }
  }
}