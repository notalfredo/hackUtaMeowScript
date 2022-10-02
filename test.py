import os.path
import time


while not os.path.exists("pythonMessage.txt"):
    print("looking for file")
    time.sleep(1)

if os.path.isfile("pythonMessage.txt"):
    print("Found file")
