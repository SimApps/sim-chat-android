// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.hilt).apply(false)
    alias(libs.plugins.ksp) apply false
   // alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlinSerialization) apply false
}

buildscript {
    extra.set("compileSdkVersion", 33)
    extra.set("minSdkVersion", 26)
    extra.set("targetSdkVersion", 33)
}



fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}
true // Needed to make the Suppress annotation work for the plugins block

