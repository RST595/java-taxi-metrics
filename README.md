### Project example:
### prometheus + grafana + spring boot 3 apiClient interfaces

To run project: 
1. Build and install to local .m2 dto package
2. Run docker files in metrics-app and taxi-provider-app packages
3. docker compose up -d<br/>


It creates:

1. Postgres database to store data fro metrics-app
2. metrics-app: collect price from taxi-provider-app and provide metrics
3. taxi-provider-app provide random prices for taxi
4. Prometheus server
5. Grafana server


Also, possible to run all on the local machine without docker, with H2 database<br/>
For that, use local config for metrics-app.<br/><br/>
In metrics app spring boot 3 client interface used for additional info from taxi-provider-app.
Spring boot 3 observation used for time tracking of methods with @Observed annotation.<br/>
Local testing possible with prepared .http files.<br/>
In config package you could find prometheus configuration and templates for grafana.
