# logger

enhance android log,based on android gradle plugin. sample usage in example project

# api

```
@MethodCost
```

print method cost time

```
@MethodInspect
```

print method trace info

# usage

build.gradle

```groovy
buildScript {
    repository {
        maven {
            url "https://jitpack.io"
        }
    }
    dependencies {
        classpath "com.github.barryxc.logger:enhance-logger-plugin:${version}"
    }
}

apply plugin: "enhance-logger-plugin"

repository {
    maven {
        url "https://jitpack.io"
    }
}
dependencies {
    implementation "com.github.barryxc.logger:logger-api:${version}"
}
```





