# GmailBackground
A small library to send an email in background without user interaction

[![](https://jitpack.io/v/anton-09/GmailBackground.svg)](https://jitpack.io/#anton-09/GmailBackground)

## Why this fork?
I used to use the original lib https://github.com/luongvo/GmailBackground and fixed it to work with Android 11+

## Usage
```java
BackgroundMail.newBuilder(this)
        .withUsername("username@gmail.com")
        .withPassword("password12345")
        .withSenderName("Your sender name")
        .withMailTo("to-email@gmail.com")
        .withMailCc("cc-email@gmail.com")
        .withMailBcc("bcc-email@gmail.com")
        .withType(BackgroundMail.TYPE_PLAIN)
        .withSubject("this is the subject")
        .withBody("this is the body")
        .withAttachments(Environment.getExternalStorageDirectory().getPath() + "/test.txt")
        .withSendingMessage(R.string.sending_email)
        .withOnSuccessCallback(new BackgroundMail.OnSendingCallback() {
            @Override
            public void onSuccess() {
                // do some magic
            }
            
            @Override
            public void onFail(Exception e) {
                // do some magic
            }
        })
        .send();
```
- Set `withSendingMessage` to custom message on sending progress dialog. If not, the sending dialog will not be showed.

- If you have the feature for user to change sender `username` and `password`. You should ignore use default session. See more detail [here](http://docs.oracle.com/javaee/6/api/javax/mail/Session.html#getDefaultInstance).
```java
.withUseDefaultSession(false)
```
**Installation**

```groovy
repositories {
    // ...
    maven { url "https://jitpack.io" }
}
```
```groovy
dependencies {
    compile 'com.github.anton-09:GmailBackground:{latest-version}'
}
```
Find the `{latest-version}` in the badge at the top of this readme file.

**Permissions**
```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.INTERNET"/>
```


**Proguard**
```
-keep class org.apache.** { *; }
-dontwarn org.apache.**

-keep class com.sun.mail.** { *; }
-dontwarn com.sun.mail.**

-keep class java.beans.** { *; }
-dontwarn java.beans.**
```

## License
Copyright 2023 Anton Maksimenko

Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
