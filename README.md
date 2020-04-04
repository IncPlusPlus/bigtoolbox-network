[![Codacy Badge](https://api.codacy.com/project/badge/Grade/124241fbf65443ac84f660d9c63bfa26)](https://www.codacy.com/manual/IncPlusPlus/bigtoolbox-network?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=IncPlusPlus/bigtoolbox-network&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/7cc79ed2-f11f-4e97-a42a-b6d9e2696282)](https://codebeat.co/projects/github-com-incplusplus-bigtoolbox-network-master)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/IncPlusPlus/bigtoolbox-network.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/IncPlusPlus/bigtoolbox-network/context:java)
[![Maintainability](https://api.codeclimate.com/v1/badges/6f8ecc4a621cb348de55/maintainability)](https://codeclimate.com/github/IncPlusPlus/bigtoolbox-network/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/6f8ecc4a621cb348de55/test_coverage)](https://codeclimate.com/github/IncPlusPlus/bigtoolbox-network/test_coverage)
[![codecov](https://codecov.io/gh/IncPlusPlus/bigtoolbox-network/branch/master/graph/badge.svg)](https://codecov.io/gh/IncPlusPlus/bigtoolbox-network)
[![Build Status](https://travis-ci.com/IncPlusPlus/bigtoolbox-network.svg?branch=master)](https://travis-ci.com/IncPlusPlus/bigtoolbox-network)
[![Known Vulnerabilities](https://snyk.io//test/github/IncPlusPlus/bigtoolbox-network/badge.svg?targetFile=pom.xml)](https://snyk.io//test/github/IncPlusPlus/bigtoolbox-network?targetFile=pom.xml)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/IncPlusPlus/bigtoolbox-network.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/IncPlusPlus/bigtoolbox-network/alerts/)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FIncPlusPlus%2Fbigtoolbox-network.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2FIncPlusPlus%2Fbigtoolbox-network?ref=badge_shield)

# What is this?
This is the network module of [bigtoolbox](https://github.com/IncPlusPlus/bigtoolbox). My lofty goal with this is to
have a lot of networking related functions implemented here that will work regardless of OS (within reason). Currently,
the Windows WiFi functionality is the focus and is in a state of WIP.

# Usage in your project
More instructions for using this will come when this is out of alpha.

## Important dependency note
A dependency of this project uses SLF4J. This library does not provide any SLF4J bindings,
so you may see an error in your console that looks like the following.
```text
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
```
If you are using `bigtoolbox-network` as a dependency in your project, you should do the following.

### If your project is an _application_
If your application is something an end-user will be running and _not_ an artifact that another project will use as a 
dependency, you can remove the SLF4J message by placing the following in your POM.
```xml
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-nop</artifactId>
  <version>1.7.25</version>
</dependency>
```
If you already have an SLF4J binding (such as `logback-classic` from `ch.qos.logback`), you **_shouldn't_** need to add 
the above snippet to your POM.

### If your project is a _library_
You don't need to do anything. If you have a bunch of tests that are throwing the above
message into the console, and it really bothers you, put the following in the `<depencencies>` section of your `pom.xml` file.
```xml
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-nop</artifactId>
  <version>1.7.25</version>
  <!-- This gets rid of annoying SLF4J error when running classes directly in this library without
   dumping SLF4J-NOP onto the classpath of projects that depend on this library. -->
  <optional>true</optional>
</dependency>
```


## License
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FIncPlusPlus%2Fbigtoolbox-network.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2FIncPlusPlus%2Fbigtoolbox-network?ref=badge_large)