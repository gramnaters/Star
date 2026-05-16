<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:ff6b6b,100:ffa502&height=200&section=header&text=Star&fontSize=80&fontColor=fff&animation=fadeIn" />
</div>

# Morphe patches for JioHotstar

[![Telegram](https://img.shields.io/badge/Telegram-2CA5E0?style=for-the-badge&logo=telegram&logoColor=white)](https://t.me/gramnaters)
[![GitHub Release](https://img.shields.io/github/v/release/gramnaters/Star?style=for-the-badge&logo=github&color=ff6b6b)](https://github.com/gramnaters/Star/releases/latest)

> Automatically unlock JioHotstar Premium — device compatibility fixes + cookie-based auth token injection

---

## Usage

> Patches use [Morphe](https://morphe.software) patcher.

**Morphe Manager**

Add Star as a patch source in Morphe Manager:

[➕ Add Star to Morphe](https://morphe.software/add-source?github=gramnaters/Star)

Then patch JioHotstar:

1. Download the original JioHotstar APKM from [APKMirror](https://www.apkmirror.com/apk/novi-digital-entertainment-private-limited/hotstar/)
2. Open Morphe Manager and select JioHotstar
3. Choose Star patches
4. **Before patching, provide your cookies:**
   - Place `sessionUserUP.txt`, `userHID.txt`, `userPID.txt`, `deviceId.txt`, and `media_token.txt` in `Downloads/Star/cookies/`
   - Or configure via patch options
5. Patch and install

> Cookies can be exported from a browser session logged into hotstar.com

**Morphe CLI**
```bash
java -jar cli.jar patch --patches star.mpp in.startv.hotstar.apkm
```

---

## Patches

> **v1.0.0** &bull; `main` &bull; 4 patches total

| Patch | Description | Options |
|-------|-------------|---------|
| Device compatibility | Fixes split APK manifest attributes and enables native library extraction for merged installs | — |
| Cookie seeding | Hooks `HsApplication.onCreate` to read auth tokens from `assets/cookies/` at startup | — |
| Identity repository injection | Patches `Ff/d.c()` (user token) and `Ff/d.i()` (media token) to return injected cookies when cache is empty | — |
| Settings | Adds SharedPreferences-backed patch configuration | — |

---

## Building

To build Star Patches, follow the [Morphe documentation](https://github.com/MorpheApp/morphe-documentation).

```bash
git clone https://github.com/gramnaters/Star.git
cd Star
./gradlew :patches:build
```

---

## How it works

1. **Device compatibility**: Removes `isSplitRequired`, `splitTypes`, and other split-related manifest attributes. Sets `extractNativeLibs=true` so merged APKs work on all devices.

2. **Cookie seeding**: Injects `CookieSeeder.seedIfNeeded()` into `HsApplication.onCreate()`. At startup, it reads `sessionUserUP.txt` (user token), `media_token.txt` (media token), and `deviceId.txt` from `assets/cookies/`, then stores them as static fields.

3. **Identity repository injection**: Patches the IdentityRepository (`Ff/d`) — the class that caches user identity. When the app's auth interceptor (`He/c`) requests tokens, our patched methods return our injected values first. If no injection is available, the original DataStore flow is used as fallback.

---

## License

[![GNU GPLv3](https://www.gnu.org/graphics/gplv3-127x51.png)](http://www.gnu.org/licenses/gpl-3.0.en.html)

This project is licensed under the GNU General Public License v3.0.
