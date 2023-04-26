    def call(String QALAB){
        node('master')
        {
            if("${QALAB}" == "true")
            {
                return "${AGENT}"
            }
            else{
                 node("master"){
                    wrap([$class: 'BuildUser']) {
                    USERID = env.BUILD_USER_ID
                    }
                }
                env.CHECKOUT_USING_USER="${USERID}"
                env.OCLOGIN_USING_USER="${USERID}"
                if( "${AGENT}" == "")
                {
                    env.ASSIGNED_AGENT="${USERID}"
                    log.info("No Agent Provided. Agent will be assigned from pool ${USERID}")
                }else
                {
                    env.ASSIGNED_AGENT="${AGENT}"
                    log.info("Agent Provided.The pipeline will be executed on agent:${env.ASSIGNED_AGENT}")
                }
                return "${env.ASSIGNED_AGENT}"
            }
        }
    }

