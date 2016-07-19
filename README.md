# medijour

CrossOver - A Medical Journal Publication and Subscribing System
Author : Mário de Sá Vera
Date : July 14th 2016


1- Instructions to install and configure prerequisites or dependencies, if any

        For a less error prone install we will be using Docker. To install Docker in a Ubuntu Linux desktop we recommend to follow :

https://docs.docker.com/engine/installation/linux/ubuntulinux/

there are plenty of support for other Linux flavours and that should not be a problem. Once you have Docker installed we will download MediJour container :

# docker pull desavera/medijour-env-dev:r1


2- Instructions to create and initialize the database (if required)

	Database files are located at "medijour/var/sql" and consist of two basic MySQL scripts : a model.mysql and data.mysql for Database Model and Data Insertion respectively.

3- Assumptions you have made - it is good to explain your thought process and the assumptions you have made

        One important assumption I have made was that I would be able to find a simple Authentication Service for OAuth2. It came out to be not quite a simple solution so far as Auth0 (i.e. www.auth0.com) seems to be the more mature provider but still quite unstable. Google seems to be on its way but I did not have time to look at it pretty much.
        Auth0 does a good part of the job but they seem to support more client side programming and this trial seems to be focused on security and that drives us to the server side components. I went for Spring as stated in the stack session and integration to Auth0 seed projects was not possible. I took an alternative to open access to some REST paths but that was quite frustrating as security is definitely a major goal here. Anyway, it was quite a positive effort in deed.

4- Requirements that you have not covered in your submission, if any

        I was not able to cover most of requirements. Basically due to a lack of time primary but the issues with Auth0 lack of a concise documentation and seed projects were also time consuming as most of the tech stack with Spring Boot as well.

5- Instructions to configure and prepare the source code to build and run properly

        After running the MediJour container in your Desktop all you have to do is run the image and start the bootstrap script to put up all microservices. But it is necessary to map the container ports to your Desktop before that :

http://stackoverflow.com/questions/19897743/exposing-a-port-on-a-live-docker-container

the ports you should map are : 8080,8081,8082,8083,3098 and 3099

# docker run -i -t --name medijour desavera/medijour-env-dev:r1
# cd medijour
# ./run.sh

after this you will be able to navigate in the Web Portal by browsing over http://localhost:8080

6- Issues you have faced while completing the assignment, if any

        As usual the lack of time to do a better job. Something that was not clear was how to provide the non copy requirement. I went through DRM but realized that streaming was a simpler solution doing the so called PDF seriliazing for pages to be sent on demand. I was able to find two good components for that (PDFNet and PSDFKit) though PSPDFKit is still lacking the proper support to what we wanted. There are plenty of PDF readers for Android but to accomplish the requirement for DRM I would need licensing from PDFNet or do it myself which is unfeasible in the time I had. 
	What I was able to accomplish in these few days was to have a role based authentication for ADMIN and USER to distinguish a Publisher to a Subscriber respectively and render the proper interfaces based on the same Listing microservice for both and also for the public users. 

7- Constructive feedback for improving the assignment

        I think you guys could bring in some management challenge as this position is for a management profile. All this coding is something I think I am taking advantage as I am a coder and never stopped programming but I would say you are losing several good candidates that will basically not even start coding as they have left this kind of task for a long time and that does not mean they are bad managers ! Just a braistorming on that issue...

        The task was interesting in deed.

thanks,

Mario.

