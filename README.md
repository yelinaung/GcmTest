GcmTest
-------

Thanks to [Google Cloud Messaging GCM for Android and Push Notifications](http://javapapers.com/android/google-cloud-messaging-gcm-for-android-and-push-notifications/) Tutorial!

Testing GCM for Android with [PushMeUp](https://github.com/NicosKaralis/pushmeup) gem.

Ruby script
```ruby
require 'pushmeup'

# https://android.googleapis.com/gcm/send is default
GCM.host = 'https://android.googleapis.com/gcm/send'

# :json is default and only available at the moment
GCM.format = :json

# this is the apiKey obtained from here https://code.google.com/apis/console/
GCM.key = ""

# This is the device id you can get
device1 = ""

destination = [device1]
# can be an string or an array of strings containing the regIds of the devices you want to send

data = {"m"=> "Hello Android!"}
# must be an hash with all values you want inside you notification

GCM.send_notification(destination, data )
# Notification with custom information
```
