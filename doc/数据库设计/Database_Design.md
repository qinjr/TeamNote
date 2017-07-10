### Database Design

#### Relational Database (MySQL)

UserInfo(userId, username, password, phone, emai, role)(role:user, admin)

NotebookInfo(notebookid)

Auth(aid, uid, notebookid, auth)(role:owner, collaborator)



#### Document Database (MongoDB)

- User

  {

  ​   "userId": ,

  ​   "username": ,

  ​   "personalStatus": ,

  ​   "notebooks": [ book\_A, book\_B, book\_C ] (bookId),

  ​   "followers": [ user\_A, user\_B, user\_C ] (uid),

  ​   "followings": [ user\_D, user\_E, user\_F ] (uid),

  ​   "tags": [ "tag\_A", "tag\_B" ] (tag),

  ​   "avator": ,

  ​   "collections": [ book\_D, book\_E, book\_F ] (bookId)

  ​   "valid": ,

  ​   "deleteCount": ,

  ​   "reputation":

  ​   "reward": { "valid": 1, "qrcode":  }

  ​   "upvoteBooks": [ book\_A, book\_B ][1]

  }

- Notebook

  {

  ​   "notebookId": ,

  ​   "title": ,

  ​   "description": ,

  ​   "creator": ,

  ​   "owner": ,

  ​   "star": ,

  ​   "collected": ,

  ​   "clickCount": ,

  ​   "collaborators": [ user\_A, user\_B, user\_C ] (uid),

  ​   "contributors": [ { "uid": user\_D, "point":  },  

  ​               { "uid": user\_E, "point":  } ],

  ​   "notes": [ note\_A, note\_B, note\_C ] (noteId),

  ​   "createTime": ,

	 "tags": [ tag_A, tag_B, tag_C ] (tagId)

  }

- Note

  {

  ​   "noteId": ,

  ​   "noteBookId":, 

  ​   "title": ,

	 "versionPointer": ,

  ​   "history": [{

  ​       "editTime": ,

  ​       "message": ,

  ​       "content": ,

  ​       "editor": 

  ​   }],

  ​   "comments": [ { "uid": , "datetime": , "content": , "valid": "report":} ],

  ​   "upvoters": [ user\_A, user\_B, user\_C ] (uid),

  ​   "downvoters": [ user\_D, user\_E, user\_F ] (uid),

  ​   "reportCount": 23

  ​   "valid": 1

  }

- GroupChat

  {

  ​   "notebookId": ,

  ​   "contents": [

  ​       {

  ​           "uid": ,

  ​           "datetime": ,

  ​           "content":

  ​       }

  ​   ]

  }

- Letter

  {

  ​   "letterId": ,

  ​   "senderId": ,

  ​   "receiverId": ,

  ​   "content”: ,

  ​   "sentTime": 

  }

- Tag

  {

  ​   "tagId": ,

  ​   "tagName": ,

  ​   "booksOfTag": [ book\_A, book\_B ] (bookId)

  }

- Comment

  {

  ​   "commentId": ,

  ​   "userId": ,

  ​   “sentTime": ,

  ​   "content": ,

  ​   "reportCount": ,

  ​   "valid":

  }

- Verify

   {

  ​   "date": ,

  ​   "comments": [ CID\_A, CID\_B ] (cid),

  ​   "notes":  [ note\_A, note\_B ] (noteID)


  }

- Suggestion

   {
   ​  "suggestionId": ,

   ​  "username": ,

   ​  "userId": ,

   ​  "noteId": ,

   ​  "noteBookId": ,

   ​  "content": ,

   ​  "issue": ,

   ​  "raiseTime": , 

   ​  "status":
   }
- Notice

  {
  ​  "userId": ,

  ​  "notices": [{"read":1, "content":xxx, "datetime"}]

  }

- Rule

  {
  ​  "ruleId": ,

  ​  "rules": \{"rule":"haha", "releaseTime":"xxx"}

  }

[1]:	bookId