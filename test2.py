from flask import Flask, request, redirect
from twilio.twiml.messaging_response import MessagingResponse

import os.path
import time

import pandas as pd
import numpy as np
import seaborn as sns
import matplotlib as plt

def iris(body):
    iris = pd.read_csv("/Users/trinhle/Desktop/hackUTA/iris.csv")
    df = pd.DataFrame(iris)

    if body == 'len(iris)':
        returnEnd = str(len(df))
    
    if body == 'list(iris)':
        returnEnd = str(list(df))

    if body == 'iris.head()':
        returnEnd = str(df.head())

    return returnEnd

def some_thing(string):
    text_file = open("./read_c/text.meow", "w")
    n = text_file.write(string)
    text_file.close()

    while not os.path.exists("./read_C/pythonMessage.txt"):
        print("looking for file")
        time.sleep(1)

    str = open('./read_C/pythonMessage.txt', 'r').read() 
    print(str)
    return str


app = Flask(__name__)

@app.route("/sms", methods=['GET', 'POST'])
def incoming_sms():

    """Send a dynamic reply to an incoming text message"""
    # Get the message the user sent our Twilio number
    body = request.values.get('Body', None)
    print("body = " + str(body))
    # Start our TwiML response
    resp = MessagingResponse()

    # Determine the right reply for this message
    



    '''elif body == '1+1':
        resp.message("2")
    elif body == 'bye':
        some_thing(body)
        resp.message("Goodbye")


    elif body == 'iris.head()':
        printIris = str(iris(body))
        resp.message(printIris)
    elif body == 'list(iris)':
        printIris = str(iris(body))
        resp.message(printIris)
    elif body == 'len(iris)':
        printIris = str(iris(body))
        resp.message(printIris)'''

    if 'iris' in body or 'list' in body or 'len' in body:
        printIris = str(iris(body))
        resp.message(printIris)
        
    elif body:
        #print("jfdsafs")
        #some_thing(body)
        resp.message(some_thing(body))

    return str(resp)

if __name__ == "__main__":
    app.run(debug=True)