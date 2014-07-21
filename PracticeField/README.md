# Practice Field for MobilePush SDK

## About
This is the Practice Field App for the MobilePush SDK. It serves these purposes:

- Provides a UI for practicing with various features of the MobilePush SDK.
- Provides an example or template to practice creating an Android app that use the ExactTarget MobilePush SDK.
- Provides a mechanism to collect and send debugging information to learn about the workings of the SDK as you practice.

## Fully Functional APK
The APKFiles folder contains et-practicefield-x.x.x-RELEASE.apk (where x.x.x is the current version of the PracticeField App) which can be downloaded to experience the Android SDK immediately before writing any code.

If you would rather, you can download the MobilePush Practice Field app from the Play Store under the name ET MobilePush Practice Field.

This apk file can be installed on your test devices to see the functionality of the Android SDK in action.  Typical features of the SDK are included as well as special features showing how flexible the SDK is.

To test the Location (Geo Fencing) aspects of the SDK, you can use an app on your device that will mock locations (check the Play Store, there are many options).  After installing (it will require you to set your device to allow Mock Locations), you can set a location to any of the stadiums of the teams found in the Settings Page of the app, you should receive a notification welcoming you to that stadium.  The Notification, when clicked, will open to the webpage for that team.

Note that using Mock Locations with a program that fakes GPS locations can interfere with the normal running of how your device sets location and may result in duplicate locations.  It also doesn't work on all devices (we had troubles getting it to properly fake the location on a Samsung 4).  Please keep this in mind when testing and final testing should use real locations to ensure you have your program setup correctly.

Not only can you see how the SDK works, but you can initiate messages from within this app.  Normally, you wouldn't include sending features within your app, however, this app provides a closed loop to allow you to initiate and receive messages within the same app.  This will allow you to put all the pieces together to see how the SDK works.

## Code
The code in this repository includes all of the code used to run the fully functional apk.  However, the API keys have been removed.  If you would like to debug the app or make any adjustments to create your own Practice Field app, you will need to provide several keys within the CONSTS_API class, as follows:

You can save both Development and Production keys in the CONSTS_API class, but for a debugging/test app, you only need to define the development keys.

The following keys are keys you normally use to create an app that uses the Android SDK:

1. ET_APP_ID_DEV - the AppId for your development app as defined in the AppCenter section of the Marketing Cloud
2. ET_ACCESS_TOKEN_DEV - the Access Token for your development app as defined in the AppCenter section of the Marketing Cloud
3. GCM_SENDER_ID_DEV - the Google Cloud Messaging ID as defined in the Google Cloud Developers Console for your app

The following keys are keys you need if you want to initiate messages within your customized Practice Field app:

1. ET_CLIENT_ID_DEV - the clientId for your development server to server app as defined in the AppCenter section of the Marketing Cloud
2. ET_CLIENT_SECRET_DEV -  the clientSecret for your development server to server app as defined in the AppCenter section of the Marketing Cloud
3. ET_MESSAGE_ID_DEV - the messageId of the template message (API Triggered) set in the Messaging Center of the Marketing Cloud

## Libraries
This project uses additional open source libraries.  In order to build your own version of the PracticeField, you will need to include the appropriate jar files in your project.

For example, the  Google Play Services library is required in order to use Google Cloud Messaging.  You should check the Google documentation for using Google Play Services in your project and make the necessary adjustments needed based on the development environment you use and the latest Google Play Services that is available.

## Marketing Cloud
To create a development version of the PracticeField App, you will need to have your app defined within the ExactTarget Marketing Cloud:

1. Setup your App in the App Center.
2. Send a request to connect the Google Cloud Messaging project to this app.
3. Create a Server to Server App to process messages within your app
4. Create a template (API Triggered) Message for the app in order to send messages from your app.
5. Create messages for your app for each location you'd like to test Geo Fencing.

For more information, see the info on Code@ here:
https://code.exacttarget.com/mobilepush/integrating-mobilepush-sdk-your-android-mobile-app


