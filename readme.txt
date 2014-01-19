DynamicWhitelist Bukkit Plugin
(built for CraftBukkit 1.7.2)
milan@falesnik.net
==============================
This plugin is designed for easiest possible managing of the whitelist with web app.
On each user's login it queries the specified URL with GET parameter player
and then it expects a JSON answer in format:

{"whitelisted": true|false, "message": "Message"}.

If the query fails for some reason (network error, malformed JSON, ...),
the user is prohibited from entering the server.

Everything should be UTF-8.

This is my first Bukkit plug-in and my first Java application as I come from the world of python,
so it may not be as good as I would like to.