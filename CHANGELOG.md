# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

- removes docs project
- removes guides project
- adds [publishing info plugin](https://github.com/hndrs/gradle-publishing-info-plugin) for pom details
- upgrade to spring boot 3.0.2
- upgrade to kotlin 1.7.21 (going to 1.8.0 when detekt 1.23.x is released)
- upgrade to Java 17
- removes custom data classes for most cases (replaced with official slack api client)
- refactored autoconfiguration classes (single responsibility)
- removes evaluation report

## [3.0.0]

- Added ApplicationEnvironmentCredentialsProvider
- Replaced oauth.access with oauth.v2.access
- Removed deprecated im method group
- Removed deprecated channels method group
- Removed deprecated groups method group
- Fixed conversations.setPurpose Request
- Fixed conversations.setTopic Request

## [2.0.0] - 2020-08-26

- Added Pins API methods
- Added JsonIgnore on unknown properties to DTOs
- Unified Message objects into one
- Added app_home_opened event
- Added subteam_created event
- Added subteam_members_changed event
- Added team_join event
- Added user_change event
- Introdced Instant Type mapping in UserGroup
- Added TypedEventReceiver to make generic event type mapping possible
- Added various sample methods for testing

## [1.0.0] - 2019-10-25

Initial Release
