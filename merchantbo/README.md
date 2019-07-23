# Merchant Back Office

A GraphQL API to expose merchant payments in a flexible way.

Based on [KumuluzEE GraphQL](https://github.com/kumuluz/kumuluzee-graphql).

To start an application see [KumuluzEE Getting Started(https://github.com/kumuluz/KumuluzEE/wiki/Getting-started)

To check [KumuluzEE configuration](https://github.com/kumuluz/kumuluzee/wiki/EeConfig)

To check the [EeConfigFactory code](https://github.com/kumuluz/kumuluzee/blob/master/core/src/main/java/com/kumuluz/ee/factories/EeConfigFactory.java)

Commands to run the service:

- if Uber Jar (repackage maven goal) java -jar target/bomerchant-1.0.jar
- if exploded (copy-dependencies maven goal): java -cp target/classes;target/dependency/* com.kumuluz.ee.EeApplication
- with OpenJ9 shared classes: java -Xshareclasses:name=kumuluzee -cp target/classes;target/dependency/* com.kumuluz.ee.EeApplication

To do a healthcheck: http://localhost:8081/health

To run GraphiQL: http://localhost:8081/graphiql

Example of requests below.

To get all accepted payments, sorted by amount:

~~~~
query allPayments {
  allPayments(sort: {fields: [{field: "cardNumber", order: ASC}]}) {
    id
    cardNumber
    cardType
    responseCode
    posId
  }
}
~~~~

To track the activity for a given card:

~~~~

 query trackCard {
  allPayments(filter: {fields: [{field: "cardNumber", value: "5174071733242036", op: EQ}]}) {
    id
    responseCode
    amount
    posId
    posRef {
      location
    }
  }
}
~~~~

To track the activity for a give point of sales:

~~~~

 query trackPos {
  allPayments(filter: {fields: [{field: "posId", value: "POS-01", op: EQ}]}) {
    id
    cardNumber
    cardType
    responseCode
    amount
  }
}
~~~~

To check a given Card Type:

~~~~

query trackPos {
  allPayments(filter: {fields: [{field: "posId", value: "POS-01", op: EQ}]}) {
    id
    cardNumber
    cardType
    responseCode
    amount
    cardType
  }
}
~~~~




