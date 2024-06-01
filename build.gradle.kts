// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    dependencies {
        // Add dependencies.
        classpath(libs.tools.gradle)
        classpath(libs.agconnect.agcp)
    }
}
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
}