plugins {
    id("java")
    application // Adding the application plugin to support running Java applications
}

group = "edu.hsutx"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

application {
    // You can set a default main class here if needed, or leave it unset since we define specific tasks below.
    // mainClass.set("edu.hsutx.PrintTheBible")
}

// Task to run the PrintTheBible application
tasks.register<JavaExec>("runPrintTheBible") {
    group = "application"
    description = "Runs the PrintTheBible application"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("edu.hsutx.PrintTheBible") // Set to your actual main class for PrintTheBible
}

// Task to run the VerseLookup application
tasks.register<JavaExec>("runVerseLookup") {
    group = "application"
    description = "Runs the VerseLookup application"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("edu.hsutx.VerseLookup") // Set to your actual main class for VerseLookup
    standardInput = System.`in` // Allows the task to accept standard input

}
