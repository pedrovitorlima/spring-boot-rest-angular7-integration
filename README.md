
# Simple Spring Boot Rest Example With JWT

Hey you! This is a simple project that make use of Spring Boot to provide a rest services to create 
and find fake products. Maybe something in this code can help someone.

##Table of Contents
- [Validation Error Messages](#validation-messages)
- [CORS Filter](#cors-filter)
- [JWT Configuration](#jwt-configuration)
- [Angular 7 Integration Example](#angular7-example)

## <a href="#validation-messages">Validation Messages</a>
The <a href="/src/main/java/br/pedro/sandbox/springandangular/exception/CustomRESTExceptionHandler.java">`CustomRESTExceptionHandler`</a>
class is a controller advice with intent to handle error messages. For example, if 
we annotate a domain class property with `@NotNull('Please provide some value')` this 
message can be send to requestor to handle it. In Angular, for example, we can do this 
to handle all validation messages:
``
    this.http.post((...))
      .subscribe(
        response => {
          (...)
        },

        error => {
          console.log(<any>error.error.errors);
        }
      )
`` 

This class has a method called `handleMethodArgumentNotValid` that creates a list of 
String and fill it with field and global errors. After this, return a HTTP Bad Request 
error code with those messages.

## <a href="#cors-filter">CORS Filter</a>
The <a href="/src/main/java/br/pedro/sandbox/springandangular/config/CORSFilter.java">`CORSFilter`</a>
class is a filter (dah) to intercept and add headers to avoid CORS problem. To read 
more about CORS, please visit <a href="https://pt.wikipedia.org/wiki/Cross-origin_resource_sharing">Wikipedia</a>
link.

## <a href="#jwt-configuration">JWT Configuration</a>
@todo

## <a href="#angular7-example">Angular 7 Integration Example</a>
You can take a look inside <a href="github.com/pedrovitorlima/angular7-rest-consume-example">this</a> 
project to view a full example of those rest services integrated to a simple Angular 
7 and bootstrap app.
