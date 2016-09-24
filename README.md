# SmartHealth
The application offers four main areas of functionality:
## User Profiles
People must register if they want to become users of this application. Any person may register and the application is free of charge to use. SmartHealth anticipate having about a million users, but need to be able to expand beyond that if the website becomes a very popular. A user must supply two working email addresses, and must devise a username that is unique within the SmartHealth system. At login they authenticate using their primary email address and a password. Usernames are used for display purposes. About each user we keep:
1.	their username (a string, unique to our system, which is no longer than 20 characters), 
2.	email addresses, 
3.	their real first and last name (for our records, not for public display), 
4.	their postal address (for our records, not for public display), 
5.	a paragraph of “about me” text, and 
6.	the URLs of three profile photos. 
Each user also has a score called their “karma”, which builds as they contribute quality content to the system.
Each user has a “user type”: one at a given time, though it may change over time. New users are created with type “new”; after a month of regular use they become “middle”; after a year, “old”. Professional doctors will be employed to moderate the site: these have user type “mod”. There are also some technical/managerial users who have type “admin”. The system needs to keep track of a user’s type, so that users of different types can be presented with different software features.
Administrators and moderators do not have “karma”; but users of these two types have an emergency contact phone number stored, while moderators also have their professional qualifications stored. We need to keep the qualifications we will recognize – these can be short strings like “M.D.” and “Ph.D.” – and some way to store which person has which qualifications. Note that moderators may have more than one qualification, and that the set of qualifications we recognize is likely to change over time.
Users may choose to quit using the site at any time. However if a user quits, we don’t want to delete them from the system – we simply want to mark them as having quit and make them invisible to other users.

## Social Networking
Users can befriend each other, and the system must keep track of who is whose friend. Friends can see each other’s health charts, for example, so as to engage in friendly competition over how much exercise they have performed or how much weight they have lost. Only end users are involved in friendship, not moderators and administrators.
Friendship is created when one user sends a friend request to another, and the second user confirms the friendship. We need to keep track of friend requests so that the software can display them to recipients. A friend request may be accepted or declined by the second user. The first user cannot withdraw a friend request. We must record whether and when the request is sent, accepted and denied. Users may later “unfriend” a user whom they have already “friended”, and this must be recorded and dealt with appropriately.

## Online discussion forum
Users post their questions, opinions and helpful facts into one of many forums. Each forum is about one particular topic and is accessed via a different URL. At the top of each forum page we will print the forum’s name and a short one­ or two sentence description of the topic.
Only moderators can create new topics. They can also shut them down (though again, we don’t delete a closed forum, just mark it and make it invisible to non­moderator users). We should track the dates and times on which a forum is created (and closed) and which moderator performed these actions.
A user’s text entry into one of these forums is called a “post”. Other users may “comment” on the post or give the post a rating out of 5 stars. Posts and comments are displayed as a text box, along with the name of the user who wrote it, when they wrote it, and average star rating the post or comment has. Any post or comment may include one photo, and/or one link to a webpage, and/or one link to a YouTube video. Photos are stored as files in our system, while webpages and videos are merely links to other sites.



## Health data
Health and fitness apps on the user’s phone will send data to SmartHealth for storage and display. Users will only be able to see their own health data and the health data of their friends. At this point there are plans to store data about how far the user has run, calories burned, and blood pressure. What each of these has in common is that the datum arrives asa single number. All we need to do is store the number, the time at arrived, which health property it represents, and the user to whom it belongs.
To support this we need to store a list of valid data streams (distance run, calories burned etc.). We anticipate that as more kinds of physiological sensors are added to future phones, we will need to store more kinds of health data. 
Other programmers (mobile app developers) are working out how to get this data from users’ phones: all you need to do is to decide how you are going to insert and store those values into the system.

