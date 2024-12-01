const soap = require("soap");
const express = require("express");
const bodyParser = require("body-parser");
const mysql = require("mysql");
const fs = require("fs");

const wsdl = fs.readFileSync("./getContentDocument.wsdl", "utf8");
const app = express();
app.use(bodyParser.raw({ type: () => true, limit: "5mb" }));

// MySQL connection
const db = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: "cms",
});

db.connect((err) => {
  if (err) throw err;
  console.log("Connected to MySQL");
});

const myService = {
  MyService: {
    MyServicePort: {
      GetUserContent: function (args) {
        const user_id = args.userid;
        const content_id = args.contentid;
        console.log(args);
        return new Promise((resolve, reject) => {
          if (!user_id || !content_id) {
            console.error("Missing user_id or content_id");
            reject({ content: "Invalid request parameters" });
            return;
          }
          const query =
            "SELECT php_code FROM Content WHERE user_id = ? AND id = ?";
          db.query(query, [user_id,content_id], (err, results) => {
            if (err) {
              console.error("Error querying the database:", err);
              reject({ content: "Error fetching content" });
            } else {
              console.log("Database result:", results); 
              if (results.length > 0) {
                resolve({ content: results[0].php_code || "No content found" });
              } else {
                console.log("No matching content found");
                resolve({ content: "No content found" });
              }
            }
          });
        });
      },
    },
  },
};

const PORT = 3001;
soap.listen(app, "/UserContentService", myService, wsdl, () => {
  console.log(
    `SOAP service is running on http://localhost:${PORT}/UserContentService`
  );
});

app.listen(PORT, () => {
  console.log(`Express server running on http://localhost:${PORT}`);
});
