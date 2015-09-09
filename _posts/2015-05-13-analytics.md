---
layout: page
title: "Analytics"
subtitle: "Adding Analytics"
category: features
date: 2015-05-14 12:00:00
order: 6
---
Follow the steps below to implement analytics in your mobile app:

1.  Set the enableAnalytics parameter to true on the call to readyAimFire() in the Application Class.

    ~~~ 
    ETPush.readyAimFire(this, 
                        CONSTS_API.getEtAppId(), 
                        CONSTS_API.getAccessToken(), 
                        CONSTS_API.getGcmSenderId(), 
                        true,     // enable ET Analytics 
                        true,     // enable Location Manager, if you purchased this feature
                        true);    // enable Cloud Page, if you purchased this feature
    ~~~ 
1.  Let the SDK know when your app goes into the background as the SDK sends Analytics when the app has been in the background for over 1 minute.

    > The following is required only if you are targetting **earlier than Android API 14**.  For apps targetting **Android 14 or later**, the SDK will implement these calls using registerActivityLifecycleCallbacks().

    ~~~ 
    @Override
    protected void onPause() {
        super.onPause();
        
        try {
            // Let JB4A SDK know when each activity paused
            ETPush.activityPaused(this);
        }
        catch (Exception e) {
            if (ETPush.getLogLevel() <= Log.ERROR) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            // Let JB4A SDK know when each activity resumed(
            ETPush.activityResumed(this);
        }
        catch (ETException e) {
            if (ETPush.getLogLevel() <= Log.ERROR) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }
    ~~~ 

### Mobile Analytics###
Follow the steps below to implement [Web and Mobile Analytics](http://www.exacttarget.com/products/customer-data-platform/web-mobile-analytics){:target="_blank"} in your mobile app:

1.  Set the enableAnalytics parameter to true on the call to readyAimFire() in the Application Class.

    ~~~ 
    ETPush.readyAimFire(this, 
                        CONSTS_API.getEtAppId(), 
                        CONSTS_API.getAccessToken(), 
                        CONSTS_API.getGcmSenderId(), 
                        true,     // enable ET Analytics
                        true,     // enable new Web and Mobile Analytics 
                        true,     // enable Location Manager, if you purchased this feature
                        true);    // enable Cloud Page, if you purchased this feature
    ~~~ 
1.  Let the SDK know when your app goes into the background as the SDK sends Analytics when the app has been in the background for over 1 minute.

    > The following is required only if you are targetting **earlier than Android API 14**.  For apps targetting **Android 14 or later**, the SDK will implement these calls using registerActivityLifecycleCallbacks().

    ~~~ 
    @Override
    protected void onPause() {
        super.onPause();
        
        try {
            // Let JB4A SDK know when each activity paused
            ETPush.activityPaused(this);
        }
        catch (Exception e) {
            if (ETPush.getLogLevel() <= Log.ERROR) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            // Let JB4A SDK know when each activity resumed(
            ETPush.activityResumed(this);
        }
        catch (ETException e) {
            if (ETPush.getLogLevel() <= Log.ERROR) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }
    ~~~ 
1.  To see your new Web and Mobile Analytics, open the Web and Mobile Analytics app within the Marketing Cloud:
    
    <img class="img-responsive" src="{{ site.baseurl }}/assets/wama_menu.png" />
1.  Then check the checkbox agreeing to the Terms and Conditions to get started:<br/><br/>
    
    <img class="img-responsive" src="{{ site.baseurl }}/assets/wama_t_and_c.png" />