
const express = require('express')
const app = express()
const port = 3000

const users = [
	{
		id: "1",
		username: "andre",
		email: "andredemattos.ferraz@halliburton.com",
		firstName: "andre",
		lastName: "ferraz",
		realUsername: "andre",
		password: "andre"
	},
	{
		id: "2",
		username: "emanoel",
		email: "emanoel@emanoel.com",
		firstName: "emanoel",
		lastName: "ferreira",
		realUsername: "emanoel",
		password: "emanoel"
	},
	{
		id: "3",
		username: "santini",
		email: "santini@santini.com",
		firstName: "luiz",
		lastName: "santini",
		realUsername: "santini",
		password: "santini"
	}
]

app.get('/', (req, res) => {
	res.send("working")
})

app.get('/validatePassword', (req, res) => {
	console.log('validatePassword')
	let username = req.headers.user || req.headers.username
	let user = req.headers.user
	let password = req.headers.password
	
	console.log("user", user)
	console.log("password", password)
	console.log("username", username)
	
	let found_user = undefined
	for(let i = 0; i < users.length; i++){
		if(users[i].username == username){
			found_user = users[i]
		}
	}
	
	let isValid = false
	
	if(found_user == undefined){
		isValid = false
	}
	else if(found_user.password == password){
		isValid = true
	}
	
	res.send(isValid)
})

app.get('/getUserById', (req, res) => {
	console.log('getUserById')
	let user_id = req.headers.id_person
	
	console.log("user_id", user_id)
	
	let found_user = undefined
	for(let i = 0; i < users.length; i++){
		if(users[i].id == user_id){
			found_user = users[i]
		}
	}
	
	res.send(JSON.stringify(found_user))
})

app.get('/getUser', (req, res) => {
	console.log('getUser')
	let username = req.headers.user
	
	console.log("username", username)
	
	let found_user = undefined
	for(let i = 0; i < users.length; i++){
		if(users[i].username == username){
			found_user = users[i]
		}
	}
	
	res.send(JSON.stringify(found_user))
})

app.listen(port, () => {
  console.log(`Node user mock API app listening at http://localhost:${port}`)
})
