const express = require('express');
const uuid = require('uuid');
const router = express.Router();
const posts = require('../../data/posts');


router.get('/', (req, res) => res.status(200).json(posts));

router.get('/:id', (req, res) => {
    const isPresent = posts.some(post => post.id == req.params.id);

    if(isPresent){
        res.status(200).json(posts.filter(post => post.id == req.params.id)[0]);
    }
    else{
        res.status(400).json({ msg: `No post with id ${req.params.id} exists`});
    }
});

router.post('/', (req,res) => {
    const {name, username, content} = req.body;
    const id = uuid.v4();
    if(content.length){
        const post = {
            id,
            name,
            username, 
            content
        }
        posts.push(post);
        res.status(201).send(post);
    }
    else{
        res.status(400).json({ msg: `Post has no content to add`});
    }
});

module.exports = router;

