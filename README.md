# cadabra

Sample application showing the use of boot and boot-reload with browser, ios and android simultaneously.

## Setup

Prerequisite: you must have cordova installed and the ios and android toolchain. And boot, obviously.

### Bootstrap

Run the following in one terminal

```bash
cordova plugins add cordova-plugin-whitelist
cordova platforms add browser
cordova platforms add ios
cordova platforms add android
boot development build
cordova prepare
```

Then, in four separate terminals:

```bash
boot dev

cordova run browser

cordova run ios

cordova run android
```

Cordova will by default open up a Chrome tab, an iOS simulator and an Android emulator.

Changes in src/cadabra/core.cljs should be pushed to the three platforms simultaneously.
