# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres
to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

- Added ApplicationEnvironmentCredentialsProvider
- Replaced oauth.access with oauth.v2.access
- Removed deprecated IM method group

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
