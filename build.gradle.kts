plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false // ✅ Correct kapt alias from libs.versions.toml
    alias(libs.plugins.kotlin.compose) apply false // ✅ Optional: Add this if you're using Compose

    id("com.google.gms.google-services") version "4.4.2" apply false // ✅ Required for Firebase
}
