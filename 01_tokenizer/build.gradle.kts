plugins {
    id("java")
}

group = "com.uce"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // Source: https://mvnrepository.com/artifact/com.knuddels/jtokkit
    implementation("com.knuddels:jtokkit:1.1.0")

}

tasks.test {
    useJUnitPlatform()
}