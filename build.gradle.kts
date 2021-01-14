plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testCompile("junit", "junit", "4.12")
    compile("com.mashape.unirest", "unirest-java", "1.3.1")
    // https://mvnrepository.com/artifact/com.google.oauth-client/google-oauth-client
    compile("com.google.oauth-client:google-oauth-client:1.31.0")
    compile("com.google.oauth-client:google-oauth-client-jetty:1.31.0")
    compile("com.google.oauth-client:google-oauth-client-assembly:1.31.0")

}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}