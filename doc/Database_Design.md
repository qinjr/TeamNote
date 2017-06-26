### Database Design

#### Relational Database (MySQL)

User(uid, username, password, phone, emai, role)

#### Document Database (MongoDB)

- User

  {

  ​	"uid": ,

  ​	"username": ,

  ​	"personalStatus": ,

  ​	"notebook": [ book_A, book_B, book_C ] (bookId),

  ​	"follow": [ user_A, user_B, user_C ] (uid),

  ​	"followed": [ user_D, user_E, user_F ] (uid),

  ​	"feeds": [ "tag_A", "tag_B" ] (tag),

  ​	"avator": ,

  ​	"collection": [ book_D, book_E, book_F ] (bookId)

  ​	"valid": ,

  ​	"deleteTime": ,

  ​	"honor":


  }

- Notebook

  {

  ​	"notebookId": ,

  ​	"title": ,

  ​	"description": ,

  ​	"creator": ,

  ​	"owner": ,

  ​	"star": ,

  ​	"collected": ,

  ​	"count": ,

  ​	"collobrator": [ user_A, user_B, user_C ] (uid),

  ​	"contributor": [ { "uid": user_D, "point":  },  

  ​				{ "uid": user_E, "point":  } ],

  ​	"note": [ note_A, note_B, note_C ] (noteId),

  ​	"createTime": ,

  }

- Note

  {

  ​	"noteId": ,

  ​	"noteBookId":, 

  ​	"title": ,

  ​	"history": [{

  ​		"editTime": ,

  ​		"message": ,

  ​		"content": ,

  ​		"editor": 

  ​	}],

  ​	"comment": [ { "uid": , "datetime": , "content": , "valid": "report":} ],

  ​	"upvote": [ user_A, user_B, user_C ] (uid),

  ​	"downvote": [ user_D, user_E, user_F ] (uid),

  ​	"report": 23

  ​	"valid": 1

  }

- Groupchat

  {

  ​	"notebookId": ,

  ​	"content": [

  ​		{

  ​			"uid": ,

  ​			"datetime": ,

  ​			"content":

  ​		}

  ​	]

  }

- Letter

  {

  ​	"uid_A": ,

  ​	"uid_B": ,

  ​	"content": [

  ​		{

  ​			"uid": ,

  ​			"datetime": ,

  ​			"content":

  ​		}

  ​	]

  }

- Tag

  {

  ​	"tagName": ,

  ​	"bookId": [ book_A, book_B ] (bookId)

  }

- Comment 
  {

  ​	"cid": ,

  ​	"uid": ,

  ​	"datetime": ,

  ​	"content": ,

  ​	"report": ,

  ​	"valid":

  }

-  Verify 
  {

  ​	"cid": [ CID_A, CID_B ] (cid),


  ​	"noteId":  [ note_A, note_B ] (noteID)
  }

- Suggestion
   {

   ​	"uid": ,

   ​	"noteId": ,

   ​	"noteBookId": ,

   ​	"content": ,

   ​	"issue": ,

   ​	"datetime": , 

   ​	"status":
    }

