## AD-Capital

Appdynamics Transaction Analytics demo application

### Docker
1. Add AppServerAgent.zip, AnalyticsAgent.zip, Oracle JDK 7 and Maven distros to build directory
2. Build with: `docker build -t appdynamics/adcapital-demo .`
3. Start the MySQL database container: `docker run -d --name db -e MYSQL_ROOT_PASSWORD=welcome1 -p 3306:3306 mysql`
4. Start the ActiveMQ messaging service container: `docker run --name jms -d appdynamics/ecommerce-activemq`
5. Run with: `docker run --name adcapital -d --link db:db --link jms:jms -e CONTROLLER_HOST=<host> -e CONTROLLER_PORT=<port> -e CONTROLLER_USER=<user> -e CONTROLLER_PWD=<pwd> -e ACCOUNT_NAME=<account> -e ACCOUNT_ACCESS_KEY=<key> appdynamics/adcapital-demo` 
6. See [env.sh](https://github.com/Appdynamics/AD-Capital/blob/master/env.sh) for details of environment variables
7. To start Analytics Agent: `docker exec -it adcapital start-analytics <events-service-endpoint>` where `<events-service-endpoint>` is `host:port`
