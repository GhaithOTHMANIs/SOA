const express = require('express');
const mysql = require('mysql2'); 
const app = express();
const PORT = 3000;

app.use(express.json());


const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '', 
    database: 'CMS'
});

db.connect(err => {
    if (err) {
        console.error('Database connection failed: ' + err.stack);
        return;
    }
    console.log('Connected to database.');
});

app.post('/api/content', (req, res) => {
    const php_code = req.body.php_code;
    const user_id = req.body.user_id;
    db.query('INSERT INTO content (php_code,user_id) VALUES (?,?)', [php_code,user_id], (err, results) => {
        if (err) {
            return res.status(500).send(err);
        }
        res.status(201).json({ id: results.insertId, php_code, user_id });
    });
});


app.listen(PORT, () => {
    console.log(`Server is running at http://localhost:${PORT}`);
});
