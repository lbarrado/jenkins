job('EJECUTA_JOBDOCKERNODE_001') {
    description('Aplicación Node JS Docker DSL para el curso de Jenkins')
    scm {
        git('https://github.com/macloujulian/nodejsapp.git', 'master') { node ->
            node / gitConfigName('Luis')
            node / gitConfigEmail('ljbarrado@gmail.com')
        }
    }
    triggers {
        scm('H/7 * * * *')
    }
    wrappers {
        nodejs('Nodejs')
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('ljbarrado/nodejs')
            tag('${GIT_REVISION,length=7}')
            registryCredentials('docker-hub')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
    publishers {

    }
}
