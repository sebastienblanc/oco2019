
## Running CardScheme with Payara Micro

[Payara Micro configuration](https://blog.payara.fish/payara-micro-clustering)

OpenJ9 share classes options:

- To start HSQLDB with a cache: *java -Xshareclasses:name=hsqldb -cp $HSQLDB_HOME/lib/hsqldb.jar org.hsqldb.server.Server --silent false*
- To display all caches: *java -Xshareclasses:listAllCaches*
- To print the stats of a given cache: *java -Xshareclasses:name=hsqldb,printStats*