# Slack Spring Boot Starters

[![Maven Central](https://img.shields.io/maven-central/v/io.hndrs.slack/slack-spring-boot-starter?style=for-the-badge)](https://search.maven.org/artifact/io.hndrs.slack/slack-spring-boot-starter)
[![Coverage](https://img.shields.io/sonar/coverage/hndrs_slack-spring-boot-starter?server=https%3A%2F%2Fsonarcloud.io&style=for-the-badge)](https://sonarcloud.io/dashboard?id=hndrs_slack-spring-boot-starter)
[![Supported Java Version](https://img.shields.io/badge/Supported%20Java%20Version-11%2B-informational?style=for-the-badge)]()
[![Snapshot Builds](https://img.shields.io/github/actions/workflow/status/hndrs/slack-spring-boot-starter/hndrs-gradle-publish.yml?branch=main&label=Snapshot%20Publish&style=for-the-badge)](https://github.com/kreait/slack-spring-boot-starter)
[![Maven Central](https://img.shields.io/nexus/s/io.hndrs.slack/slack-spring-boot-starter?label=Snapshots&server=https%3A%2F%2Foss.sonatype.org&style=for-the-badge)](https://oss.sonatype.org/#nexus-search;quick~io.hndrs.slack)

# Changelog

## [3.0.0] - 2019-10-25

- **changes base package to io.hdnrs**
- Added Autoconfiguration Failure Analysis
- replace oauth.access with oauth.v2.access
- removes im method group (deprecated by slack)
- remove groups and channels methods (deprecated by slack)
- Added ApplicationEnvironmentCredentialsProvider
- fix conversations.setPurpose and conversations.setTopic Request
- supported java version to 11+

## [2.0.0] - 2019-08-26

[3.0.0]: https://github.com/hndrs/slack-spring-boot-starter/compare/v2.0.0...v.3.0.0

[2.0.0]: https://github.com/hndrs/slack-spring-boot-starter/compare/v1.0.0...v.2.0.0
