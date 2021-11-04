# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

 # Keep custom model classes

# Workaround for ProGuard not recognizing dontobfuscate
# https://speakerdeck.com/chalup/proguard
-dontobfuscate
-dontoptimize
-optimizations !code/allocation/variable

-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions



  -keep class com.app.mob.halloween.Hallow { *; }
 -keep class com.app.mob.halloween.Notification.** { *; }

 -keepclassmembers class com.app.mob.halloween.Hallow { *; }
 -keepclassmembers class com.app.mob.halloween.Notification.** { *; }

 -keep class com.firebase.** { *; }
 -keepnames class com.fasterxml.jackson.** { *; }
 -keepattributes Signature


 # https://github.com/firebase/FirebaseUI-Android/issues/1175
 -dontwarn okio.**
 -dontwarn retrofit2.Call
 -dontnote retrofit2.Platform$IOS$MainThreadExecutor
 -keep class android.support.v7.widget.RecyclerView { *; }


-dontwarn com.google.**
-dontwarn org.apache.**
-dontwarn com.sap.**
-dontwarn au.com.bytecode.**
-dontwarn org.joda.**
-dontwarn android.content.**
-dontwarn android.graphics.**
-dontwarn android.util.**
-dontwarn android.view.**

-dontwarn javax.servlet.**
-dontwarn jcifs.http.**
-dontwarn org.codehaus.**

-dontwarn com.firebase.ui.**
-dontnote io.grpc.internal.**

-keep class com.firebase.** { *; }
-keep class org.apache.** { *; }
-keepnames class com.fasterxml.jackson.** { *; }
-keepnames class javax.servlet.** { *; }
-keepnames class org.ietf.jgss.** { *; }
-dontwarn org.w3c.dom.**
-dontwarn org.joda.time.**
-dontwarn org.shaded.apache.**
-dontwarn org.ietf.jgss.**

# Only necessary if you downloaded the SDK jar directly instead of from maven.
-keep class com.shaded.fasterxml.jackson.** { *; }
-keep class !com.app.mob.halloween.** { *; }


# This rule will properly ProGuard all the model classes in
# the package com.yourcompany.models. Modify to fit the structure
# of your app.






