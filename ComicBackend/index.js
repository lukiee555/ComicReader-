var express = require("express");
var mysql = require("mysql");
var bodyParser = require("body-parser");

//Connect to MySQL
var connect = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: "testmanga"
});

//Create RestFul
var app = express();
var publicDir = __dirname + "/public/";

app.use(express.static(publicDir));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

//GET ALL BANNER
app.get("/banner", (req, res, next) => {
  connect.query("SELECT * FROM banner", function(error, result, fields) {
    connect.on("error", function(err) {
      console.log("[MYSQL ERROR]", err);
    });
    if (result && result.length) {
      res.end(JSON.stringify(result));
    } else {
      res.end(JSON.stringify("NO BANNER HERE"));
    }
  });
});

//GET ALL COMIC
app.get("/comic", (req, res, next) => {
  connect.query("SELECT * FROM manga", function(error, result, fields) {
    connect.on("error", function(err) {
      console.log("[MYSQL ERROR]", err);
    });
    if (result && result.length) {
      res.end(JSON.stringify(result));
    } else {
      res.end(JSON.stringify("NO COMIC HERE"));
    }
  });
});

//GET CHAPTER BY MANGA ID
app.get("/chapter/:mangaid", (req, res, next) => {
  connect.query(
    "SELECT * FROM chapter WHERE MangaID=?",
    [req.params.mangaid],
    function(error, result, fields) {
      connect.on("error", function(err) {
        console.log("[MYSQL ERROR]", err);
      });
      if (result && result.length) {
        res.end(JSON.stringify(result));
      } else {
        res.end(JSON.stringify("NO CHAPTER HERE"));
      }
    }
  );
});

//GET IMAGES BY CHAPTER ID
app.get("/links/:chapterid", (req, res, next) => {
  connect.query(
    "SELECT * FROM link WHERE ChapterId=?",
    [req.params.chapterid],
    function(error, result, fields) {
      connect.on("error", function(err) {
        console.log("[MYSQL ERROR]", err);
      });
      if (result && result.length) {
        res.end(JSON.stringify(result));
      } else {
        res.end(JSON.stringify("NO CHAPTER HERE"));
      }
    }
  );
});

//Start Server
app.listen(3000, () => {
  console.log("listining to PORT 3000");
});
