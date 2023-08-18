# QuizMircroserviceApp
+ Quiz application helps admin create new quizzes of different topics and users can take up them tocheck their proficiency level on respective topics.
+ Designed this application in Microservice architecture and implemented with the help of Spring Cloud Netflix-Eureka.
+ Created a service registry(Eureka Server) and registered two Question services,one Quiz service and an API Gateway to it as Eureka clients.Used OpenFeignClient to communicate with other services.
+ Written REST APIs for posting and accessing questions, quizzes and calculating the score of quiz.
+ Tested the application using Postman tool.
