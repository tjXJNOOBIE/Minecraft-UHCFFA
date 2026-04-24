# Minecraft-UHCFFA

Minecraft UHC free-for-all plugin from the 2017/2018 era. The project combines FFA combat with UHC-style kits, drops, stats, leaderboards, map switching, and ELO/ranking support.

## What It Does

- Runs a single-instance UHC FFA combat server.
- Tracks player stats, ELO/rating state, live game state, storage, and SQL-backed persistence.
- Provides commands for stats, kill top, spectating, map switching, debugging, table creation, and build mode.
- Manages random kits, random drops, scoreboards, chat utilities, disguise behavior, timing, and map state.

## Repository Layout

- `src/API/` - ELO, stats, SQL, storage, map, kit, scoreboard, disguise, and utility systems.
- `src/Commands/` - command handlers for gameplay, moderation, stats, and setup.
- `src/dev/tjxjnoobie/ffa/` - plugin entry point.
- `plugin.yml` - Bukkit plugin metadata and command declarations.
- `NETWORK_OVERVIEW` - historical network context.

## Tech Stack

- Java
- Bukkit/Spigot-style plugin APIs
- SQL-backed player and game data

## Status

Archived historical UHC FFA project. It is useful as reference material for older competitive Minecraft combat systems.
