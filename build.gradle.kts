group = "no.universitetsforlaget.juridika.libraries"
version = "1.0.5-SNAPSHOT"

plugins {
    id("java")
    `maven-publish`
    id("jacoco")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

val juridikaGitlabRepo: (RepositoryHandler) -> MavenArtifactRepository = {
    it.maven {
        val juridikaGitlabTokenType: String? by project
        val juridikaGitLabToken: String by project

        url = uri("https://gitlab.com/api/v4/projects/33843272/packages/maven")
        credentials(HttpHeaderCredentials::class) {
            name = juridikaGitlabTokenType ?: "Private-Token"
            value = juridikaGitLabToken
        }
        authentication {
            create<HttpHeaderAuthentication>("header")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = rootProject.name
            from(components["java"])
        }
    }
    repositories { juridikaGitlabRepo(this) }
}

dependencies {


    implementation("net.sf.saxon:Saxon-HE:9.7.0-15")
    testImplementation("junit:junit:4.12")
    testImplementation("org.hamcrest:hamcrest:2.2")

}
