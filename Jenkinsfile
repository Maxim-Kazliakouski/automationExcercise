pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M2"
    }
//     triggers {
//         cron('0 8 * * *')
//     }
    parameters {
        gitParameter ( branch: '',
        branchFilter: 'origin/(.*)',
        defaultValue: 'master',
        description: '',
        name: 'BRANCH',
        quickFilterEnabled: true,
        selectedValue: 'NONE',
        sortMode: 'NONE',
        tagFilter: '*',
        type: 'PT_BRANCH' )
    }

    stages {
        stage('Prepare Selenoid: starting containers') {
            steps {
                //bat "docker pull selenoid/$BROWSER"
                //bat "D://UI_API//src//test//resources//ConfigurationManager//cm.exe selenoid start --vnc"
                //bat "D://automationExercise//docker-compose up -d"
                bat 'docker start nginx'
                bat "docker exec nginx sh -c 'service nginx start'"
                bat "docker exec -T -u 0 nginx sh -c 'service nginx status'"
                bat 'docker-compose up -d'
                //bat "D://UI_API//src//test//resources//ConfigurationManager//cm.exe selenoid-ui start"
                //bat "D://UI_API//src//test//resources//ConfigurationManager//cm.exe selenoid status"
                bat "curl http://localhost:4444/status"
            }
        }

        stage('UI tests') {

            steps {

                script {

                    try {

                        // Get some code from a GitHub repository
                        git branch: "${params.BRANCH}",  url: 'https://github.com/Maxim-Kazliakouski/automationExercise.git'
                        // Run Maven on a Unix agent.
                        // sh "mvn clean -Dsurefire.suiteXmlFiles=src/test/resources/chromeLaunchTest.xml \
                        // To run Maven on a Windows agent, use
                        bat "gradle clean test"

                    }
					catch (Exception error)
					{
                        unstable('Testing failed')
                    }
                }
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                //junit '**/target/surefire-reports/TEST-*.xml'
                junit '**/build/test-results/test/**/TEST-*.xml'
                }
            }
        }

        stage('Stopping and deleting containers') {
                steps {
                    script {
                            //bat "docker stop selenoid"
                            //bat "docker rm selenoid"
                            //bat "docker rm selenoid-ui"
                            //bat "docker stop nginx"
                            bat "docker stop automationexercise-selenoid-ui-1"
                            bat "docker rm automationexercise-selenoid-ui-1"
                            bat "docker stop automationexercise-selenoid-1"
                            bat "docker rm automationexercise-selenoid-1"
                }
            }
        }

        stage('Generating Allure report') {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                    bat "docker cp C://ProgramData//Jenkins//.jenkins//workspace//AutomationExercise//allure-report// nginx:/var/www/html/REPORT-${BRANCH}-%date%-%time%"
                }
            }
        }
    }
}