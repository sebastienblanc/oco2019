TODO:

- smartMerchant finalization : 
    - asynchronous processing of request (optional), 
    - health check (required), 
    - fault tolerance (required), 
    - REST client, SSE connection to merchantbo including resiliency,
- minimalist merchant-bo based on Kumuluzee : direct access to db, no SSE,
- bankauthor refactoring: to be renamed CardScheme, authorization dependening on a configured max_amount to be predictible,
- one global Maven project with 3 modules?

# Build
mvn clean package && docker build -t org.sebjef/acceptance .

# References
* https://www.freeformatter.com/credit-card-number-generator-validator.html
* https://creditcardjs.com/credit-card-type-detection
* https://hzhou.me/2014/08/13/java-get-credit-card-type-by-its-number/
* https://www.kodnito.com/posts/documenting-rest-api-using-microprofile-openapi-swagger-ui-payara-micro/
* https://medium.com/liferay-engineering-brazil/server-sent-events-with-jax-rs-a63ce1813d82
* https://github.com/kumuluz/kumuluzee-graphql
* https://blog.payara.fish/the-health-check-service-in-depth-payara-server


# RUN

docker rm -f acceptance || true && docker run -d -p 8080:8080 -p 4848:4848 --name acceptance org.sebjef/acceptance 

To get test card numbers: https://www.freeformatter.com/credit-card-number-generator-validator.html

The first of each type is black-listed

VISA:
4485248221242242
4716727468113894
4232234208447079676

MasterCard:
5261749597812879
5174071733242036
5401120961249695

American Express (AMEX):
343506189778618
374279489923472
379434751648035

Discover:
6011191990123805
6011716535549278
6011146077659186427

JCB:
3536173000512832
3537010260649376
3531769374239348486

Diners Club - North America:
5566365109293359
5463347980845915
5455359964459555

Diners Club - Carte Blanche:
30002684064025
30250213149136
30557466033555

Diners Club - International:
36031319313683
36593848419737
36673765041514

Maestro:
5020439313243185
6304768679459653
6763612398304564

Visa Electron:
4917992993236951
4508979941862421
4026075143680779

InstaPayment:
6385990870712183
6371309589977282
6388185251574859