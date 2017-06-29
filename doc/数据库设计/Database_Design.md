### Database Design

#### Relational Database (MySQL)

UserInfo(uid, username, password, phone, emai, role)(role:user, admin)

NotebookInfo(notebookid)

Auth(uid, notebookid, role)(role:owner, collaborator)



#### Document Database (MongoDB)

- User

  {

  ​   "uid": ,

  ​   "username": ,

  ​   "personalStatus": ,

  ​   "notebook": [ book\_A, book\_B, book\_C ] (bookId),

  ​   "follow": [ user\_A, user\_B, user\_C ] (uid),

  ​   "followed": [ user\_D, user\_E, user\_F ] (uid),

  ​   "feeds": [ "tag\_A", "tag\_B" ] (tag),

  ​   "avator": ,

  ​   "collection": [ book\_D, book\_E, book\_F ] (bookId)

  ​   "valid": ,

  ​   "deleteTime": ,

  ​   "honor":

  ​   "reward": { "valid": 1, "qrcode":  }


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

  ​   "count": ,

  ​   "collobrator": [ user\_A, user\_B, user\_C ] (uid),

  ​   "contributor": [ { "uid": user\_D, "point":  },  

  ​               { "uid": user\_E, "point":  } ],

  ​   "note": [ note\_A, note\_B, note\_C ] (noteId),

  ​   "createTime": ,

  }

- Note

  {

  ​   "noteId": ,

  ​   "noteBookId":, 

  ​   "title": ,

  ​   "history": [{

  ​       "editTime": ,

  ​       "message": ,

  ​       "content": ,

  ​       "editor": 

  ​   }],

  ​   "comment": [ { "uid": , "datetime": , "content": , "valid": "report":} ],

  ​   "upvote": [ user\_A, user\_B, user\_C ] (uid),

  ​   "downvote": [ user\_D, user\_E, user\_F ] (uid),

  ​   "report": 23

  ​   "valid": 1

  }

- GroupChat

  {

  ​   "notebookId": ,

  ​   "content": [

  ​       {

  ​           "uid": ,

  ​           "datetime": ,

  ​           "content":

  ​       }

  ​   ]

  }

- Letter

  {
  
  ​   "lid": ,

  ​   "uid\_A": ,

  ​   "uid\_B": ,

  ​   "content": [

  ​       {

  ​           "uid": ,

  ​           "datetime": ,

  ​           "content":

  ​       }

  ​   ]

  }

- Tag

  {
  
  ​   "tid": ,

  ​   "tagName": ,

  ​   "bookId": [ book\_A, book\_B ] (bookId)

  }

- Comment

  {

  ​   "cid": ,

  ​   "uid": ,

  ​   "datetime": ,

  ​   "content": ,

  ​   "report": ,

  ​   "valid":

  }

- Verify

  {

  ​   "cid": [ CID\_A, CID\_B ] (cid),


  ​   "noteId":  [ note\_A, note\_B ] (noteID)
  
  }

- Suggestion

   {
   ​  "sid": ,
   ​  "uid": ,

   ​  "noteId": ,

   ​  "noteBookId": ,

   ​  "content": ,

   ​  "issue": ,

   ​  "datetime": , 

   ​  "status":
   }
   
- Notice

  {
  ​  "uid": ,

  ​  "notice": [{"read":1, "content": }]
  
  }

- Rule

  {
  ​  "rid": ,
  
  ​  "rules": [{"rule":"haha", "releaseTime":"xxx"}]
  
  }
