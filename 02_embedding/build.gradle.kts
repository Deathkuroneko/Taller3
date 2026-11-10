plugins {
    id("java")
}

group = "com.uce"
version = "unspecified"

repositories {
    mavenCentral()
}

val langchain4jVersion = "1.23.0"

dependencies {
    implementation("dev.langchain4j:langchain4j:${langchain4jVersion}")
    implementation("dev.langchain4j:langchain4j-embeddings:${langchain4jVersion}-beta33")

}

