<div align="center">
 <img src="https://user-images.githubusercontent.com/62361708/227724184-46950307-c0ed-41ac-a7cd-621601c1edb0.png" width=256>
</div>

Minecraft plugin that adds an **XPShop** to the game where players can purchase items with XP.

In the **XPShop** you can put any item, custom or not on any level price. The items in the **XPShop** can also be configured to be purchasable with certain permissions additionally to the level price.

Uses [NexiaAPI](https://github.com/NexiaDevelopers/NexiaAPI), [ACF](https://github.com/aikar/commands) and [SpiGUI](https://github.com/SamJakob/SpiGUI).

Developed by [HeadMonitor](https://github.com/HeadMonitor) for the [Nexia Network](https://www.playnexia.net/).

<div align="center">
 <img src="https://user-images.githubusercontent.com/62361708/227810985-0f602a5a-c5ad-46e0-b5e2-855815de74da.png">
</div>

<div align="center">
 <img src="https://user-images.githubusercontent.com/62361708/225291005-6b6bf7df-50bf-48f6-acbe-d50772ee865a.png">
</div>

Just place the .jar file in your plugins folder and the plugin should work! Easy as that! After that you can configure the the plugin from the `config.yml` to your needs.

- Every Section in every File in the `XPShopItems/` folder represents an item for sale. Each file's format should match that of the `ExampleItems.yml` file. There can be as
many Sections in each File. We recommend grouping the items in each file by type.

<div align="center">
 <img src="https://user-images.githubusercontent.com/62361708/225288203-3f8324f9-5a02-4156-9159-b13429466559.png">
</div>

- `/xpshop` Opens the XPShop GUI.
- `/xpshop buy <item>` Purchases a specific item from the XPShop.

<div align="center">
 <img src="https://user-images.githubusercontent.com/62361708/225288290-f1b60d76-9af8-40f6-90d3-c5a6083cf661.png">
</div>

- `net.nexia.xpshop.buy`
- `net.nexia.xpshop.<item>`

<div align="center">
 <img src="https://user-images.githubusercontent.com/62361708/225288387-3b514380-63ec-467d-95c0-c0ebd76105ac.png">
</div>

<details>
<summary>config.yml</summary>
<pre>

    # MAIN SETTINGS
    CreateExampleFiles: true    # Set whether the example files should be recreated if deleted. (WARNING: These get replaced each time the server restarts.)
    PermissionBasedShop: false  # Set whether the shop should be permission based. Each item will require a permission in the syntax `net.nexia.xpshop.item`

    #   __  ______  ____  _
    #   \ \/ /  _ \/ ___|| |__   ___  _ __
    #    \  /| |_) \___ \| '_ \ / _ \| '_ \
    #    /  \|  __/ ___) | | | | (_) | |_) |
    #   /_/\_\_|   |____/|_| |_|\___/| .__/
    #                                |_|
</pre>
</details>

<div align="center">
 <img src="https://user-images.githubusercontent.com/62361708/225434090-dce1fb3c-9ff6-43e9-80c3-76ac800ad7f5.png">
</div>

You can get **Support**, **Request a Feature** or **Report a Bug** through the [Discussion](https://blank.org) tab or through our [Discord](https://blank.org). We will do our best to help you out!

<div align="center">
 <img src="https://user-images.githubusercontent.com/62361708/225288503-a789afa0-4be5-4ff4-a83f-9d28c4dda1ed.png">
</div>

- **Q**: I don't exactly understand how to set the plugin up. Can you provide help? \
  **A**: Yes! You can leave a comment on the [Discussion](https://blank.org) tab, or join our
         [Discord](https://blank.org) server.

- **Q**: I have an issue, found a bug or want to request a feature. \
  **A**: You can leave a comment on the [Discussion](https://blank.org) tab, or join our [Discord](https://blank.org) 
         server.
         
- **Q**: If I request a feature what are the chances of it being accepted? How long will it take to implement? \
  **A**: The chances are very high! We want the Plugin to be as much useful to you as possible. As for the time to implement
         this solely depends on the complexity of the feature, but it shouldn't take more than a few days!

<div align="center">
 <img src="https://user-images.githubusercontent.com/62361708/225436833-ae8c3941-335e-452b-aa48-7cc490ee4a11.png">
</div>

<p align="center">
  <img src="https://user-images.githubusercontent.com/62361708/220228413-2fea94e2-f7fe-4708-84d7-f8ac6a7bec5f.png"/>
</p>
