// Store the current time in the given time zone
def calendar = Calendar.getInstance(TimeZone.getTimeZone('UTC'))
calendar.add(Calendar.HOUR_OF_DAY, 3) // Add 3 hours
// Format the updated time as HH:mm
def now = calendar.format("dd-MM-yyyy---HH:mm")

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
        type: 'PT_BRANCH')
    }

    stages {
        stage('Clearing folders: video, screenshots, logs, allure-results') {
            steps {
                    dir('C://ProgramData//Jenkins//.jenkins//workspace//AutomationExercise//build//allure-results')
                    {
                        deleteDir()
                    }
                    dir('D:\\docker\\logs')
                    {
                        deleteDir()
                    }
                    dir('D:\\docker\\video')
                    {
                        deleteDir()
                    }
            }
        }

        stage('Prepare Selenoid: starting containers') {
            steps {
                    script {
                            sh "docker exec -u 0 nginx sh -c 'mkdir /var/www/html/${BRANCH}_${now}'"
                            //sh "docker exec -u 0 nginx sh -c 'mkdir /var/www/html/${BRANCH}_${now}/video'"
                            //sh "docker exec -u 0 nginx sh -c 'mkdir /var/www/html/${BRANCH}_${now}/logs'"
                    }

                //bat "docker pull selenoid/$BROWSER"
                bat 'docker start nginx'
                bat 'docker exec -u 0 nginx sh -c "service nginx start"'
                bat 'docker exec -u 0 nginx sh -c "service nginx status"'
                bat 'docker-compose up -d'
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

                        //this block 'withCredentials' using for transfer encrypted data from Jenkins to the
                        // build, f.e. qase-token has encrypted data in Jenkins credentials and after that
                        // we transfer encrypted data to TOKEN_FOR_QASE which invokes when tests are launching
                        withCredentials ([
                            string(credentialsId: 'qase_token',
                            variable: 'TOKEN_FOR_QASE'),
                        ]){

                        bat "gradle clean -DlaunchType=$LAUNCH_TYPE -DvideoTestRecord=$VIDEO_TEST_RECORD -DlogsFromSelenoid=$LOGS_FROM_SELENOID -DcodeProject=$CODE_PROJECT -DtestRun=$TEST_RUN -Dtoken=$TOKEN_FOR_QASE  test"
                        }
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

        stage('Stopping and deleting docker containers') {
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
                        results: [[path: 'build/allure-results']]
                    ])
                }
            }
        }


        stage('Moving reports and logs to the nginx server') {
                    steps {
                        script {
                                sh "docker cp C://ProgramData//Jenkins//.jenkins//workspace//AutomationExercise//allure-report nginx:/var/www/html/${BRANCH}_${now}"
                                sh "docker cp D://docker//video nginx:/var/www/html/${BRANCH}_${now}/video"
                                sh "docker cp C://ProgramData//Jenkins//.jenkins//workspace//AutomationExercise//target//testsLog.log nginx:/var/www/html/${BRANCH}_${now}/logs"
                                sh "docker cp C://ProgramData//Jenkins//.jenkins//workspace//AutomationExercise//target//screenshots nginx:/var/www/html/${BRANCH}_${now}/bugs_screenshots"
                        }
                    }
        }
    }
}