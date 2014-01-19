#!/usr/bin/env python2
# -*- coding: utf-8 -*-

""" Sample server application showing how to use the
    DynamicWhitelist plugin for Bukkit

@author: Milan Falešník <milan@falesnik.net>
@license: Public Domain
"""

from bottle import run, route, request, response
import json
import sys


@route('/check_whitelist', method='GET')
def events_list():
    response.content_type = "application/json"
    allowed_players = {"p1", "p2"}
    # You can query database or whatever you want.
    # This is just for simple demonstration.
    if request.query.player in allowed_players:
        return json.dumps(
            {
                "whitelisted": True,
                "message": "Welcome"
            }
        )
    else:
        return json.dumps(
            {
                "whitelisted": False,
                "message": "You cannot go in!"
            }
        )

port = 8080
if len(sys.argv) == 2:
    port = int(sys.argv[-1])
run(host='0.0.0.0', port=port, debug=True)
