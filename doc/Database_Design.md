### Database Design

#### Relational Database (MySQL)

User(uid, username, password, phone, emai, role)

#### Document Database (MongoDB)

- User

  {

  ​	"uid": ,

  ​	"notebook": [ book_A, book_B, book_C ] (bookId),

  ​	"follow": [ user_A, user_B, user_C ] (uid),

  ​	"followed": [ user_D, user_E, user_F ] (uid),

  ​	"feeds": [ "tag_A", "tag_B" ] (tag),

  ​	"icon": ,

  ​	"collection": [ book_D, book_E, book_F ] (bookId)

  }

- Notebook

  {

  ​	"notebookId": ,

  ​	"title": ,

  ​	"description": ,

  ​	"creator": ,

  ​	"owner": ,

  ​	"collobrator": [ user_A, user_B, user_C ] (uid),

  ​	"contributor": [ { "name": user_D, "point":  },  

  ​				{ "name": user_E, "point":  } ],

  ​	"note": [ note_A, note_B, note_C ] (noteId),

  ​	"createTime": ,

  }

- Note

  {

  ​	"noteId": ,

  ​	"title": ,

  ​	"history": {

  ​		"editTime": ,

  ​		"message": ,

  ​		"content": ,

  ​		"editor": 

  ​	},

  ​	"comment": [ { "uid": , "datetime": , "content": , "valid": } ],

  ​	"upvote": [ user_A, user_B, user_C ] (uid),

  ​	"downvote": [ user_D, user_E, user_F ] (uid),

  ​	"count": ,

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