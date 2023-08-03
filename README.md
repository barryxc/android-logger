# logger

enhance android log , transform task based to implement instrument

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
build.gradle
buildScript{
	repository{
		maven{
			url "https://jitpack.io"
		}
  }
  
  dependencies{
    classpath ""
  }
}

subprojects{
  repository{
		maven{
			url "https://jitpack.io"
		}
  }
}

```





