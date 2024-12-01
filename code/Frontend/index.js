const express = require("express");
const app = express();
const axios = require("axios");
const port = 3002;

app.set("view engine", "ejs");
app.set("views", "./templates");
app.use(express.urlencoded({ extended: true }));
app.use(express.json());

/*
const route = express.Router();


route.get('/', (req, res) => {
    const items = ['Item 1', 'Item 2', 'Item 3', 'Item Jhin'];
    res.render('Home', { items });
});

app.use('/', route); 
*/

app.get("/content", async (req, res) => {
  try {
    const { user_id, content_id } = req.body;
    if (!user_id || !content_id) {
      return res
        .status(400)
        .json({ message: "user_id and content_id are required" });
    }

    const response = await axios.post('http://localhost:8083/content', {
        user_id,
        content_id
    }, {
        headers: { 'Content-Type': 'application/json' }
    });

    res.json(response.data);
} catch (error) {
    console.error(error.response?.data || error.message);
    res.status(500).json({ message: 'Error fetching data from external API' });
}
});

app.post('/login', async (req, res) => {
    try {
        const { login, password } = req.body;

        if (!login || !password) {
            return res.status(400).json({ message: 'login and password are required' });
        }

        const response = await axios.post('http://localhost:8081/login', {
            login,
            password
        }, {
            headers: { 'Content-Type': 'application/json' }
        });

        res.json(response.data);
    } catch (error) {
        console.error(error.response?.data || error.message);
        console.log(error.status)
        res.status(500).json({ message: error.message });
    }
});

app.post('/content', async (req, res) => {
    try {
        const { php_code, user_id } = req.body;

        if (!php_code || !user_id) {
            return res.status(400).json({ message: 'php_code and user_id are required' });
        }

        const response = await axios.post('http://localhost:8082/content', {
            php_code,
            user_id
        }, {
            headers: { 'Content-Type': 'application/json' }
        });

        res.json(response.data);
    } catch (error) {
        console.error(error.response?.data || error.message);
        res.status(500).json({ message: 'Error posting content to external API' });
    }
});









app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}/`);
});
