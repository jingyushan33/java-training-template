pipeline {
    agent { label 'linux' }

    tools {
        maven 'maven3.6'
    }

    // keep 30 days, 200 builds.
    options {
        buildDiscarder(logRotator(artifactDaysToKeepStr: '',
        artifactNumToKeepStr: '', daysToKeepStr: '30', numToKeepStr: '200'))
        timestamps()
    }

    // 配置根据自己项目修改
    environment {
        NAMESPACE = 'k8s命名空间'
        DEFAULT_IMAGE_TAG = '1.0.0-snapshot'
        ALI_REGISTRY = 'registry.cn-hangzhou.aliyuncs.com'
        ALI_RREGISTRY_CREDENTIAL_ID = 'srdRegistryCrenId'
        DOCKERFILE = 'Dockerfile'
        BRANCH_PREFIX = 'develop'
        BRANCH_RELEASE = 'release/'
        TAG_PREFIX = 'v'
        DETAILS_BUILDING = true
        DEPLOY_BUILDING = true
        EMAIL = '运维账号'
    }

    stages {
        stage ('Mvn'){
            steps{
                dir("${env.WORKSPACE}"){
                    script{
                        def job_name = env.JOB_NAME =~ /.*\/(.*)\/.*/
                        env.JOB_NAMES = job_name[0][1]
                        echo "JOB_NAMES is ${env.JOB_NAMES}"
                    }
                    rtMavenDeployer (
                            id: 'deployer1',
                            serverId: 'lunz-artifactory',
                            releaseRepo: "${env.JOB_NAMES}",
                            snapshotRepo: "${env.JOB_NAMES}"
                        )
                    rtMavenRun (
                        // Tool name from Jenkins configuration.
                        tool: 'maven3.6',
                        pom: 'pom.xml',
                        goals: 'clean package -Dmaven.test.skip',
                        // Maven options.
                        //opts: '-Xms1024m -Xmx2048m',
                        //deployerId: 'deployer1',
                    )
                   // sh "cp ./target/${env.JOB_NAMES}-1.0.0.jar ./docker/"
                }
            }
        }

        stage ('Judge'){
            steps{
                script{
                    def matcher = env.BRANCH_NAME =~ /.*/
                    def branch_name = matcher[0]
                    if (branch_name.startsWith("${BRANCH_PREFIX}") && branch_name.endsWith("${DEFAULT_IMAGE_TAG}")) {
                        echo "end with snapshot"
                        def IMAGE_TAG = branch_name.replaceAll("${BRANCH_PREFIX}","")
                        IMAGE_URL = "${ALI_REGISTRY}/${NAMESPACE}/${env.JOB_NAMES}:${IMAGE_TAG}"
                        echo "${IMAGE_URL}"
                    }else if (branch_name.startsWith("${BRANCH_PREFIX}")) {
                        //def IMAGE_TAG = branch_name.replaceAll("${BRANCH_PREFIX}","")
                        def IMAGE_TAG = "${DEFAULT_IMAGE_TAG}"
                        echo "Release New Version ${IMAGE_TAG}"
                        IMAGE_URL = "${ALI_REGISTRY}/${NAMESPACE}/${env.JOB_NAMES}:${IMAGE_TAG}"
                        echo "${IMAGE_URL}"
                        DEPLOY_BUILDING = false
                    }else if (branch_name.startsWith("${TAG_PREFIX}")) {
                        def IMAGE_TAG = branch_name.replaceAll("${TAG_PREFIX}","")
                        echo "Tag New Version ${IMAGE_TAG}"
                        IMAGE_URL = "${ALI_REGISTRY}/${NAMESPACE}/${env.JOB_NAMES}:${IMAGE_TAG}"
                        echo "${IMAGE_URL}"
                        DEPLOY_BUILDING = false
                    }else if (branch_name.startsWith("${BRANCH_RELEASE}")) {
                         def IMAGE_TAG = branch_name.replaceAll("${BRANCH_RELEASE}","")
                         IMAGE_TAG = IMAGE_TAG + "-alpha"
                         IMAGE_URL = "${ALI_REGISTRY}/${NAMESPACE}/${env.JOB_NAMES}:${IMAGE_TAG}"
                         echo "${IMAGE_URL}"
                         DEPLOY_BUILDING = false
                     }else if (branch_name.length() >= 1){
                        echo "${branch_name} no need to build"
                        DETAILS_BUILDING = false
                    }
                }
            }
        }

        stage('Build Details'){

            when {
                expression {
                    DETAILS_BUILDING
                }
            }

            stages{
                stage ('Push'){
                    steps{
                        script{
                            docker.withRegistry("https://${IMAGE_URL}", "${ALI_RREGISTRY_CREDENTIAL_ID}") {
                                def myImage = docker.build("${IMAGE_URL}", " -f ${DOCKERFILE} .")
                                myImage.push()
                            }
                        }
                    }
                }

                stage('Deploy') {

                    when {
                        expression {
                            DEPLOY_BUILDING
                        }
                    }

                    steps {
                        script{
                            build job: '../redeploy-pipeline', parameters:[
                            string(name: 'service_name', value: "${env.JOB_NAMES}"),
                            ], wait:false
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            mail subject: """构建通知:${env.JOB_NAME} - Build # ${env.BUILD_NUMBER} - ${currentBuild.result?:'SUCCESS'}""",
            body: """
            <hr/>
            <font color="red">(本邮件是程序自动下发的，请勿回复！)</font><br/><hr/>

            项目名称：${env.JOB_NAME}<br/><hr/>

            构建编号：${env.BUILD_NUMBER}<br/><hr/>

            构建结果：${currentBuild.result}  <br/><hr/>

            构建时长：${currentBuild.duration}ms <br/><hr/>

            构建日志地址：<a href="${env.BUILD_URL}console">${env.BUILD_URL}console</a><br/><hr/>

            构建地址：<a href="${env.BUILD_URL}">${env.BUILD_URL}</a><br/><hr/>
            """,
            charset: 'utf-8',
            mimeType: 'text/html',
            to: "${EMAIL}"
        }
    }
}

