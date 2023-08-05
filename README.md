# logger

enhance android log,based on android gradle plugin

```
@MethodCost
```

print method cost time

```
@MethodInspect
```

print method trace info

# sample usage

#build.gradle

```groovy
buildScript {
    repository {
        maven {
            url "https://jitpack.io"
        }
    }
    dependencies {
        classpath "com.github.barryxc.logger:enhance-gradle-plugin:${version}"
    }
}

repository {
    maven {
        url "https://jitpack.io"
    }
}
```





